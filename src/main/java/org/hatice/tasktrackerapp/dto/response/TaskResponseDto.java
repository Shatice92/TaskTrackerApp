package org.hatice.tasktrackerapp.dto.response;

import jakarta.persistence.Column;
import org.hatice.tasktrackerapp.enums.TaskStatus;


import java.time.LocalDate;

public record TaskResponseDto(String title, String description, LocalDate dueDate, TaskStatus status) {
}