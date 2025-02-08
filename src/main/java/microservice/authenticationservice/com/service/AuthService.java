package microservice.authenticationservice.com.service;

import microservice.authenticationservice.com.dto.AuthLoginRequest;
import microservice.authenticationservice.com.dto.AuthTokenResponse;

public interface AuthService {

    AuthTokenResponse login(AuthLoginRequest authLoginRequest);
}
