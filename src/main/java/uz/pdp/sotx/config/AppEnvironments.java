package uz.pdp.sotx.config;


import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.sql.In;

@Configuration
@Getter
@ToString
public class AppEnvironments {

    @Value("${application.telegram.token}")
    private String token;

    @Value("${application.telegram.username}")
    private String username;

    @Value("${application.file-root}")
    private String root;

    @Value("${application.file-max-size}")
    private Long fileMaxSize;
}
