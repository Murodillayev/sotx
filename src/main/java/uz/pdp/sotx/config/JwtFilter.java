package uz.pdp.sotx.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.repository.AuthUserRepository;

import java.io.IOException;

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


        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.replace("Bearer ", "");

            // validate token and get claims (subject, ...)
            Claims claims = jwtUtils.validateToken(token);

            // load user from db by subject
            String phone = claims.getSubject();
            AuthUser authUser = authUserRepository.findByPhone(phone).orElseThrow(
                    () -> new RuntimeException("Invalid phone number")
            );

            // create Auth( auth is true)
            CustomUserDetails userDetails = CustomUserDetails.builder()
                    .role(authUser.getRole())
                    .id(authUser.getId())
                    .username(authUser.getPhone())
                    .build();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // put to SecutiryContextHolder
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }
}
