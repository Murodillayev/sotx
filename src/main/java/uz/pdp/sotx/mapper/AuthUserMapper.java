package uz.pdp.sotx.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.dto.AuthUserRegisterDto;

@Component
public class AuthUserMapper {

    public AuthUser fromDto(AuthUserRegisterDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setFullName(dto.getFullName());
        authUser.setPassword(dto.getPassword());
        authUser.setUsername(dto.getUsername());
        return authUser;
    }
}
