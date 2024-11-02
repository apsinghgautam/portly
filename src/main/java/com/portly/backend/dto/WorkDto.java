package com.portly.backend.dto;

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
public class WorkDto {

    @NotNull(message = "Company name must not be null")
    @NotBlank(message = "Company name must not be blank")
    @Size(max = 100, message = "Company name must not exceed 100 characters")
    private String companyName;

    @NotNull(message = "Duration must not be null")
    @NotBlank(message = "Duration must not be blank")
    @Pattern(regexp = "^(\\d+\\s*(months?|years?))$", message = "Duration must be in the format 'X months' or 'X years'")
    private String duration;

    @NotNull(message = "Job description must not be null")
    @NotBlank(message = "Job description must not be blank")
    @Size(max = 500, message = "Job description must not exceed 500 characters")
    private String jobDescription;

    @NotNull(message = "Job role must not be null")
    @NotBlank(message = "Job role must not be blank")
    @Size(max = 100, message = "Job role must not exceed 100 characters")
    private String jobRole;

}
