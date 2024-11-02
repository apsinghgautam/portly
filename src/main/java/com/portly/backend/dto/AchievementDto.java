package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AchievementDto {

    private List<String> achievements;
    private String backgroundColour;
    private String textColour;
    private String titleBackgroundColour;
    private String titleTextColour;


}
