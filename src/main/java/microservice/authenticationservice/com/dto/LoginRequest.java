package microservice.authenticationservice.com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @Size(min = 8, max = 25, message = "Username must have a minimum length of 8 and a maximum of 25")
    @NotBlank(message = "Username must not be blank")
    private String username;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$",
            message = "Password must have at least 8 characters, including uppercase, lowercase, number, and special character (!@#$%^&*)"
    )
    private String password;
}
