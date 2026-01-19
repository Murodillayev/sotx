package uz.pdp.sotx.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository repository;

    public CustomUserDetailsService(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser = repository.findByUsernameAndDeletedFalse(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" +  authUser.getRole())
        );

        return new User(authUser.getUsername(), authUser.getPassword(), authorities);
    }
}
