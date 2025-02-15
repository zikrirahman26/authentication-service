package microservice.authenticationservice.com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.authenticationservice.com.model.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserManagementResponse {

    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private RoleType roleType;
    private String status;
}
