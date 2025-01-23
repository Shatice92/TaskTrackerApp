package org.hatice.tasktrackerapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.tasktrackerapp.enums.UserRole;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;
	@Column(nullable = false)
	private Long userId;
	
	
}