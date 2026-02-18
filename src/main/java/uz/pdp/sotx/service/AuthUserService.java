package uz.pdp.sotx.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.dto.LoginRequest;
import uz.pdp.sotx.model.dto.LoginResponse;
import uz.pdp.sotx.repository.AuthUserRepository;

@Service
public class AuthUserService {
    private final PasswordEncoder encoder;
    private final AuthUserRepository repository;

    public AuthUserService(PasswordEncoder encoder, AuthUserRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    public LoginResponse login(LoginRequest request) {
        String phone = request.getPhone();
        AuthUser authUser = repository.findByPhone(phone).orElseThrow(
                () -> new BadCredentialsException("Bad credentials")
        );
        if (!encoder.matches(request.getPassword(), authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }



        return null;
    }
}
