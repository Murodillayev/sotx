package uz.pdp.sotx.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.sotx.model.entity.base.BaseEntity;
import uz.pdp.sotx.model.enums.AuthRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthUser extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    private String email;
    private String phone;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

//    @Enumerated(EnumType.STRING)
    private String role;
}
