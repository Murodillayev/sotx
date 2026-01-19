package uz.pdp.sotx.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.dto.RegisterDto;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.model.enums.AuthRole;

@Component
public class AuthUserMapper {

    public AuthUser fromDto(RegisterDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setFullName(dto.getFullName());
        authUser.setEmail(dto.getEmail());
        authUser.setPhone(dto.getPhone());
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(dto.getPassword());
        authUser.setRole(AuthRole.USER.name());
        return authUser;
    }
}
