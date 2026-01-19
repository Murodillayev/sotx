package uz.pdp.sotx.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserCreateDto {
    private String fullName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String role; // none
}
