package uz.pdp.sotx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.sotx.model.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
}
