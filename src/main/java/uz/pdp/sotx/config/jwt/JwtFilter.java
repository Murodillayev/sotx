package uz.pdp.sotx.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.sotx.config.YamlData;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;
import uz.pdp.sotx.utils.Constants;
import uz.pdp.sotx.validator.AuthUserValidator;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final AuthUserValidator authUserValidator;
    private final YamlData yamlData;

    public JwtFilter(JwtUtils jwtUtils, AuthUserValidator authUserValidator, YamlData yamlData) {
        this.jwtUtils = jwtUtils;
        this.authUserValidator = authUserValidator;
        this.yamlData = yamlData;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        long start = System.currentTimeMillis();
        String token = request.getHeader("Authorization");

        if (!isPublicPath(request.getRequestURI()) && token != null) {
            // validate token
            Claims claims = jwtUtils.validateToken(token.replace("Bearer ", ""));
            if (claims.getSubject() != null) {

                Authentication authentication = prepareAuthentication(claims);
                // put Authentification to Security context holder
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
        long end = System.currentTimeMillis();
        System.out.println("Request time: " + (end - start) + " ms");

    }

    private Authentication prepareAuthentication(Claims claims) {
        List<GrantedAuthority> authorities;

        if (yamlData.getSyncDb()) {
            AuthUser authUser = authUserValidator.existsAndGetByUsername(claims.getSubject());
            authorities = List.of(new SimpleGrantedAuthority("ROLE_" + authUser.getRole()));
        } else {
            String role = claims.get("role", String.class);
            authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                authorities);
    }


    private boolean isPublicPath(String path) {
        return Arrays.asList(Constants.WHITE_LIST).contains(path);
    }

}
