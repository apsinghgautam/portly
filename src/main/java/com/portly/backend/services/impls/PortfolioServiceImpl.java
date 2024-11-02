package com.portly.backend.services.impls;

import com.portly.backend.dto.*;
import com.portly.backend.dto.input.*;
import com.portly.backend.dto.output.PortfolioDto;
import com.portly.backend.dto.output.PortfolioInEditModeDto;
import com.portly.backend.entities.*;
import com.portly.backend.entities.enums.Section;
import com.portly.backend.exceptions.ResourceNotFoundException;
import com.portly.backend.repositories.PortfolioRepository;
import com.portly.backend.services.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.portly.backend.entities.enums.Section.*;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final ModelMapper modelMapper;
    private final UserServiceImpl userService;
    private final AchievementSectionServiceImpl achievementSectionService;
    private final AppBarSectionServiceImpl appBarSectionService;
    private final ContactSectionServiceImpl contactSectionService;
    private final HeaderSectionServiceImpl headerSectionService;
    private final ProjectSectionServiceImpl projectSectionService;
    private final SkillSectionServiceImpl skillSectionService;
    private final WorkExperienceSectionServiceImpl workExperienceSectionService;
    private final WorkServiceImpl workService;
    private final ProjectServiceImpl projectService;

    @Override
    public PortfolioDto getPortFolio(String username) {
        User user = userService.getUserByUri(username);
        if(user == null) throw new RuntimeException("No Portfolio is found with username: " +username);
        Portfolio portfolio = getPortfolioByUser(user);
        if(!portfolio.getIsPublic()) throw new ResourceNotFoundException("Portfolio is not found.");
        return modelMapper.map(portfolio, PortfolioDto.class);

    }

    @Override
    public Portfolio getPortfolioByUser(User user) {
        return portfolioRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("Portfolio is not found with username " + user.getUsername())
        );
    }

    @Override
    @Transactional
    public WorkExperienceDto editWorkExperienceSection(WorkExperienceDtoInput workExperienceDto) {
        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);
        WorkExperienceSection workExperienceSection = portfolio.getWorkExperienceSection();
        List<Work> workList = workExperienceSection.getWorkList();
        List<WorkDto> workListDto = workExperienceDto.getWorkList();

        int workListSize = workList.size();
        int workListDtoSize = workListDto.size();

        // Updating existing works
        for (int i = 0; i < Math.min(workListSize, workListDtoSize); i++) {
            Work workToUpdate = modelMapper.map(workListDto.get(i), Work.class);
            workToUpdate.setId(workList.get(i).getId());  // Maintain existing ID
            workList.set(i, workService.updateWork(workToUpdate));
        }

        // Adding new works if DTO list is larger
        if (workListSize < workListDtoSize) {
            for (int i = workListSize; i < workListDtoSize; i++) {
                workList.add(workService.addWork(modelMapper.map(workListDto.get(i), Work.class)));
            }
        }
        // Removing extra works if original list is larger
        else if (workListSize > workListDtoSize) {
            for (int i = workListDtoSize; i < workListSize; i++) {
                workService.deleteWork(workList.get(i));
            }
            workList.subList(workListDtoSize, workListSize).clear();
        }

        // Updating the WorkExperienceSection
        WorkExperienceSection updatedWorkExperienceSection = WorkExperienceSection.builder()
                .id(workExperienceSection.getId())
                .workList(workList)
                .textColour(workExperienceDto.getTextColour())
                .backgroundColour(workExperienceDto.getBackgroundColour())
                .cardBackgroundColour(workExperienceDto.getCardBackgroundColour())
                .cardTextColour(workExperienceDto.getCardTextColour())
                .build();

        Map<Section, Boolean> activeSection = portfolio.getActiveSection();
        activeSection.replace(WORK_EXPERIENCE,workExperienceDto.getIsActive());
        portfolio.setActiveSection(activeSection);
        savePortfolio(portfolio);
        return modelMapper.map(workExperienceSectionService.updateWorkExperienceSection(updatedWorkExperienceSection), WorkExperienceDto.class);
    }


    @Override
    @Transactional
    public SkillDto editSkillSection(SkillDtoInput skillDto) {

        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);

        SkillSection skillSection = modelMapper.map(skillDto, SkillSection.class);

        skillSection.setId(portfolio.getSkillSection().getId());
        portfolio.setSkillSection(
                skillSectionService.updateSkillSection(skillSection)
        );

        Map<Section, Boolean> activeSection = portfolio.getActiveSection();
        activeSection.replace(SKILLS,skillDto.getIsActive());
        portfolio.setActiveSection(activeSection);
        savePortfolio(portfolio);
        return modelMapper.map(portfolio.getSkillSection(), SkillDto.class);

    }

    @Override
    @Transactional
    public ContactDto editContactSection(ContactDtoInput contactDto) {

        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);

        ContactSection contactSection = modelMapper.map(contactDto,ContactSection.class);
        contactSection.setId(portfolio.getContactSection().getId());
        portfolio.setContactSection(
                contactSectionService.updateContactSection(contactSection)
        );

        Map<Section, Boolean> activeSection = portfolio.getActiveSection();
        activeSection.replace(CONTACT,contactDto.getIsActive());
        portfolio.setActiveSection(activeSection);
        savePortfolio(portfolio);
        return modelMapper.map(portfolio.getContactSection(),ContactDto.class);
    }

    @Override
    @Transactional
    public ProjectSectionDto editProjectSection(ProjectSectionDtoInput projectSectionDto) {
        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);
        ProjectSection projectSection = portfolio.getProjectSection();
        List<Project> projects = projectSection.getProjects();
        List<ProjectDto> projectsDto = projectSectionDto.getProjects();

        int projectListSize = projects.size();
        int projectListDtoSize = projectsDto.size();

        // Updating existing projects
        for (int i = 0; i < Math.min(projectListSize, projectListDtoSize); i++) {
            Project projectToUpdate = modelMapper.map(projectsDto.get(i), Project.class);
            projectToUpdate.setId(projects.get(i).getId());  // Preserve the original ID
            projects.set(i, projectService.updateProject(projectToUpdate));
        }

        // Adding new projects if DTO list is larger
        if (projectListSize < projectListDtoSize) {
            for (int i = projectListSize; i < projectListDtoSize; i++) {
                projects.add(projectService.addProject(modelMapper.map(projectsDto.get(i), Project.class)));
            }
        }
        // Removing extra projects if original list is larger
        else if (projectListSize > projectListDtoSize) {
            for (int i = projectListDtoSize; i < projectListSize; i++) {
                projectService.deleteProject(projects.get(i));
            }
            projects.subList(projectListDtoSize, projectListSize).clear();
        }

        // Update the ProjectSection
        ProjectSection updatedProjectSection = ProjectSection.builder()
                .id(projectSection.getId())
                .projects(projects)
                .textColour(projectSectionDto.getTextColour())
                .backgroundColour(projectSectionDto.getBackgroundColour())
                .projectButtonBackgroundColour(projectSectionDto.getProjectButtonBackgroundColour())
                .projectButtonTextColour(projectSectionDto.getProjectButtonTextColour())
                .projectBackgroundColour(projectSectionDto.getProjectBackgroundColour())
                .projectTextColour(projectSectionDto.getProjectTextColour())
                .build();

        Map<Section, Boolean> activeSection = portfolio.getActiveSection();
        activeSection.replace(PROJECT_SECTION,projectSectionDto.getIsActive());
        portfolio.setActiveSection(activeSection);
        savePortfolio(portfolio);

        return modelMapper.map(projectSectionService.updateProjectSection(updatedProjectSection), ProjectSectionDto.class);
    }

    @Override
    @Transactional
    public AppBarDto editAppBarSection(AppBarDtoInput appBarDto) {

        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);

        AppBarSection appBarSection = modelMapper.map(appBarDto, AppBarSection.class);

        appBarSection.setId(portfolio.getAppBarSection().getId());
        portfolio.setAppBarSection(
                appBarSectionService.updateAppBarSection(appBarSection)
        );
        Map<Section, Boolean> activeSection = portfolio.getActiveSection();
        activeSection.replace(APP_BAR,appBarDto.getIsActive());
        portfolio.setActiveSection(activeSection);
        savePortfolio(portfolio);
        return modelMapper.map(portfolio.getAppBarSection(),AppBarDto.class);
    }

    @Override
    @Transactional
    public HeaderDto editHeaderSection(HeaderDtoInput headerDto) {

        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);

        HeaderSection headerSection = modelMapper.map(headerDto, HeaderSection.class);
        headerSection.setId(portfolio.getHeaderSection().getId());
        portfolio.setHeaderSection(
                headerSectionService.updateHeaderSection(headerSection)
        );

        Map<Section, Boolean> activeSection = portfolio.getActiveSection();
        activeSection.replace(HEADER,headerDto.getIsActive());
        portfolio.setActiveSection(activeSection);
        savePortfolio(portfolio);

        return modelMapper.map(portfolio.getHeaderSection(), HeaderDto.class);
    }

    @Override
    @Transactional
    public AchievementDto editAchievementSection(AchievementDtoInput achievementDto) {

        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);
        AchievementSection achievementSection =  modelMapper.map(achievementDto, AchievementSection.class);
        achievementSection.setId(portfolio.getAchievementSection().getId());

        portfolio.setAchievementSection(
                achievementSectionService.updateAchievementSection(achievementSection)
        );

        Map<Section, Boolean> activeSection = portfolio.getActiveSection();
        activeSection.replace(ACHIEVEMENT,achievementDto.getIsActive());
        portfolio.setActiveSection(activeSection);
        savePortfolio(portfolio);
        return modelMapper.map(portfolio.getAchievementSection(), AchievementDto.class);

    }

    @Override
    @Transactional
    public void createPortfolio(User user) {

        Portfolio portfolio = Portfolio.builder()
                .user(user)
                .isPublic(false)
                .activeSection(Map.of(
                        Section.ACHIEVEMENT,true,
                        Section.APP_BAR,true,
                        Section.CONTACT,true,
                        Section.HEADER,true,
                        Section.PROJECT_SECTION,true,
                        Section.SKILLS,true,
                        WORK_EXPERIENCE,true
                ))
                .achievementSection(achievementSectionService.createAchievementSection())
                .appBarSection(appBarSectionService.createAppBarSection())
                .contactSection(contactSectionService.createContactSection())
                .headerSection(headerSectionService.createHeaderSection())
                .projectSection(projectSectionService.createProjectSection())
                .skillSection(skillSectionService.createSkillSection())
                .workExperienceSection(workExperienceSectionService.createWorkExperienceSection())
                .build();
        portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio savePortfolio(Portfolio portfolio) {
         return portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio editIsPublic(Boolean isPublic) {

        User user = userService.getCurrentUser();
        Portfolio portfolio = getPortfolioByUser(user);

        portfolio.setIsPublic(isPublic);
        return savePortfolio(portfolio);
    }

    @Override
    public PortfolioInEditModeDto getCurrentPortFolio() {
        User user = userService.getCurrentUser();
        return modelMapper.map(getPortfolioByUser(user), PortfolioInEditModeDto.class);
    }


}
