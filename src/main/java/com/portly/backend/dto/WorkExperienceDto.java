package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkExperienceDto {

    private String backgroundColour;
    private String textColour;
    private String cardBackgroundColour;
    private String cardTextColour;
    private List<WorkDto> workList;

}
