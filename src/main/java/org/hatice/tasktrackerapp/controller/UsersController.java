package org.hatice.tasktrackerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.tasktrackerapp.dto.request.UserLoginRequestDto;
import org.hatice.tasktrackerapp.dto.request.UserRequestDto;
import org.hatice.tasktrackerapp.dto.request.UserUpdateDto;
import org.hatice.tasktrackerapp.dto.response.BaseResponse;
import org.hatice.tasktrackerapp.dto.response.UserResponseDto;
import org.hatice.tasktrackerapp.entity.User;
import org.hatice.tasktrackerapp.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hatice.tasktrackerapp.constant.EndPoints.*;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UsersController {
	
	private final UsersService usersService;
	
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid UserRequestDto dto) {
		usersService.register(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().message("User registered successfully").data(true)
		                                     .code(200).success(true).build());
	}
	
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<String>> login(@RequestBody @Valid  UserLoginRequestDto dto) {
		String token = usersService.doLogin(dto);
		return ResponseEntity.ok(BaseResponse.<String>builder().code(200).data(token).message("Login Successful")
		                                     .success(true).build());
	}
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<UserResponseDto>>> getAllUsers() {
		List<UserResponseDto> users =
				usersService.findAll().stream().map(user -> new UserResponseDto(user.getUsername(), user.getEmail()))
				            .toList();
		return ResponseEntity.ok(BaseResponse.<List<UserResponseDto>>builder().data(users).code(200).success(true)
		                                     .message("All users are listed successfully..").build());
	}
	
	@GetMapping(GETBYID)
	public ResponseEntity<BaseResponse<UserResponseDto>> getUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(BaseResponse.<UserResponseDto>builder()
		                                     .data(new UserResponseDto(usersService.getUserById(userId)
		                                                                           .getUsername(), usersService
				                                                               .getUserById(userId).getEmail()))
		                                     .code(200).success(true).message("User found.").build());
	}

//	@PostMapping(SAVE)
//	public ResponseEntity<BaseResponse<Boolean>> saveUser(@RequestBody @Valid UserRequestDto dto) {
//		usersService.save(dto);
//		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).code(200).success(true)
//		                                     .message("User saved successfully.").build());
//	}
	
	@DeleteMapping(DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteUser(@PathVariable Long userId) {
		usersService.deleteById(userId);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).code(200).success(true)
		                                     .message("User deleted successfully.").build());
	}
	
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<Boolean>> updateUser(@RequestBody @Valid UserUpdateDto dto,
	                                                        @PathVariable Long userId) {
		usersService.update(userId, dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).code(200).success(true)
		                                     .message("User updated successfully.").build());
	}
	
	
	@GetMapping(GETBYUSERNAME)
	public ResponseEntity<BaseResponse<List<UserResponseDto>>> getUsersByUsername(@RequestParam String username) {
		List<UserResponseDto> list = usersService.findAllByUsername(username).stream()
		                                         .map(user -> new UserResponseDto(user.getUsername(), user.getEmail()))
		                                         .toList();
		
		return ResponseEntity.ok(BaseResponse.<List<UserResponseDto>>builder().data(list).code(200).success(true)
		                                     .message("User listed by username").build());
	}
	
	@GetMapping(GETBYEMAIL)
	public ResponseEntity<BaseResponse<List<UserResponseDto>>> getUsersByEmail(@RequestParam String email) {
		List<UserResponseDto> list = usersService.findByEmail(email).stream()
		                                         .map(user -> new UserResponseDto(user.getUsername(), user.getEmail()))
		                                         .toList();
		return ResponseEntity.ok(BaseResponse.<List<UserResponseDto>>builder().data(list).code(200).success(true)
		                                     .message("User listed by email").build());
	}
	
	@GetMapping(GETUSERBYNAME)
	public User getUserByUsername(@RequestParam String username) {
		return usersService.findUserByUsername(username);
		
	}
	
	
	@GetMapping(GETPROFILEBYTOKEN)
	public ResponseEntity<BaseResponse<User>> getProfile(String token){
		return ResponseEntity.ok(
				BaseResponse.<User>builder()
				            .code(200)
				            .data(usersService.getProfile(token))
				            .message("User profile retrieved successfully.")
				            .success(true)
				            .build()
		);
	}
	
	
}