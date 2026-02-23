package uz.pdp.sotx;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsernameAndPassword(String username, String password);

    Optional<AuthUser> findByUsername(String username);
}
