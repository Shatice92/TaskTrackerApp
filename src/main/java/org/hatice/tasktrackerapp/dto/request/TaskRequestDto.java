package org.hatice.tasktrackerapp.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

//not blank isn't valid for non-string types
public record TaskRequestDto(@NotBlank String title, @NotBlank String description, @Future @NotNull LocalDate dueDate,
                             @NotNull Long userId) {
}