package org.hatice.tasktrackerapp.config;

import lombok.RequiredArgsConstructor;
import org.hatice.tasktrackerapp.entity.Role;
import org.hatice.tasktrackerapp.entity.User;
import org.hatice.tasktrackerapp.service.RoleService;
import org.hatice.tasktrackerapp.service.UsersService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {
	private final UsersService userService;
	private final RoleService roleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
	
	public UserDetails loadUserById(Long userId) {
		User user = userService.getUserById(userId);
		
		Role role = roleService.findRolesById(userId);
		GrantedAuthority grantedAuthorities=new SimpleGrantedAuthority(role.getRole().name());
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		//usera ait yetkilerle dolduruyoruz artık:
//		grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
		System.out.println("grantedAuthorities" + grantedAuthorities);
		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
		                                                         .password(user.getPassword()).accountExpired(false)
		                                                         .accountLocked(false).authorities(grantedAuthorities)
		                                                         .build();
	}
}