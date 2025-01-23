package org.hatice.tasktrackerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.hatice.tasktrackerapp.dto.request.TaskRequestDto;
import org.hatice.tasktrackerapp.dto.response.BaseResponse;
import org.hatice.tasktrackerapp.dto.response.TaskResponseDto;
import org.hatice.tasktrackerapp.entity.Task;
import org.hatice.tasktrackerapp.enums.TaskStatus;
import org.hatice.tasktrackerapp.service.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hatice.tasktrackerapp.constant.EndPoints.*;


@RestController
@RequestMapping(TASKS)
@RequiredArgsConstructor
public class TasksController {
	private final TasksService tasksService;
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Boolean>> createTask(@RequestBody @Valid TaskRequestDto dto) {
		tasksService.createTask(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().message("task created successfully").code(200)
		                                     .data(true).build());
	}
	
	
	@GetMapping(GETBYID)
	public ResponseEntity<BaseResponse<List<TaskResponseDto>>> getTasksByUserId(@PathVariable Long userId) {
		List<TaskResponseDto> list = tasksService.getTasksByUserId(userId).stream()
		                                         .map(task -> new TaskResponseDto(task.getTitle(),
		                                                                          task.getDescription(),
		                                                                          task.getDueDate(), task.getStatus()))
		                                         .toList();
		return ResponseEntity.ok(BaseResponse.<List<TaskResponseDto>>builder().message("tasks listed successfully")
		                                     .code(200).data(list).build());
	}
	
	@GetMapping(GETBYSTATUS)
	public ResponseEntity<BaseResponse<List<Task>>> getTasksByTaskStatus(@RequestParam TaskStatus status) {
		List<Task> taskList = tasksService.getTasksByTaskStatus(status);
		return ResponseEntity.ok(BaseResponse.<List<Task>>builder().message("tasks listed successfully").code(200)
		                                     .data(taskList).build());
	}
}