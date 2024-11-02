package com.portly.backend.services.impls;

import com.portly.backend.entities.SkillSection;
import com.portly.backend.repositories.SkillSectionRepository;
import com.portly.backend.services.SkillSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillSectionServiceImpl implements SkillSectionService {

    private final SkillSectionRepository skillSectionRepository;

    @Override
    public SkillSection createSkillSection() {
        SkillSection skillSection = SkillSection.builder()
                .advance(List.of("C++", "Flutter", "Godot", "Java Swing"))
                .beginner(List.of("Python", "HTML", "CSS","JS"))
                .intermediate(List.of("Java", "C"))
                .backgroundColour("0xFFFFFFFF")
                .textColour("0xFF000000")
                .tagBackgroundColour("0xFF212121")
                .tagTextColour("0xFFFFFFFF")
                .build();
        return skillSectionRepository.save(skillSection);
    }

    @Override
    public SkillSection updateSkillSection(SkillSection skillSection) {
        return skillSectionRepository.save(skillSection);
    }
}
