package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectSectionDto {

    private String backgroundColour;
    private String textColour;
    private String projectBackgroundColour;
    private String projectTextColour;
    private String projectButtonBackgroundColour;
    private String projectButtonTextColour;
    private List<ProjectDto> projects;

}
