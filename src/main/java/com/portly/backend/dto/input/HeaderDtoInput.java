package com.portly.backend.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeaderDtoInput {

    @NotNull(message = "isActive must not be null")
    private Boolean isActive;

    @NotNull(message = "Hero description must not be null")
    @NotBlank(message = "Hero description must not be blank")
    @Size(min = 10, max = 500, message = "Hero description must be between 10 and 500 characters")
    private String heroDescription;

    @NotNull(message = "Highlight color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid highlight color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String highlightColour;

    private String image;

    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    private String cv;

    @NotNull(message = "Background color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String backgroundColour;

    @NotNull(message = "Text color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String textColour;

}
