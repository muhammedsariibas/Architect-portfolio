package com.kddizayn.api.module.i18n;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "i18n_messages",
    uniqueConstraints = @UniqueConstraint(columnNames = {"key_name", "locale"})
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class I18nMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** e.g. "hero.title", "nav.services" */
    @Column(name = "key_name", nullable = false)
    private String key;

    /** "tr" or "en" */
    @Column(nullable = false, length = 5)
    private String locale;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
