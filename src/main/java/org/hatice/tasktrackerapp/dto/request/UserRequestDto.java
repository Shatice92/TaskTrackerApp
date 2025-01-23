package org.hatice.tasktrackerapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hatice.tasktrackerapp.enums.UserRole;

public record UserRequestDto(@NotBlank(message = "username cannot be empty.") String username,
                             @Email(message = "email should be valid") String email, @NotBlank String password) {
}