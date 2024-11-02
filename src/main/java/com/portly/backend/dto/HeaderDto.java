package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeaderDto {

    private String heroDescription;
    private String image;
    private String cv;
    private String backgroundColour;
    private String textColour;
    private String highlightColour;
    private String name;

}
