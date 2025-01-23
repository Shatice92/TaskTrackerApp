package org.hatice.tasktrackerapp.repository;


import org.hatice.tasktrackerapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findAllByUserId(Long userId);
	
	Role getRoleByUserId(Long userId);
	
}