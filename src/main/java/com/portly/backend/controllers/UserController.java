package com.portly.backend.controllers;

import com.portly.backend.dto.*;
import com.portly.backend.dto.input.*;
import com.portly.backend.dto.output.PortfolioInEditModeDto;
import com.portly.backend.entities.Portfolio;
import com.portly.backend.services.SessionService;
import com.portly.backend.services.impls.PortfolioServiceImpl;
import com.portly.backend.services.impls.SessionServiceImpl;
import com.portly.backend.services.impls.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin( allowedHeaders = {"*"})
public class UserController {

    private final UserServiceImpl userService;
    private final PortfolioServiceImpl portfolioService;
    private final SessionServiceImpl sessionService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfile(){
        return ResponseEntity.ok(userService.getMyProfile());
    }

    @PutMapping("/edit/name")
    public ResponseEntity<String> editName(@RequestBody EditNameDto editNameDto){
        return ResponseEntity.ok(userService.editName(editNameDto));
    }

    @PutMapping("/edit/password")
    public ResponseEntity<String> editPassword(@RequestBody EditPasswordDto editPasswordDto){
        return ResponseEntity.ok(userService.editPassword(editPasswordDto));
    }
    @PutMapping("/logout")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok(sessionService.logout());
    }

    @PutMapping("/edit/achievement")
    public ResponseEntity<AchievementDto> editAchievementSection(@RequestBody AchievementDtoInput achievementDto){
        return ResponseEntity.ok(portfolioService.editAchievementSection(achievementDto));
    }

    @PutMapping("/edit/header")
    public ResponseEntity<HeaderDto> editHeaderSection(@RequestBody HeaderDtoInput headerDto){
        return ResponseEntity.ok(portfolioService.editHeaderSection(headerDto));
    }

    @PutMapping("/edit/appBar")
    public ResponseEntity<AppBarDto> editAppBarSection(@RequestBody AppBarDtoInput appBarDto){
        return ResponseEntity.ok(portfolioService.editAppBarSection(appBarDto));
    }

    @PutMapping("/edit/project")
    public ResponseEntity<ProjectSectionDto> editProjectSection(@RequestBody ProjectSectionDtoInput projectSectionDto){
        return ResponseEntity.ok(portfolioService.editProjectSection(projectSectionDto));
    }

    @PutMapping("/edit/contact")
    public ResponseEntity<ContactDto> editContactSection(@RequestBody ContactDtoInput contactDto){
        return ResponseEntity.ok(portfolioService.editContactSection(contactDto));
    }

    @PutMapping("/edit/skill")
    public ResponseEntity<SkillDto> editSkillSection(@RequestBody SkillDtoInput skillDto){
        return ResponseEntity.ok(portfolioService.editSkillSection(skillDto));
    }

    @PutMapping("/edit/workExperience")
    public ResponseEntity<WorkExperienceDto> editWorkExperienceSection(@RequestBody WorkExperienceDtoInput workExperienceDto){
        return ResponseEntity.ok(portfolioService.editWorkExperienceSection(workExperienceDto));
    }

    @PutMapping("/edit/isPublic")
    public ResponseEntity<Portfolio> editIsPublicPublic(@RequestBody IsPublicDto isPublic){
        return ResponseEntity.ok(portfolioService.editIsPublic(isPublic.getIsPublic()));
    }

    @GetMapping("/portfolio")
    public ResponseEntity<PortfolioInEditModeDto> getPortfolio(){
        return ResponseEntity.ok(portfolioService.getCurrentPortFolio());
    }

}
