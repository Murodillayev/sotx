package uz.pdp.sotx.service;

import org.springframework.stereotype.Service;
import uz.pdp.sotx.mapper.AuthUserMapper;
import uz.pdp.sotx.model.dto.RegisterDto;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;

@Service
public class AuthUserService {
    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;

    public AuthUserService(AuthUserMapper mapper, AuthUserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void register(RegisterDto dto) {
        AuthUser authUser = mapper.fromDto(dto);
        repository.save(authUser);
    }
}
