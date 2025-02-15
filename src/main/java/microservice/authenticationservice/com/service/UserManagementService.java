package microservice.authenticationservice.com.service;

import microservice.authenticationservice.com.dto.RegistrationRequest;
import microservice.authenticationservice.com.dto.UserManagementResponse;

public interface UserManagementService {

    UserManagementResponse userRegistration(RegistrationRequest registrationRequest);
}
