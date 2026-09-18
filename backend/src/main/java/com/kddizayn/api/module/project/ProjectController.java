package com.kddizayn.api.module.project;

import com.kddizayn.api.module.project.dto.ProjectRequest;
import com.kddizayn.api.module.project.dto.ProjectResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/api/v1/projects/public") public ResponseEntity<List<ProjectResponse>> publicProjects() { return ResponseEntity.ok(projectService.findPublic()); }
    @GetMapping("/api/v1/admin/projects") public ResponseEntity<List<ProjectResponse>> all() { return ResponseEntity.ok(projectService.findAll()); }
    @PostMapping("/api/v1/admin/projects") public ResponseEntity<ProjectResponse> create(@Valid @RequestBody ProjectRequest request) { return ResponseEntity.ok(projectService.create(request)); }
    @PutMapping("/api/v1/admin/projects/{id}") public ResponseEntity<ProjectResponse> update(@PathVariable Long id, @Valid @RequestBody ProjectRequest request) { return ResponseEntity.ok(projectService.update(id, request)); }
    @DeleteMapping("/api/v1/admin/projects/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { projectService.delete(id); return ResponseEntity.noContent().build(); }
    @DeleteMapping("/api/v1/admin/projects/{projectId}/images/{mediaId}") public ResponseEntity<Void> removeImage(@PathVariable Long projectId, @PathVariable Long mediaId) { projectService.removeImage(projectId, mediaId); return ResponseEntity.noContent().build(); }
}
