package org.hatice.tasktrackerapp.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hatice.tasktrackerapp.dto.request.UserLoginRequestDto;
import org.hatice.tasktrackerapp.dto.request.UserRequestDto;
import org.hatice.tasktrackerapp.dto.request.UserUpdateDto;
import org.hatice.tasktrackerapp.entity.User;
import org.hatice.tasktrackerapp.exception.ErrorType;
import org.hatice.tasktrackerapp.exception.TaskTrackerException;
import org.hatice.tasktrackerapp.mapper.UserMapper;
import org.hatice.tasktrackerapp.repository.UsersRepository;
import org.hatice.tasktrackerapp.util.JwtManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UsersService {
	private final UsersRepository usersRepository;
	private final JwtManager jwtManager;
	private AuthenticationManager authenticationManager;
	
	public List<User> findAll() {
		List<User> allUsers = usersRepository.findAll();
		if (allUsers.isEmpty()) {
			throw new TaskTrackerException(ErrorType.USERLIST_IS_EMPTY);
		}
		return allUsers;
	}
	
	
	public User getUserById(Long id) {
		return usersRepository.findById(id).orElseThrow(() -> new TaskTrackerException(ErrorType.USER_NOTFOUND));
	}
	
	public void register(UserRequestDto dto) {
		User user = UserMapper.INSTANCE.fromUserRequestDto(dto);
		usersRepository.save(user);
	}
	
	public void deleteById(Long id) {
		usersRepository.findById(id).ifPresentOrElse(usersRepository::delete, () -> {
			throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
		});
	}
	
	
	public void update(Long id, UserUpdateDto dto) {
		User user = usersRepository.findById(id).orElseThrow(() -> new TaskTrackerException(ErrorType.USER_NOTFOUND));
		user.setEmail(dto.email());
		usersRepository.save(user);
	}
	
	public List<User> findAllByUsername(String username) {
		List<User> allByUsernameContainingIgnoreCase = usersRepository.findAllByUsernameContainingIgnoreCase(username);
		if (allByUsernameContainingIgnoreCase.isEmpty()) throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
		return allByUsernameContainingIgnoreCase;
	}
	
	public List<User> findByEmail(String email) {
		List<User> allByEmailContainingIgnoreCase = usersRepository.findAllByEmailContainingIgnoreCase(email);
		if (allByEmailContainingIgnoreCase.isEmpty()) throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
		return allByEmailContainingIgnoreCase;
	}
	
	public User findUserByUsername(String username) {
		User userByUsername = usersRepository.findByUsername(username);
		if (userByUsername == null) throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
		return userByUsername;
	}
	
	
	public String doLogin(UserLoginRequestDto dto) {
		
		Optional<User> userOptional = usersRepository.findOptionalByUsernameAndPassword(dto.username(),
		                                                                                dto.password());
		if (userOptional.isEmpty()) throw new TaskTrackerException(ErrorType.USER_NOTFOUND);
			Authentication authenticate =
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(),
					                                                                           dto.password()));
			SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = jwtManager.createToken(userOptional.get().getId());
		return token;
	}
}