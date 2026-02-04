package uz.pdp.sotx.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ErrorPosterBot extends TelegramLongPollingBot {

    private final MessageHandler messageHandler;

    public ErrorPosterBot(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            messageHandler.handle(update.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "nasyachibot:";
    }

    @Override
    public String getBotToken() {
        return "8498920498:AAHdjpmKH2lxrb0ZSeUcfPsYGdJqGnl1dv0";
    }

    public void sendMessage(SendMessage message) {
        try {
            message.enableHtml(true);
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
