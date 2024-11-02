package com.portly.backend.repositories;

import com.portly.backend.entities.AchievementSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementSectionRepository extends JpaRepository<AchievementSection,Long> {

}
