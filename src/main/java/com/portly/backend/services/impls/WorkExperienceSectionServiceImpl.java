package com.portly.backend.services.impls;

import com.portly.backend.entities.WorkExperienceSection;
import com.portly.backend.repositories.WorkExperienceSectionRepository;
import com.portly.backend.services.WorkExperienceSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceSectionServiceImpl implements WorkExperienceSectionService {

    private final WorkExperienceSectionRepository workExperienceSectionRepository;
    private final WorkServiceImpl workService;

    @Override
    public WorkExperienceSection createWorkExperienceSection() {

        WorkExperienceSection workExperienceSection = WorkExperienceSection.builder()
                .backgroundColour("0xFF001228")
                .textColour("0xFFFFFFFF")
                .cardBackgroundColour("0xFFFFFFFF")
                .cardTextColour("0xFF000000")
                .workList(List.of(workService.createWork(),workService.createWork()))
                .build();

        return workExperienceSectionRepository.save(workExperienceSection);
    }

    @Override
    public WorkExperienceSection updateWorkExperienceSection(WorkExperienceSection workExperienceSection) {
        return workExperienceSectionRepository.save(workExperienceSection);
    }
}
