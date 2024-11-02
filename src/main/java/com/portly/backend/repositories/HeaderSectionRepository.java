package com.portly.backend.repositories;

import com.portly.backend.entities.HeaderSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderSectionRepository extends JpaRepository<HeaderSection,Long> {
}
