package com.portly.backend.services;

import com.portly.backend.dto.*;
import com.portly.backend.dto.ProjectSectionDto;
import com.portly.backend.dto.WorkExperienceDto;
import com.portly.backend.dto.input.*;
import com.portly.backend.dto.output.PortfolioDto;
import com.portly.backend.dto.output.PortfolioInEditModeDto;
import com.portly.backend.entities.Portfolio;
import com.portly.backend.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface PortfolioService {

    PortfolioDto getPortFolio(String username);

    Portfolio getPortfolioByUser(User user);

    WorkExperienceDto editWorkExperienceSection(WorkExperienceDtoInput workExperienceDto);

    SkillDto editSkillSection(SkillDtoInput skillDto);

    ContactDto editContactSection(ContactDtoInput contactDto);

    ProjectSectionDto editProjectSection(ProjectSectionDtoInput projectSectionDto);

    AppBarDto editAppBarSection(AppBarDtoInput appBarDto);

    HeaderDto editHeaderSection(HeaderDtoInput headerDto);

    AchievementDto editAchievementSection(AchievementDtoInput achievementDto);

    void createPortfolio(User user);

    Portfolio savePortfolio(Portfolio portfolio);

    Portfolio editIsPublic(Boolean isPublic);

    PortfolioInEditModeDto getCurrentPortFolio();
}
