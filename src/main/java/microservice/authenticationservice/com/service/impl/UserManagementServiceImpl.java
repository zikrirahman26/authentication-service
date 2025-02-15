package microservice.authenticationservice.com.service.impl;

import lombok.RequiredArgsConstructor;
import microservice.authenticationservice.com.config.ApplicationConfiguration;
import microservice.authenticationservice.com.dto.RegistrationRequest;
import microservice.authenticationservice.com.dto.UserManagementResponse;
import microservice.authenticationservice.com.entity.UserManagement;
import microservice.authenticationservice.com.repository.UserManagementRepository;
import microservice.authenticationservice.com.service.UserManagementService;
import microservice.authenticationservice.com.utils.ValidationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserManagementRepository userManagementRepository;
    private final ValidationRequest validationRequest;
    private final ApplicationConfiguration applicationConfiguration;

    @Override
    public UserManagementResponse userRegistration(RegistrationRequest registrationRequest) {

        validationRequest.validationRequest(registrationRequest);

        if (userManagementRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already in use");
        }

        if (userManagementRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use");
        }

        String encodePassword = applicationConfiguration.passwordEncoder().encode(registrationRequest.getPassword());

        UserManagement userManagement = getUserManagement(registrationRequest, encodePassword);

        userManagementRepository.save(userManagement);

        return userResponse(userManagement);
    }

    private static UserManagement getUserManagement(RegistrationRequest registrationRequest, String encodePassword) {
        UserManagement userManagement = new UserManagement();
        userManagement.setUsername(registrationRequest.getUsername());
        userManagement.setEmail(registrationRequest.getEmail());
        userManagement.setPassword(encodePassword);
        userManagement.setFullName(registrationRequest.getFullName());
        userManagement.setPhoneNumber(registrationRequest.getPhoneNumber());
        userManagement.setRoleType(registrationRequest.getRoleType());
        userManagement.setStatus("active");
        return userManagement;
    }

    private UserManagementResponse userResponse(UserManagement userManagement) {
        return UserManagementResponse.builder()
                .username(userManagement.getUsername())
                .email(userManagement.getEmail())
                .fullName(userManagement.getFullName())
                .phoneNumber(userManagement.getPhoneNumber())
                .roleType(userManagement.getRoleType())
                .status(userManagement.getStatus())
                .build();
    }
}
