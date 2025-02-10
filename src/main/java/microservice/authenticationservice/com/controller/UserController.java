package microservice.authenticationservice.com.controller;

import microservice.authenticationservice.com.dto.*;
import microservice.authenticationservice.com.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<UserResponse>> userRegistration(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserResponse userResponse = userService.userRegistration(userRegistrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<UserResponse>builder()
                        .data(userResponse)
                        .message("User registration successful")
                .build());
    }
}
