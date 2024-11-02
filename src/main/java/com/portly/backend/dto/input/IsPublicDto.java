package com.portly.backend.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsPublicDto {

    @NotNull(message = "isActive must not be null")
    private Boolean isPublic;
}
