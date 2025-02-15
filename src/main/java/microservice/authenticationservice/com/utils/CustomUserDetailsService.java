package microservice.authenticationservice.com.utils;

import microservice.authenticationservice.com.entity.UserManagement;
import microservice.authenticationservice.com.repository.UserManagementRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserManagementRepository userManagementRepository;

    public CustomUserDetailsService(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserManagement userManagement = userManagementRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));

        return User.builder()
                .username(userManagement.getUsername())
                .password(userManagement.getPassword())
                .build();
    }
}
