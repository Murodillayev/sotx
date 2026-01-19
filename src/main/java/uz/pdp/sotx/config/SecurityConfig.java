package uz.pdp.sotx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.pdp.sotx.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] WHITE_LIST = {
            "/index",
            "/login",
            "/register",
            "/api/v1/auth/register"
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {

        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(WHITE_LIST)
                    .permitAll()
                    .anyRequest()
                    .fullyAuthenticated();
        });

        http.formLogin(
                form -> {
                    form
                            .loginPage("/login")
                            .defaultSuccessUrl("/index", true)
                            .usernameParameter("username")
                            .passwordParameter("password");
                }
        );

        http.logout(
                logout ->
                        logout.logoutUrl("/logout")
                                .deleteCookies("JSESSIONID")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")

        );

//        http.formLogin(Customizer.withDefaults());
//        http.logout(Customizer.withDefaults());

        http.userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
