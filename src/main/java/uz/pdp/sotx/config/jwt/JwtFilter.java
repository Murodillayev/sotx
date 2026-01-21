package uz.pdp.sotx.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.sotx.utils.Constants;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (!isPublicPath(request.getRequestURI()) && token != null) {
            // validate token
            // load user by username from db
            // make Authentification(UserDetails) (isAuth=true)
            // put Authentification to Security context holder
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return Arrays.asList(Constants.WHITE_LIST).contains(path);
    }

}
