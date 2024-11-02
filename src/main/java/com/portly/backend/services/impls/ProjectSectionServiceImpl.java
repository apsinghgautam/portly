package com.portly.backend.services.impls;

import com.portly.backend.entities.ProjectSection;
import com.portly.backend.repositories.ProjectSectionRepository;
import com.portly.backend.services.ProjectSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectSectionServiceImpl implements ProjectSectionService {

    private final ProjectSectionRepository projectSectionRepository;
    private final ProjectServiceImpl projectService;

    @Override
    public ProjectSection createProjectSection() {
        ProjectSection projectSection = ProjectSection.builder()
                .projects(List.of(projectService.createProject(),projectService.createProject()))
                .backgroundColour("0xFF001228")
                .textColour("0xFFFFFFFF")
                .projectButtonBackgroundColour("0xFFB41C6F")
                .projectButtonTextColour("0xFFFFFFFF")
                .projectBackgroundColour("0xFFFFFFFF")
                .projectTextColour("0xFF000000")
                .build();
        return projectSectionRepository.save(projectSection);
    }

    @Override
    public ProjectSection updateProjectSection(ProjectSection projectSection) {
        return projectSectionRepository.save(projectSection);
    }
}
