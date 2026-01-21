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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.sotx.model.entity.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;
import uz.pdp.sotx.utils.Constants;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final AuthUserRepository authUserRepository;

    public JwtFilter(JwtUtils jwtUtils, AuthUserRepository authUserRepository) {
        this.jwtUtils = jwtUtils;
        this.authUserRepository = authUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (!isPublicPath(request.getRequestURI()) && token != null) {


            // validate token
            Claims payload = jwtUtils.validateToken(token.replace("Bearer ", ""));

            // load user by username from db
            String username = payload.getSubject();
            Optional<AuthUser> authUserOptional = authUserRepository.findByUsernameAndDeletedFalse(username);

            if (authUserOptional.isPresent()) {
                AuthUser authUser = authUserOptional.get();
                List<SimpleGrantedAuthority> authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + authUser.getRole())
                );
                // make Authentification(UserDetails) (isAuth=true)
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

                // put Authentification to Security context holder
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }


    private boolean isPublicPath(String path) {
        return Arrays.asList(Constants.WHITE_LIST).contains(path);
    }

}
