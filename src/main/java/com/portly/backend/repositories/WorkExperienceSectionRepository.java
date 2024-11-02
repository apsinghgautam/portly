package com.portly.backend.repositories;

import com.portly.backend.entities.WorkExperienceSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceSectionRepository extends JpaRepository<WorkExperienceSection,Long> {
}
