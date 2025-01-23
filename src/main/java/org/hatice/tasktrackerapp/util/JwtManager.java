package org.hatice.tasktrackerapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {
	@Value("${tasktracker.jwt.secret-key}")
	private String SECRETKEY;
	@Value("${tasktracker.jwt.issuer}")
	private String ISSUER;
	private final Long EXTIME = 1000L * 60 * 15;
	
	
	public String createToken(Long authId) {
		Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
		Date creationDate = new Date(System.currentTimeMillis());
		Date expirationDate = new Date(System.currentTimeMillis() + EXTIME);
		String token =
				JWT.create().withAudience().withIssuer(ISSUER).withIssuedAt(creationDate).withExpiresAt(expirationDate)
				   .withClaim("authId", authId)
				   .withClaim("key", "value123").sign(algorithm);
		return token;
	}
	
	public Optional<Long> validateToken(String token) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(token);
			if (Objects.isNull(decodedJWT)) return Optional.empty();
			return Optional.of(decodedJWT.getClaim("authId").asLong());
			
		}
		catch (Exception e) {
			return Optional.empty();
		}
		
	}
}