package uz.pdp.sotx.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.dto.LoginResponse;

import javax.crypto.SecretKey;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    public LoginResponse generateToken(String username) {
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 3);

        String accessToken =
                Jwts.builder()
                        .signWith(getSecretKey())
                        .issuedAt(new Date())
                        .subject(username)
                        .expiration(expiration)
                        .claims(Map.of()) // payload
                        .compact();

        return LoginResponse.builder()
                .token(accessToken)
                .build();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor("bu_secret_key_uzunligi_32_ta_belgidan_kam_bolmasin".getBytes());
    }

    public Claims validateToken(String token) {


        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
}
