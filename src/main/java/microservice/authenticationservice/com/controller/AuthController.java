package microservice.authenticationservice.com.controller;

import microservice.authenticationservice.com.dto.*;
import microservice.authenticationservice.com.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthTokenResponse>> login(@RequestBody AuthLoginRequest authLoginRequest) {
        AuthTokenResponse tokenResponse = authService.login(authLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<AuthTokenResponse>builder()
                .data(tokenResponse)
                .message("User login successful")
                .build());
    }
}
