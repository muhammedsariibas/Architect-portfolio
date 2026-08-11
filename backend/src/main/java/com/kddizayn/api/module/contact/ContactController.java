package com.kddizayn.api.module.contact;

import com.kddizayn.api.module.contact.dto.ContactInfoRequest;
import com.kddizayn.api.module.contact.dto.ContactInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    /**
     * GET /api/v1/contact/public
     * Unauthenticated — front-end fetches contact details here.
     */
    @GetMapping("/public")
    public ResponseEntity<ContactInfoResponse> getPublic() {
        return ResponseEntity.ok(contactService.getPublic());
    }

    /**
     * PUT /api/v1/contact/admin
     * ROLE_ADMIN — update all contact fields in one request.
     */
    @PutMapping("/admin")
    public ResponseEntity<ContactInfoResponse> update(
            @Valid @RequestBody ContactInfoRequest request) {
        return ResponseEntity.ok(contactService.update(request));
    }
}
