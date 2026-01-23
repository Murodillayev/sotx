package uz.pdp.sotx.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;

@Component
@RequiredArgsConstructor
public class AuthUserValidator {
    private final AuthUserRepository repository;

    public AuthUser existsAndGetByUsername(String username) {
        if (username == null) {
            throw new UsernameNotFoundException("Username is null");
        }
        return repository.findByUsernameAndDeletedFalse(username).orElseThrow(
                () -> new UsernameNotFoundException("Bad credentials")
        );
    }
}
