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
public class WorkExperienceSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String backgroundColour;
    private String textColour;
    private String cardBackgroundColour;
    private String cardTextColour;

    @OneToMany
    private List<Work> workList;

}
