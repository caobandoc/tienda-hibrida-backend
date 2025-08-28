package com.caoc.tienda.hibridas.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.caoc.tienda.hibridas.backend.repository.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final Algorithm algorithm;
    private final long expirationMillis;
    private final String issuer;

    public JwtService(@Value("${security.jwt.secret}") String secret,
                      @Value("${security.jwt.expiration}") long expirationMillis,
                      @Value("${security.jwt.issuer}") String issuer) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.expirationMillis = expirationMillis;
        this.issuer = issuer;
    }

    public String generateToken(UserDetails user) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(issuer)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusMillis(expirationMillis)))
                .sign(algorithm);
    }

    public Instant getExpirationInstant() {
        return Instant.now().plusMillis(expirationMillis);
    }

    public String validateTokenAndGetUsername(String token) {
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = validateTokenAndGetUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
