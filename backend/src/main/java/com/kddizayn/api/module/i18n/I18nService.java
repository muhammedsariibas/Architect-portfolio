package com.kddizayn.api.module.i18n;

import com.kddizayn.api.exception.ResourceNotFoundException;
import com.kddizayn.api.module.i18n.dto.I18nMessageRequest;
import com.kddizayn.api.module.i18n.dto.I18nMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class I18nService {

    private final I18nRepository i18nRepository;

    // ── Public ────────────────────────────────────────────────────────────────

    /**
     * Returns all key-value pairs for the requested locale as a flat map.
     * e.g. { "hero.title": "KD Dizayn", "nav.services": "Hizmetler" }
     */
    public Map<String, String> getLocaleMap(String locale) {
        return i18nRepository.findByLocale(locale)
                .stream()
                .collect(Collectors.toMap(
                        I18nMessage::getKey,
                        I18nMessage::getContent,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // ── Admin ─────────────────────────────────────────────────────────────────

    public List<I18nMessageResponse> findAll() {
        return i18nRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<I18nMessageResponse> findByLocale(String locale) {
        return i18nRepository.findByLocale(locale)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public I18nMessageResponse create(I18nMessageRequest req) {
        if (i18nRepository.existsByKeyAndLocale(req.getKey(), req.getLocale())) {
            throw new IllegalArgumentException(
                    "Key '" + req.getKey() + "' already exists for locale '" + req.getLocale() + "'");
        }
        I18nMessage msg = I18nMessage.builder()
                .key(req.getKey())
                .locale(req.getLocale())
                .content(req.getContent())
                .build();
        return toResponse(i18nRepository.save(msg));
    }

    @Transactional
    public I18nMessageResponse update(Long id, I18nMessageRequest req) {
        I18nMessage msg = i18nRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("I18n message not found with id: " + id));

        msg.setContent(req.getContent());
        // key & locale changes are intentionally not allowed to prevent broken refs
        return toResponse(i18nRepository.save(msg));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private I18nMessageResponse toResponse(I18nMessage m) {
        return I18nMessageResponse.builder()
                .id(m.getId())
                .key(m.getKey())
                .locale(m.getLocale())
                .content(m.getContent())
                .updatedAt(m.getUpdatedAt())
                .build();
    }
}
