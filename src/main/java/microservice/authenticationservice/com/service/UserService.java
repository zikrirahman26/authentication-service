package microservice.authenticationservice.com.service;

import microservice.authenticationservice.com.dto.UserRegistrationRequest;
import microservice.authenticationservice.com.dto.UserResponse;

public interface UserService {

    UserResponse userRegistration(UserRegistrationRequest userRegistrationRequest);
}
