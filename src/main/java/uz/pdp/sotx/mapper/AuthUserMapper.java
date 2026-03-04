package uz.pdp.sotx.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.dto.AuthUserDto;
import uz.pdp.sotx.model.dto.AuthUserRegisterDto;
import uz.pdp.sotx.model.enums.AuthRole;

@Component
public class AuthUserMapper {

    public AuthUser fromDto(AuthUserRegisterDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setFullName(dto.getFullName());
        authUser.setPassword(dto.getPassword());
        authUser.setUsername(dto.getUsername());
        authUser.setRole(AuthRole.USER);
        return authUser;
    }

    public AuthUserDto toDto(AuthUser save) {
        return AuthUserDto.builder()
                .id(save.getId())
                .fullName(save.getFullName())
                .role(save.getRole())
                .username(save.getUsername())
                .build();
    }
}
