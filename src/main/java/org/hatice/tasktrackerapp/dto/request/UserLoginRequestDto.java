package org.hatice.tasktrackerapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(@NotBlank String username, @NotBlank String password) {
}