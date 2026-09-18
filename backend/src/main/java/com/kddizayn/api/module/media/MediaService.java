package com.kddizayn.api.module.media;

import com.kddizayn.api.exception.FileStorageException;
import com.kddizayn.api.exception.ResourceNotFoundException;
import com.kddizayn.api.module.media.dto.MediaResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import com.kddizayn.api.module.project.Project;
import com.kddizayn.api.module.project.ProjectRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaService {

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg", "image/png", "image/webp"
    );
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024L; // 10 MB

    private final MediaRepository mediaRepository;
    private final ProjectRepository projectRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new FileStorageException("Could not create upload directory", e);
        }
    }

    // ── Public ────────────────────────────────────────────────────────────────

    public List<MediaResponse> findAll() {
        return mediaRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<MediaResponse> findByCategory(MediaCategory category) {
        return mediaRepository.findByCategoryOrderByCreatedAtDesc(category)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── Admin ─────────────────────────────────────────────────────────────────

    public MediaResponse upload(MultipartFile file, MediaCategory category) {
        return upload(file, category, null);
    }

    public MediaResponse upload(MultipartFile file, MediaCategory category, Long projectId) {
        validate(file);

        String originalName = file.getOriginalFilename();
        String extension    = getExtension(originalName);
        String storedName   = UUID.randomUUID() + "." + extension;
        Path   targetPath   = Paths.get(uploadDir, storedName);

        try {
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file " + originalName, e);
        }

        String fileUrl = buildUrl(storedName);
        Project project = projectId == null ? null : projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        Media media = Media.builder()
                .fileName(originalName)
                .fileType(file.getContentType())
                .filePath(targetPath.toAbsolutePath().toString())
                .fileUrl(fileUrl)
                .category(category)
                .project(project)
                .build();

        return toResponse(mediaRepository.save(media));
    }

    public void delete(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media not found with id: " + id));
        deleteFile(media.getFilePath());
        mediaRepository.delete(media);
    }

    /**
     * Replace current LOGO: deletes the old file+record and saves the new one.
     */
    public MediaResponse updateLogo(MultipartFile file) {
        mediaRepository.findFirstByCategory(MediaCategory.LOGO).ifPresent(old -> {
            deleteFile(old.getFilePath());
            mediaRepository.delete(old);
        });
        return upload(file, MediaCategory.LOGO);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void validate(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileStorageException("Cannot store empty file");
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new FileStorageException("File type not allowed: " + file.getContentType()
                    + ". Allowed: PNG, JPG, WEBP");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileStorageException("File size exceeds the 10 MB limit");
        }
    }

    private void deleteFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            log.warn("Could not delete file at path: {}", filePath);
        }
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "bin";
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }

    private String buildUrl(String storedName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(storedName)
                .toUriString();
    }

    private MediaResponse toResponse(Media m) {
        return MediaResponse.builder()
                .id(m.getId())
                .fileName(m.getFileName())
                .fileType(m.getFileType())
                .fileUrl(m.getFileUrl())
                .category(m.getCategory())
                .projectId(m.getProject() == null ? null : m.getProject().getId())
                .createdAt(m.getCreatedAt())
                .build();
    }
}
