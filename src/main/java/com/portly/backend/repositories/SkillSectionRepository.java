package com.portly.backend.repositories;

import com.portly.backend.entities.SkillSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSectionRepository extends JpaRepository<SkillSection,Long> {
}
