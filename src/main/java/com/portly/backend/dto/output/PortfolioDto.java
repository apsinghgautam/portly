package com.portly.backend.dto.output;

import com.portly.backend.dto.*;
import com.portly.backend.entities.enums.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortfolioDto {

    private Map<Section, Boolean> activeSection;
    private AppBarDto appBarSection;
    private AchievementDto achievementSection;
    private ContactDto contactSection;
    private ProjectSectionDto projectSection;
    private WorkExperienceDto workExperienceSection;
    private SkillDto skillSection;
    private HeaderDto headerSection;

}
