package uz.pdp.sotx;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppProps {

    @Value("${application.telegram-bot.token}")
    private String token;

    @Value("${application.telegram-bot.username}")
    private String username;
}
