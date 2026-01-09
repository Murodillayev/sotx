package uz.pdp.sotx.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.sotx.model.base.BaseEntity;
import uz.pdp.sotx.model.enums.AuthRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends BaseEntity {
    private String fullName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private AuthRole role;
    private Image image;
}
