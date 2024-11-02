package com.portly.backend.repositories;

import com.portly.backend.entities.AppBarSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppBarSectionRepository extends JpaRepository<AppBarSection,Long> {
}
