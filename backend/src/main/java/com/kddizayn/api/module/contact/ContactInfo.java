package com.kddizayn.api.module.contact;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_info")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String googleMapsUrl;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    private String workingHours;
}
