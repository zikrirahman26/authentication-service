package microservice.authenticationservice.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validate")
public class ValidateTokenController {

    @PostMapping("/token")
    public ResponseEntity<Object> validateToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
