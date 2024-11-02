package com.portly.backend.dto.input;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageDto {

    @NotNull(message = "Recipient (username) must not be null")
    @NotBlank(message = "Recipient (username) must not be blank")
    @Size(min = 3, max = 30, message = "Recipient (username) must be between 3 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Recipient (username) can only contain letters and digits")
    private String recipient;

    @NotNull(message = "Email must not be null")
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Message must not be null")
    @NotBlank(message = "Message must not be blank")
    @Size(min = 1, max = 500, message = "Message must be between 1 and 500 characters")
    private String message;

}
