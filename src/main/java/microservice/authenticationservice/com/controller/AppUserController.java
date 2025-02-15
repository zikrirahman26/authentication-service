package microservice.authenticationservice.com.controller;

import lombok.RequiredArgsConstructor;
import microservice.authenticationservice.com.dto.*;
import microservice.authenticationservice.com.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api-users")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<AppUserResponse>> userRegistration(@RequestBody RegistrationRequest registrationRequest) {
        AppUserResponse appUserResponse = appUserService.userRegistration(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<AppUserResponse>builder()
                        .data(appUserResponse)
                        .message("User registration successfully")
                .build());
    }
}
