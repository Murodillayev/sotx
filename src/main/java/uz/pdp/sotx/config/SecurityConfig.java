package uz.pdp.sotx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.pdp.sotx.service.CustomUserDetailsService;
import uz.pdp.sotx.utils.Constants;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {

        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(Constants.WHITE_LIST)
                    .permitAll()
                    .anyRequest()
                    .fullyAuthenticated();
        });

        http.sessionManagement(
                securitySessionManagementConfigurer -> {
                    securitySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                }
        );


        http.userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}


// Security contec holder(....)

// cf (check token, create authentification(isAuth=ture )) -> put holder

// -> header(token) ->  sf1 -> cf -> sf2(auth check)  sf3 .... -> controller
