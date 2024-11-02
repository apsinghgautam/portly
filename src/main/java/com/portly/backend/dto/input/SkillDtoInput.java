package com.portly.backend.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillDtoInput {

    @NotNull(message = "isActive must not be null")
    private Boolean isActive;

    @NotNull(message = "Advance skills list must not be null")
    @Size(min = 1, message = "There must be at least one advance skill")
    private List<@NotBlank(message = "Skill must not be blank") String> advance;

    @NotNull(message = "Intermediate skills list must not be null")
    @Size(min = 1, message = "There must be at least one intermediate skill")
    private List<@NotBlank(message = "Skill must not be blank") String> intermediate;

    @NotNull(message = "Beginner skills list must not be null")
    @Size(min = 1, message = "There must be at least one beginner skill")
    private List<@NotBlank(message = "Skill must not be blank") String> beginner;

    @NotNull(message = "Background color must not be null")
    @NotBlank(message = "OTP must not be blank")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String backgroundColour;

    @NotNull(message = "Text color must not be null")
    @NotBlank(message = "Text color must not be blank")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String textColour;

    @NotNull(message = "Tag background color must not be null")
    @NotBlank(message = "Tag background color must not be blank")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid tag background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String tagBackgroundColour;

    @NotNull(message = "Tag text color must not be null")
    @NotBlank(message = "Tag text color must not be blank")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid tag text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String tagTextColour;

}
