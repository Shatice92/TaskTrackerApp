package org.hatice.tasktrackerapp.dto.response;


import org.hatice.tasktrackerapp.enums.UserRole;

public record RoleResponseDto(UserRole role, Long userId) {
}