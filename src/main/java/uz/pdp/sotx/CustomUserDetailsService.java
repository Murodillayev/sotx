package uz.pdp.sotx;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.config.CustomUserDetails;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserRepository repository;

    public CustomUserDetailsService(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        AuthUser authUser = repository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException("User not found"));

//        return new User(
//                authUser.getPhone(),
//                authUser.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_" + authUser.getRole()))
//        );

        return CustomUserDetails.builder()
                .username(authUser.getPhone())
                .password(authUser.getPassword())
                .role(authUser.getRole())
                .id(authUser.getId())
                .build();
    }
}
