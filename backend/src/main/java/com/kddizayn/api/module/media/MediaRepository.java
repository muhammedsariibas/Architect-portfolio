package com.kddizayn.api.module.media;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByOrderByCreatedAtDesc();

    List<Media> findByCategoryOrderByCreatedAtDesc(MediaCategory category);

    Optional<Media> findFirstByCategory(MediaCategory category);
}
