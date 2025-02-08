package microservice.authenticationservice.com.service.impl;

import microservice.authenticationservice.com.dto.AuthLoginRequest;
import microservice.authenticationservice.com.dto.AuthTokenResponse;
import microservice.authenticationservice.com.entity.UserEntity;
import microservice.authenticationservice.com.repository.UserRepository;
import microservice.authenticationservice.com.security.JwtService;
import microservice.authenticationservice.com.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public AuthTokenResponse login(AuthLoginRequest authLoginRequest) {
        UserEntity userEntity = userRepository.findByUsername(authLoginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + authLoginRequest.getUsername() + " not found"));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequest.getUsername(), authLoginRequest.getPassword()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect");
        }

        String token = jwtService.generateToken(userEntity.getUsername());

        return AuthTokenResponse.builder()
                .token(token)
                .build();
    }
}
