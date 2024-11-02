package com.portly.backend.services;

import com.portly.backend.entities.Project;

public interface ProjectService {

    Project createProject();

    Project updateProject(Project project);

    Project addProject(Project project);

    void deleteProject(Project project);

}
