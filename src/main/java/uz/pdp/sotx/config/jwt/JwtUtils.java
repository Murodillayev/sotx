package uz.pdp.sotx.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.sotx.config.YamlData;

import javax.crypto.SecretKey;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final YamlData yamlData;

    public String generateAccessToken(String username, Map<String, Object> claims) {
        Date expiration = new Date(System.currentTimeMillis() + yamlData.getAccessTokenExpiration());
        return generateToken(username, expiration, claims);
    }

    public String generateRefreshToken(String username) {
        Date expiration = new Date(System.currentTimeMillis() + yamlData.getRefreshTokenExpiration());
        return generateToken(username, expiration, Collections.emptyMap());
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }






    private String generateToken(String username, Date expiration, Map<String, Object> claims) {

        return Jwts.builder()
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .subject(username)
                .expiration(expiration)
                .claims(claims) // payload
                .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor("bu_secret_key_uzunligi_32_ta_belgidan_kam_bolmasin".getBytes());
    }


}
