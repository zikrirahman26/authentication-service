package microservice.authenticationservice.com.controller;

import lombok.RequiredArgsConstructor;
import microservice.authenticationservice.com.dto.*;
import microservice.authenticationservice.com.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api-users")
public class UserManagementController {

    private final UserManagementService userManagementService;

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<UserManagementResponse>> userRegistration(@RequestBody RegistrationRequest registrationRequest) {
        UserManagementResponse userManagementResponse = userManagementService.userRegistration(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<UserManagementResponse>builder()
                        .data(userManagementResponse)
                        .message("User registration successfully")
                .build());
    }
}
