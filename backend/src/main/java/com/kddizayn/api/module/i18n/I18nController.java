package com.kddizayn.api.module.i18n;

import com.kddizayn.api.module.i18n.dto.I18nMessageRequest;
import com.kddizayn.api.module.i18n.dto.I18nMessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class I18nController {

    private final I18nService i18nService;

    // ── Public ────────────────────────────────────────────────────────────────

    /**
     * GET /api/v1/i18n/public/{locale}
     * Front-end calls this to hydrate its i18n store (e.g. /public/tr or /public/en).
     * Returns a flat key-value map.
     */
    @GetMapping("/api/v1/i18n/public/{locale}")
    public ResponseEntity<Map<String, String>> getPublic(@PathVariable String locale) {
        return ResponseEntity.ok(i18nService.getLocaleMap(locale));
    }

    // ── Admin ─────────────────────────────────────────────────────────────────

    /**
     * GET /api/v1/admin/i18n/messages
     * All messages — optionally filter by ?locale=tr
     */
    @GetMapping("/api/v1/admin/i18n/messages")
    public ResponseEntity<List<I18nMessageResponse>> getAll(
            @RequestParam(required = false) String locale) {

        if (locale != null && !locale.isBlank()) {
            return ResponseEntity.ok(i18nService.findByLocale(locale));
        }
        return ResponseEntity.ok(i18nService.findAll());
    }

    /**
     * POST /api/v1/admin/i18n/messages
     * Add a new key+locale pair.
     */
    @PostMapping("/api/v1/admin/i18n/messages")
    public ResponseEntity<I18nMessageResponse> create(
            @Valid @RequestBody I18nMessageRequest request) {
        return ResponseEntity.ok(i18nService.create(request));
    }

    /**
     * PUT /api/v1/admin/i18n/messages/{id}
     * Update the content of an existing message.
     */
    @PutMapping("/api/v1/admin/i18n/messages/{id}")
    public ResponseEntity<I18nMessageResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody I18nMessageRequest request) {
        return ResponseEntity.ok(i18nService.update(id, request));
    }
}
