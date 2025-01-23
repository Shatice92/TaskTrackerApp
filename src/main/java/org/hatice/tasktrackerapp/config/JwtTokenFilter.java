package org.hatice.tasktrackerapp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hatice.tasktrackerapp.util.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtManager jwtManager;
	@Autowired
	private JwtUserDetails jwtUserDetails;
	
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		String path = request.getServletPath();
//		return path.equals("/v1/dev/users/register") || path.equals("/v1/dev/users/login") || path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs");
//	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("JwtTokenFilter doFilterInternal çalıştı...");
		String authorizationHeader = request.getHeader("Authorization");
		log.warn("Gelen Token: " + authorizationHeader);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			log.warn("Gelen Substring Yapılmış Token: " + token);
			Optional<Long> optionalUserId = jwtManager.validateToken(token);
			log.warn("Tokendan çıkan userId: " + optionalUserId.get());
			
			if (optionalUserId.isPresent()) {
				UserDetails userDetails = jwtUserDetails.loadUserById(optionalUserId.get());
				
				UsernamePasswordAuthenticationToken upaToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(upaToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
	}
}