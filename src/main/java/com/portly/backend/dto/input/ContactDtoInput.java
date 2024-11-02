package com.portly.backend.dto.input;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDtoInput {

    @NotNull(message = "isActive must not be null")
    private Boolean isActive;

    @NotNull(message = "Email must not be null")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid mobile number. It should contain only digits and can optionally start with a '+' sign.")
    private String mobile;

    @NotNull(message = "Social links must not be null")
    @Size(min = 1, message = "There must be at least one social link")
    private Map<@NotBlank(message = "Platform name must not be blank") String, @NotBlank(message = "URL must not be blank") String> socialLinks;

    @NotNull(message = "Background color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String backgroundColour;

    @NotNull(message = "Text color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String textColour;

    @NotNull(message = "Message background color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid message background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String messageBackgroundColour;

    @NotNull(message = "Message text color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid message text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String messageTextColour;

    @NotNull(message = "Button background color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid button background color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String buttonBackgroundColour;

    @NotNull(message = "Button text color must not be null")
    @Pattern(regexp = "^0x([A-Fa-f0-9]{8})$", message = "Invalid button text color. It should be in ARGB hex code format like 0xFFFFFFFF.")
    private String buttonTextColour;


}
