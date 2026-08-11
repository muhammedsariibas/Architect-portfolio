package com.kddizayn.api.module.i18n;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface I18nRepository extends JpaRepository<I18nMessage, Long> {

    List<I18nMessage> findByLocale(String locale);

    Optional<I18nMessage> findByKeyAndLocale(String key, String locale);

    boolean existsByKeyAndLocale(String key, String locale);
}
