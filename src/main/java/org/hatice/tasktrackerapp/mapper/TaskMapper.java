package org.hatice.tasktrackerapp.mapper;

import org.hatice.tasktrackerapp.dto.request.TaskRequestDto;
import org.hatice.tasktrackerapp.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
		ReportingPolicy.IGNORE)
public interface TaskMapper {
	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
	
	@Mapping(target = "status", constant = "PENDING")
	Task fromTaskRequestDto(TaskRequestDto taskRequestDto);
}