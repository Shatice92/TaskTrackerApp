package org.hatice.tasktrackerapp.mapper;

import org.hatice.tasktrackerapp.dto.request.UserRequestDto;
import org.hatice.tasktrackerapp.dto.request.UserUpdateDto;
import org.hatice.tasktrackerapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	
	User fromUserRequestDto(UserRequestDto dto);
	
	User fromUpdateRequestDto(UserUpdateDto dto);
}