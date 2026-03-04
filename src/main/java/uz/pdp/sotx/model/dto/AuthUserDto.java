package uz.pdp.sotx.model.dto;

import lombok.*;
import uz.pdp.sotx.model.enums.AuthRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDto {
    private Long id;
    private String fullName;
    private String username;
    private AuthRole role;
}
