package org.hatice.tasktrackerapp.dto.request;

import jakarta.validation.constraints.Email;

public record UserUpdateDto(@Email(message = "email should be valid") String email) {
}