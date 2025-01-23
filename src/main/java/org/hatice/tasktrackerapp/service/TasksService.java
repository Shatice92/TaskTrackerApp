package org.hatice.tasktrackerapp.service;

import lombok.RequiredArgsConstructor;

import org.hatice.tasktrackerapp.dto.request.TaskRequestDto;
import org.hatice.tasktrackerapp.entity.Task;
import org.hatice.tasktrackerapp.entity.User;
import org.hatice.tasktrackerapp.enums.TaskStatus;
import org.hatice.tasktrackerapp.exception.ErrorType;
import org.hatice.tasktrackerapp.exception.TaskTrackerException;
import org.hatice.tasktrackerapp.mapper.TaskMapper;
import org.hatice.tasktrackerapp.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TasksService {
	private final TasksRepository tasksRepository;
	private final UsersService usersService;
	
	public void createTask(TaskRequestDto dto) {
		User userById = usersService.getUserById(dto.userId());
		if (userById == null) {
			throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
		}
		Task task = TaskMapper.INSTANCE.fromTaskRequestDto(dto);
		tasksRepository.save(task);
	}
	
	
	public List<Task> getTasksByUserId(Long userId) {
		User userById = usersService.getUserById(userId);
		if (userById == null) {
			throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
		}
		return tasksRepository.findByUserId(userId);
	}
	
	public List<Task> getTasksByTaskStatus(TaskStatus status) {
		List<Task> allByStatus = tasksRepository.findAllByStatus(status);
		if (allByStatus == null) {
			throw new TaskTrackerException(ErrorType.TASK_NOTFOUND);
		}
		return allByStatus;
	}
	
}