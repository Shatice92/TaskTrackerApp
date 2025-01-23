package org.hatice.tasktrackerapp.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hatice.tasktrackerapp.dto.request.RoleRequestDto;
import org.hatice.tasktrackerapp.dto.response.BaseResponse;
import org.hatice.tasktrackerapp.entity.Role;
import org.hatice.tasktrackerapp.enums.UserRole;
import org.hatice.tasktrackerapp.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

import static org.hatice.tasktrackerapp.constant.EndPoints.*;


@RestController
@RequestMapping(ROLE)
@RequiredArgsConstructor
public class RoleController {
	private final RoleService roleService;
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Boolean>> assignRole(@RequestBody RoleRequestDto dto) {
		Role role = new Role();
		role.setUserId(dto.userId());
		role.setRole(UserRole.valueOf(dto.role()));
		roleService.assignRoleToUser(role);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).code(200).success(true)
		                                     .message("role saved successfully").build());
	}
	
	
	@GetMapping(GETBYID)
	public ResponseEntity<BaseResponse<List<Role>>> getRolesByUserId(@PathVariable Long userId) {
		List<Role> rolesById = roleService.findRolesById(userId);
		return ResponseEntity.ok(BaseResponse.<List<Role>>builder().data(rolesById).code(200).success(true)
		                                     .message("user'S roles retrieved").build());
	}
	
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<Role>>> getAllUsersRoles() {
		return ResponseEntity.ok(BaseResponse.<List<Role>>builder().code(200).success(true).data(roleService.findAll())
		                                     .message("All users' roles are listed..").build());
	}
}