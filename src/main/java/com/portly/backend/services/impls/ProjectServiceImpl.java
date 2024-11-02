package com.portly.backend.services.impls;

import com.portly.backend.entities.Project;
import com.portly.backend.repositories.ProjectRepository;
import com.portly.backend.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project createProject() {
        Project project = Project.builder()
                .projectUrl("https://shashank1q.github.io/GeminiChatBot/")
                .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.")
                .image(null)
                .tags(List.of("Flutter", "Bloc", "Dio"))
                .title("To-Do Webapp")
                .build();
        return projectRepository.save(project);
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }
}
