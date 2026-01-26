package uz.pdp.sotx.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import uz.pdp.sotx.config.jwt.JwtUtils;
import uz.pdp.sotx.mapper.AuthUserMapper;
import uz.pdp.sotx.model.dto.LoginRequest;
import uz.pdp.sotx.model.dto.LoginResponse;
import uz.pdp.sotx.model.dto.RegisterDto;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;
import uz.pdp.sotx.validator.AuthUserValidator;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserValidator validator;

    public void register(RegisterDto dto) {
        AuthUser authUser = mapper.fromDto(dto);
        repository.save(authUser);
    }

    public LoginResponse login(LoginRequest request) {
        AuthUser authUser = validator.existsAndGetByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        String accessToken = jwtUtils.generateAccessToken(request.getUsername(), Map.of(
                "role", authUser.getRole()
        ));

        String refreshToken = jwtUtils.generateRefreshToken(request.getUsername());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public  LoginResponse refreshToken(String token) {
        Claims claims = jwtUtils.validateToken(token);

        AuthUser authUser = validator.existsAndGetByUsername(claims.getSubject());

        String accessToken = jwtUtils.generateAccessToken(claims.getSubject(), Map.of(
                "role", authUser.getRole()
        ));
//        String refreshToken = jwtUtils.generateRefreshToken(claims.getSubject());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(token)
                .build();
    }
}
