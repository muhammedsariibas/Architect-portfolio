package com.kddizayn.api.module.media;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import com.kddizayn.api.module.project.Project;

@Entity
@Table(name = "media")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;   // e.g. "image/jpeg"

    @Column(nullable = false)
    private String filePath;   // absolute path on disk

    @Column(nullable = false)
    private String fileUrl;    // public URL served to clients

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
