package uz.pdp.sotx.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.config.JwtUtils;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.dto.LoginRequest;
import uz.pdp.sotx.model.dto.LoginResponse;
import uz.pdp.sotx.repository.AuthUserRepository;

import java.util.Map;

@Service
public class AuthUserService {
    private final PasswordEncoder encoder;
    private final AuthUserRepository repository;
    private final JwtUtils jwtUtils;

    public AuthUserService(PasswordEncoder encoder, AuthUserRepository repository, JwtUtils jwtUtils) {
        this.encoder = encoder;
        this.repository = repository;
        this.jwtUtils = jwtUtils;
    }

    public LoginResponse login(LoginRequest request) {
        String phone = request.getPhone();
        AuthUser authUser = repository.findByPhone(phone).orElseThrow(
                () -> new BadCredentialsException("Bad credentials")
        );
        if (!encoder.matches(request.getPassword(), authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }


        String accessToken = jwtUtils.generateToken(phone, Map.of());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
