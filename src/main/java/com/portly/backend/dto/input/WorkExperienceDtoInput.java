package com.portly.backend.dto.input;

import com.portly.backend.dto.WorkDto;
import jakarta.validation.Valid;
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
public class WorkExperienceDtoInput {

    @NotNull(message = "isActive must not be null")
    private Boolean isActive;

    @NotNull(message = "Background color must not be null")
    @NotBlank(message = "Background color must not be blank")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})$", message = "Invalid background color format. It must be a valid hex code")
    private String backgroundColour;

    @NotNull(message = "Text color must not be null")
    @NotBlank(message = "Text color must not be blank")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})$", message = "Invalid text color format. It must be a valid hex code")
    private String textColour;

    @NotNull(message = "Card background color must not be null")
    @NotBlank(message = "Card background color must not be blank")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})$", message = "Invalid card background color format. It must be a valid hex code")
    private String cardBackgroundColour;

    @NotNull(message = "Card text color must not be null")
    @NotBlank(message = "Card text color must not be blank")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})$", message = "Invalid card text color format. It must be a valid hex code")
    private String cardTextColour;

    @NotNull(message = "Work list must not be null")
    @Size(min = 1, message = "Work list must contain at least one work entry")
    private List<@Valid WorkDto> workList;


}
