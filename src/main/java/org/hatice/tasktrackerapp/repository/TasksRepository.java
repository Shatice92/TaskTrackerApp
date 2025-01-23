package org.hatice.tasktrackerapp.repository;


import org.hatice.tasktrackerapp.entity.Task;
import org.hatice.tasktrackerapp.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Long> {
	List<Task> findByUserId(Long userId);
	
	List<Task> findByStatus(TaskStatus taskStatus);
	
	List<Task> findAllByStatus(TaskStatus status);
}