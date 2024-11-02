package com.portly.backend.repositories;

import com.portly.backend.entities.ContactSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactSectionRepository extends JpaRepository<ContactSection,Long> {
}
