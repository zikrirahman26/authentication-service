package microservice.authenticationservice.com.dto;

import jakarta.validation.constraints.Email;
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
public class UserRegistrationRequest {

    @Size
    @NotBlank(message = "Username must not be blank")
    private String username;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$",
            message = "Password must have at least 8 characters, including uppercase, lowercase, number, and special character (!@#$%^&*)"
    )
    private String password;

    @NotBlank(message = "FirstName must not be blank")
    private String firstName;
    private String lastName;

    @Email(message = "Email must be a valid format")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @Pattern(
            regexp = "^\\d{10,15}$",
            message = "Phone number must be between 10 to 15 digits and contain only numbers")
    private String phone;
}
