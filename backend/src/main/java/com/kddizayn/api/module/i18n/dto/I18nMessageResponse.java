package com.kddizayn.api.module.i18n.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class I18nMessageResponse {
    private Long id;
    private String key;
    private String locale;
    private String content;
    private LocalDateTime updatedAt;
}
