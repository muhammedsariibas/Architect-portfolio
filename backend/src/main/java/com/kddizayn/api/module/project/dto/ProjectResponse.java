package com.kddizayn.api.module.project.dto;

import com.kddizayn.api.module.media.dto.MediaResponse;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime createdAt;
    private List<MediaResponse> images;
}
