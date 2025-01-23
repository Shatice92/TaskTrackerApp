package org.hatice.tasktrackerapp.service;

import lombok.RequiredArgsConstructor;
import org.hatice.tasktrackerapp.entity.Role;
import org.hatice.tasktrackerapp.entity.User;
import org.hatice.tasktrackerapp.exception.ErrorType;
import org.hatice.tasktrackerapp.exception.TaskTrackerException;
import org.hatice.tasktrackerapp.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository roleRepository;
	private final UsersService usersService;
	
	
	public void assignRoleToUser(Role role) {
		roleRepository.save(role);
	}
	
	public Role findRolesById(Long userId) {
		User userById = usersService.getUserById(userId);
		if (userById == null) {
			throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
		}
		return roleRepository.getRoleByUserId(userId);
	}
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
}