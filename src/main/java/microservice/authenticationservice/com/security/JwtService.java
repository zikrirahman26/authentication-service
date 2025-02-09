package microservice.authenticationservice.com.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    @Value("${spring.security.jwt.secret}")
    private String SECRET_KEY;

    @Value("${spring.security.jwt.expiration}")
    private long EXPIRATION_KEY;

    private Key getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_KEY))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}

