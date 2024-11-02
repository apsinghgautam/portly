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
public class AchievementDtoInput {

    @NotNull(message = "isActive must not be null")
    private Boolean isActive;

    @NotNull(message = "Achievements list must not be null")
    @Size(min = 1, message = "There must be at least one achievement")
    private List<@NotBlank(message = "Achievement must not be blank") String> achievements;

    @NotNull(message = "Background color list must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String backgroundColour;

    @NotNull(message = "Text color list must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String textColour;

    @NotNull(message = "Title Background Color list must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid title background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String titleBackgroundColour;

    @NotNull(message = "Title text color list must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid title text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String titleTextColour;

}
