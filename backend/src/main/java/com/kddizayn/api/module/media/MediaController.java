package com.kddizayn.api.module.media;

import com.kddizayn.api.module.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    // ── Public ────────────────────────────────────────────────────────────────

    /** GET /api/v1/media/public/all — All media (for front-end) */
    @GetMapping("/api/v1/media/public/all")
    public ResponseEntity<List<MediaResponse>> getAll() {
        return ResponseEntity.ok(mediaService.findAll());
    }

    /** GET /api/v1/media/public/category/{category} — Filter by category */
    @GetMapping("/api/v1/media/public/category/{category}")
    public ResponseEntity<List<MediaResponse>> getByCategory(
            @PathVariable MediaCategory category) {
        return ResponseEntity.ok(mediaService.findByCategory(category));
    }

    // ── Admin ─────────────────────────────────────────────────────────────────

    /**
     * POST /api/v1/admin/media/upload
     * Multipart: file + category (SLIDER | GALLERY | LOGO | GENERAL)
     */
    @PostMapping("/api/v1/admin/media/upload")
    public ResponseEntity<MediaResponse> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("category") MediaCategory category,
            @RequestParam(value = "projectId", required = false) Long projectId) {
        return ResponseEntity.ok(mediaService.upload(file, category, projectId));
    }

    /** DELETE /api/v1/admin/media/{id} */
    @DeleteMapping("/api/v1/admin/media/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** PUT /api/v1/admin/media/logo — Replace site logo */
    @PutMapping("/api/v1/admin/media/logo")
    public ResponseEntity<MediaResponse> updateLogo(
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(mediaService.updateLogo(file));
    }
}
