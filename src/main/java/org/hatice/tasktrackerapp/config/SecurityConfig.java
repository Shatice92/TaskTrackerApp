package org.hatice.tasktrackerapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("swagger-ui/**", "/v3/api-docs/**", "/v1/dev/users/register", "/v1/dev/users/login")
			   .permitAll().requestMatchers("/admin/**", "/v1/dev/users/list").hasAuthority("ADMIN").anyRequest()
			   .authenticated();
		});
		http.csrf(AbstractHttpConfigurer::disable);
		http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public JwtTokenFilter getJwtTokenFilter() {
		return new JwtTokenFilter();
	}
}