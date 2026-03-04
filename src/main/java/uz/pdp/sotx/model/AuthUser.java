package uz.pdp.sotx.model;

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
@Entity
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String username;


    private String password;

    @Enumerated(EnumType.STRING)
    private AuthRole role;
}
