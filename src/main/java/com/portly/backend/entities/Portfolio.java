package com.portly.backend.entities;


import com.portly.backend.entities.enums.Section;
import jakarta.persistence.*;
import lombok.*;


import java.util.Map;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isPublic;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Section, Boolean> activeSection;

    @OneToOne
    private User user;

    @OneToOne
    private AppBarSection appBarSection;

    @OneToOne
    private AchievementSection achievementSection;

    @OneToOne
    private ContactSection contactSection;

    @OneToOne
    private ProjectSection projectSection;

    @OneToOne
    private WorkExperienceSection workExperienceSection;

    @OneToOne
    private SkillSection skillSection;

    @OneToOne
    private HeaderSection headerSection;

}
