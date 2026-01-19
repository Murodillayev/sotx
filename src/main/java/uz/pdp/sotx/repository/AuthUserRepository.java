package uz.pdp.sotx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sotx.model.entity.AuthUser;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);
}
