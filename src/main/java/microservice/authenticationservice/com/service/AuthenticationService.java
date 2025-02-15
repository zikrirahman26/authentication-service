package microservice.authenticationservice.com.service;

import microservice.authenticationservice.com.dto.LoginRequest;
import microservice.authenticationservice.com.dto.TokenResponse;

public interface AuthenticationService {

    TokenResponse login(LoginRequest loginRequest);
}
