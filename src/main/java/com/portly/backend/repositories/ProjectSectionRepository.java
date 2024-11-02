package com.portly.backend.repositories;

import com.portly.backend.entities.ProjectSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSectionRepository extends JpaRepository<ProjectSection,Long> {
}
