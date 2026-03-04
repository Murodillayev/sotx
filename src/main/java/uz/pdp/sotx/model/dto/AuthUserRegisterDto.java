package uz.pdp.sotx.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.sotx.model.enums.AuthRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserRegisterDto {
    private String fullName;
    private String username;
    private String password;
    private String email;
}
