package com.kddizayn.api.module.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactInfoRequest {

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    private String googleMapsUrl;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    private String workingHours;
}
