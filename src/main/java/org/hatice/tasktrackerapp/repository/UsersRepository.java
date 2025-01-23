package org.hatice.tasktrackerapp.repository;


import org.hatice.tasktrackerapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
	
	List<User> findUsersById(Long id);
	
	List<User> findAllByUsernameContainingIgnoreCase(String username);
	
	List<User> findAllByEmailContainingIgnoreCase(String email);
	
	Optional<User> findOptionalByUsernameAndPassword(String username, String password);
	
	
	User findByUsername(String username);
}