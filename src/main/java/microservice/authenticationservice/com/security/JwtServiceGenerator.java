package microservice.authenticationservice.com.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import microservice.authenticationservice.com.entity.UserManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceGenerator {

    @Value("${spring.security.jwt.secret}")
    private String SECRET_KEY;

    @Value("${spring.security.jwt.expiration}")
    private long EXPIRATION_KEY;

    private Key getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Map<String, Object> createClaims(UserManagement userManagement) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userManagement.getUsername());
        claims.put("email", userManagement.getEmail());
        claims.put("role", userManagement.getRoleType().name());
        return claims;
    }

    public String generateToken(UserManagement userManagement) {
        return Jwts.builder()
                .setClaims(createClaims(userManagement))
                .setSubject(String.valueOf(userManagement.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_KEY))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
