package com.portly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDto {

    private String email;
    private String mobile;
    private Map<String,String> socialLinks;
    private String backgroundColour;
    private String textColour;
    private String messageBackgroundColour;
    private String messageTextColour;
    private String buttonBackgroundColour;
    private String buttonTextColour;

}
