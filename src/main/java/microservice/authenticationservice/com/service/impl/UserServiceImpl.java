package microservice.authenticationservice.com.service.impl;

import microservice.authenticationservice.com.config.ApplicationConfig;
import microservice.authenticationservice.com.dto.UserRegistrationRequest;
import microservice.authenticationservice.com.dto.UserResponse;
import microservice.authenticationservice.com.entity.UserEntity;
import microservice.authenticationservice.com.repository.UserRepository;
import microservice.authenticationservice.com.service.UserService;
import microservice.authenticationservice.com.utils.RequestValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RequestValidation requestValidation;
    private final ApplicationConfig applicationConfig;

    public UserServiceImpl(UserRepository userRepository, RequestValidation requestValidation, ApplicationConfig applicationConfig) {
        this.userRepository = userRepository;
        this.requestValidation = requestValidation;
        this.applicationConfig = applicationConfig;
    }

    @Override
    public UserResponse userRegistration(UserRegistrationRequest userRegistrationRequest) {

        requestValidation.validationRequest(userRegistrationRequest);

        if (userRepository.existsByUsername(userRegistrationRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already in use");
        }

        String encodePassword = applicationConfig.passwordEncoder().encode(userRegistrationRequest.getPassword());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRegistrationRequest.getUsername());
        userEntity.setPassword(encodePassword);
        userEntity.setFirstName(userRegistrationRequest.getFirstName());
        userEntity.setLastName(userRegistrationRequest.getLastName());
        userEntity.setEmail(userRegistrationRequest.getEmail());
        userEntity.setPhone(userRegistrationRequest.getPhone());
        userRepository.save(userEntity);

        return userResponse(userEntity);
    }

    private UserResponse userResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .build();
    }
}
