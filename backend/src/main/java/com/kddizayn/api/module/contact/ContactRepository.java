package com.kddizayn.api.module.contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactInfo, Long> {
    // There is always exactly one row; we fetch it by id=1 via service.
}
