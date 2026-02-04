package uz.pdp.sotx.bot.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.sotx.bot.ErrorPosterBot;

@Configuration
public class BotInitializer {

    private final ErrorPosterBot bot;

    public BotInitializer(ErrorPosterBot bot) {
        this.bot = bot;
    }

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotsApi() {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
}
