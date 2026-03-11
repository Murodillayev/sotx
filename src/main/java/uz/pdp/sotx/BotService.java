package uz.pdp.sotx;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashSet;
import java.util.Set;

@Service
public class BotService {
    private final ParrotBot bot;
    private final Set<String> chats = new HashSet<>();

    public BotService(ParrotBot bot) {
        this.bot = bot;
    }

    public void sendMessage(String message) {
        for (String chat : chats) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chat);
            sendMessage.setText(message);
            bot.sendMessage(sendMessage);
        }
    }

    public void replyMessage(Message message) {
        chats.add(message.getChatId().toString());
        String chatId = message.getChatId().toString();
        String text = message.getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        bot.sendMessage(sendMessage);
    }
}
