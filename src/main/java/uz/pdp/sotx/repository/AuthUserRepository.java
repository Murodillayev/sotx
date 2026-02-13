package uz.pdp.sotx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sotx.model.AuthUser;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByPhone(String phone);
}
