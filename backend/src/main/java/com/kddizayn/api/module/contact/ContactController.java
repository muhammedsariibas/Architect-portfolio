package com.kddizayn.api.module.contact;

import com.kddizayn.api.module.contact.dto.ContactInfoRequest;
import com.kddizayn.api.module.contact.dto.ContactInfoResponse;
import com.kddizayn.api.module.contact.dto.ContactMessageRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    private final ContactMessageService contactMessageService;

    /**
     * GET /api/v1/contact/public
     * Unauthenticated — front-end fetches contact details here.
     */
    @GetMapping("/public")
    public ResponseEntity<ContactInfoResponse> getPublic() {
        return ResponseEntity.ok(contactService.getPublic());
    }

    /** POST /api/v1/contact/message — send a message from the public contact form. */
    @PostMapping("/message")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody ContactMessageRequest request) {
        contactMessageService.send(request);
        return ResponseEntity.noContent().build();
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
