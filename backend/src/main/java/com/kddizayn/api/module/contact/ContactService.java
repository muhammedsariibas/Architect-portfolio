package com.kddizayn.api.module.contact;

import com.kddizayn.api.exception.ResourceNotFoundException;
import com.kddizayn.api.module.contact.dto.ContactInfoRequest;
import com.kddizayn.api.module.contact.dto.ContactInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private static final Long SINGLETON_ID = 1L;

    private final ContactRepository contactRepository;

    // ── Public ────────────────────────────────────────────────────────────────

    public ContactInfoResponse getPublic() {
        ContactInfo info = contactRepository.findById(SINGLETON_ID)
                .orElseThrow(() -> new ResourceNotFoundException("Contact info not configured yet"));
        return toResponse(info);
    }

    // ── Admin ─────────────────────────────────────────────────────────────────

    @Transactional
    public ContactInfoResponse update(ContactInfoRequest req) {
        // Upsert pattern — always operate on the single row with id=1
        ContactInfo info = contactRepository.findById(SINGLETON_ID)
                .orElse(ContactInfo.builder().id(SINGLETON_ID).build());

        info.setPhone(req.getPhone());
        info.setEmail(req.getEmail());
        info.setAddress(req.getAddress());
        info.setGoogleMapsUrl(req.getGoogleMapsUrl());
        info.setFacebookUrl(req.getFacebookUrl());
        info.setInstagramUrl(req.getInstagramUrl());
        info.setLinkedinUrl(req.getLinkedinUrl());
        info.setWorkingHours(req.getWorkingHours());

        return toResponse(contactRepository.save(info));
    }

    // ── Helper ────────────────────────────────────────────────────────────────

    private ContactInfoResponse toResponse(ContactInfo c) {
        return ContactInfoResponse.builder()
                .id(c.getId())
                .phone(c.getPhone())
                .email(c.getEmail())
                .address(c.getAddress())
                .googleMapsUrl(c.getGoogleMapsUrl())
                .facebookUrl(c.getFacebookUrl())
                .instagramUrl(c.getInstagramUrl())
                .linkedinUrl(c.getLinkedinUrl())
                .workingHours(c.getWorkingHours())
                .build();
    }
}
