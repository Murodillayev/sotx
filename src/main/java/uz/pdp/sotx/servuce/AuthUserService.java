package uz.pdp.sotx.servuce;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.mapper.AuthUserMapper;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.dto.AuthUserDto;
import uz.pdp.sotx.model.dto.AuthUserRegisterDto;

@Service
public class AuthUserService {
    private final AuthUserMapper mapper;

    public AuthUserService(AuthUserMapper mapper) {
        this.mapper = mapper;
    }

    public  AuthUserDto register(AuthUserRegisterDto dto) {
        AuthUser authUser = mapper.fromDto(dto);
        return null;
    }
}
