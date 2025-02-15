package microservice.authenticationservice.com.service;

import microservice.authenticationservice.com.dto.LoginRequest;
import microservice.authenticationservice.com.dto.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginRequest loginRequest);
}
