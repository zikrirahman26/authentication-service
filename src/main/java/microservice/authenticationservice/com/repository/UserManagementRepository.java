package microservice.authenticationservice.com.repository;

import microservice.authenticationservice.com.entity.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserManagementRepository extends JpaRepository<UserManagement, Long> {

    Optional<UserManagement> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
