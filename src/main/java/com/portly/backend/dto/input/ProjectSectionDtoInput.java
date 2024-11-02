package com.portly.backend.dto.input;

import com.portly.backend.dto.ProjectDto;
import jakarta.validation.Valid;
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
public class ProjectSectionDtoInput {

    @NotNull(message = "isActive must not be null")
    private Boolean isActive;

    @NotNull(message = "Background color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String backgroundColour;

    @NotNull(message = "Text color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String textColour;

    @NotNull(message = "Project background color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid project background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String projectBackgroundColour;

    @NotNull(message = "Project text color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid project text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String projectTextColour;

    @NotNull(message = "Project button background color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid project button background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String projectButtonBackgroundColour;

    @NotNull(message = "Project button text color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid project button text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String projectButtonTextColour;

    @NotNull(message = "Projects must not be null")
    @Size(min = 1, message = "There must be at least one project")
    private List<@Valid ProjectDto> projects; // Assuming ProjectDto is another class you have defined


}
