package uz.pdp.sotx.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.config.jwt.JwtUtils;
import uz.pdp.sotx.mapper.AuthUserMapper;
import uz.pdp.sotx.model.dto.LoginRequest;
import uz.pdp.sotx.model.dto.LoginResponse;
import uz.pdp.sotx.model.dto.RegisterDto;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;

@Service
public class AuthUserService {
    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;
    private final JwtUtils jwtUtils;


    public AuthUserService(AuthUserMapper mapper, AuthUserRepository repository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.mapper = mapper;
        this.repository = repository;
        this.jwtUtils = jwtUtils;
    }

    public void register(RegisterDto dto) {
        AuthUser authUser = mapper.fromDto(dto);
        repository.save(authUser);
    }

    public LoginResponse login(LoginRequest request) {
        AuthUser authUser = repository.findByUsernameAndDeletedFalse(request.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Bad credentials")
        );
        if (!request.getPassword().equals(authUser.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        return jwtUtils.generateToken(request.getUsername());
    }
}
