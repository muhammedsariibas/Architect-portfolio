package com.kddizayn.api.module.project;

import com.kddizayn.api.exception.ResourceNotFoundException;
import com.kddizayn.api.module.media.Media;
import com.kddizayn.api.module.media.MediaRepository;
import com.kddizayn.api.module.media.dto.MediaResponse;
import com.kddizayn.api.module.project.dto.ProjectRequest;
import com.kddizayn.api.module.project.dto.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final MediaRepository mediaRepository;

    public List<ProjectResponse> findPublic() { return projectRepository.findAllByOrderByCreatedAtDesc().stream().map(this::toResponse).toList(); }
    public List<ProjectResponse> findAll() { return findPublic(); }
    public ProjectResponse create(ProjectRequest request) { return toResponse(projectRepository.save(Project.builder().title(request.getTitle()).description(request.getDescription()).category(request.getCategory()).build())); }
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = get(id); project.setTitle(request.getTitle()); project.setDescription(request.getDescription()); project.setCategory(request.getCategory());
        return toResponse(projectRepository.save(project));
    }
    public void delete(Long id) {
        Project project = get(id);
        project.getMedia().forEach(media -> media.setProject(null));
        mediaRepository.saveAll(project.getMedia());
        projectRepository.delete(project);
    }
    public void removeImage(Long projectId, Long mediaId) {
        Project project = get(projectId);
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException("Media not found"));
        if (media.getProject() == null || !projectId.equals(media.getProject().getId())) throw new ResourceNotFoundException("Image is not assigned to this project");
        media.setProject(null); mediaRepository.save(media);
    }
    private Project get(Long id) { return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id)); }
    private ProjectResponse toResponse(Project p) {
        List<MediaResponse> images = p.getMedia().stream().map(m -> MediaResponse.builder().id(m.getId()).fileName(m.getFileName()).fileType(m.getFileType()).fileUrl(m.getFileUrl()).category(m.getCategory()).projectId(p.getId()).createdAt(m.getCreatedAt()).build()).collect(Collectors.toList());
        return ProjectResponse.builder().id(p.getId()).title(p.getTitle()).description(p.getDescription()).category(p.getCategory()).createdAt(p.getCreatedAt()).images(images).build();
    }
}
