package com.kddizayn.api.module.media.dto;

import com.kddizayn.api.module.media.MediaCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MediaResponse {
    private Long id;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private MediaCategory category;
    private Long projectId;
    private LocalDateTime createdAt;
}
