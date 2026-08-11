package com.kddizayn.api.module.i18n.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class I18nMessageRequest {

    @NotBlank(message = "Key is required")
    private String key;

    @NotBlank(message = "Locale is required")
    @Pattern(regexp = "^(tr|en)$", message = "Locale must be 'tr' or 'en'")
    private String locale;

    @NotBlank(message = "Content is required")
    private String content;
}
