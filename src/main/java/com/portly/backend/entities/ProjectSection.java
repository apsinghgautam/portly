package com.portly.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String backgroundColour;
    private String textColour;
    private String projectBackgroundColour;
    private String projectTextColour;
    private String projectButtonBackgroundColour;
    private String projectButtonTextColour;

    @OneToMany
    private List<Project> projects;

}
