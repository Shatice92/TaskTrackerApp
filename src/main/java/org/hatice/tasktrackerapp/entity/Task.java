package org.hatice.tasktrackerapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.tasktrackerapp.enums.TaskStatus;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	LocalDate dueDate;
	@Enumerated(EnumType.STRING)
	TaskStatus status;
	private Long userId;
	
	
	
	
}