package microservice.authenticationservice.com.service.impl;

import lombok.RequiredArgsConstructor;
import microservice.authenticationservice.com.dto.LoginRequest;
import microservice.authenticationservice.com.dto.TokenResponse;
import microservice.authenticationservice.com.entity.UserManagement;
import microservice.authenticationservice.com.repository.UserManagementRepository;
import microservice.authenticationservice.com.security.JwtServiceGenerator;
import microservice.authenticationservice.com.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtServiceGenerator jwtServiceGenerator;
    private final UserManagementRepository userManagementRepository;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        UserManagement userManagement = userManagementRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + loginRequest.getUsername() + " not found"));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect");
        }

        String token = jwtServiceGenerator.generateToken(userManagement);

        return TokenResponse.builder()
                .token(token)
                .build();
    }
}
