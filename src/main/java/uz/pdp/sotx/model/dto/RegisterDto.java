package uz.pdp.sotx.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String fullName;
    private String email;
    private String phone;
    private String username;
    private String password;
}
