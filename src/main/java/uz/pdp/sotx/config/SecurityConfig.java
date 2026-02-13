package uz.pdp.sotx.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.pdp.sotx.CustomUserDetailsService;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.enums.AuthRole;
import uz.pdp.sotx.repository.AuthUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers(
                                    "/home",
                                    "/login",
                                    "/logout",
                                    "/static/**",
                                    "/css/**"
                            ).permitAll()
                            .anyRequest().authenticated();
                })
                .userDetailsService(customUserDetailsService);

        http.formLogin(formLoginConfigurer -> {
            formLoginConfigurer.loginPage("/login");
            formLoginConfigurer.loginProcessingUrl("/login");
            formLoginConfigurer.usernameParameter("phone");
            formLoginConfigurer.passwordParameter("password");
            formLoginConfigurer.defaultSuccessUrl("/", true);
        });
        http.logout(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
