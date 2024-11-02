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
public class SkillSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> advance;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> intermediate;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> beginner;

    private String backgroundColour;
    private String textColour;
    private String tagBackgroundColour;
    private String tagTextColour;


}
