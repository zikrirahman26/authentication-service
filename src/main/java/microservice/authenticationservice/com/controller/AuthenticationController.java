package microservice.authenticationservice.com.controller;

import lombok.RequiredArgsConstructor;
import microservice.authenticationservice.com.dto.*;
import microservice.authenticationservice.com.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api-auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authenticationService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<TokenResponse>builder()
                .data(tokenResponse)
                .message("User login successful")
                .build());
    }
}
