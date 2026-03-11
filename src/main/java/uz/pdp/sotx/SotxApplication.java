package uz.pdp.sotx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SotxApplication {
    public static void main(String[] args) {
        SpringApplication.run(SotxApplication.class, args);
    }


    @Bean
    public TelegramBotsApi telegramBotsApi(ParrotBot bot) {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
            return api;
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
