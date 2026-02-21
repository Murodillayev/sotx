package uz.pdp.sotx.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Map;

@Component
public class JwtUtils {


    public String generateToken(String phone, Map<String, Object> claims) {
        return Jwts.builder()
                .subject(phone)
                .claims(claims)
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {

        return Keys.hmacShaKeyFor("bu_secter_key_uzunligi_32_byte_bolishi_shart".getBytes());
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
