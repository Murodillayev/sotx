package uz.pdp.sotx.config;


import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ToString
public class YamlData {

    @Value("${application.jwt.sync-db:true}")
    private Boolean syncDb;

    @Value("${application.jwt.access-token-exp:120000}") // 2 min
    private Long accessTokenExpiration;


    @Value("${application.jwt.refresh-token-exp:300000}") // 5 min
    private Long refreshTokenExpiration;

}

// Security config

// form based -> no stateless

// http basic -> stateless
// jwt  -> stateless
