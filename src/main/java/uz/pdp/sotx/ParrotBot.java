package uz.pdp.sotx;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class ParrotBot extends TelegramLongPollingBot {

    private final AppProps props;

    private final BotService botService;

    public ParrotBot(AppProps props, @Lazy BotService botService) {
        super(props.getToken());
        this.props = props;
        this.botService = botService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            botService.replyMessage(update.getMessage());

        } else if (update.hasCallbackQuery()) {

        }
    }

    @Override
    public String getBotUsername() {
        return props.getUsername();
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}


// devbot, prodbot