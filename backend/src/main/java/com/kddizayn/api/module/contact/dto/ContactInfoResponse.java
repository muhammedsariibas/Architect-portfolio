package com.kddizayn.api.module.contact.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactInfoResponse {
    private Long id;
    private String phone;
    private String email;
    private String address;
    private String googleMapsUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String workingHours;
}
