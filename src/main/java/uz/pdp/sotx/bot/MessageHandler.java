package uz.pdp.sotx.bot;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.pdp.sotx.bot.model.Member;
import uz.pdp.sotx.bot.repository.MemberRepository;

@Component
public class MessageHandler {
    private final ErrorPosterBot bot;
    private final MemberRepository memberRepository;

    public MessageHandler(@Lazy ErrorPosterBot bot, MemberRepository memberRepository) {
        this.bot = bot;
        this.memberRepository = memberRepository;
    }

    public void handle(Message message) {
        String text = message.getText();
        String chatId = message.getChatId().toString();
        if (text.startsWith("login:")) {
            String usernameAndPass = text.replace("login:", "");
            registerUser(usernameAndPass, chatId);
        }
    }

    private void registerUser(String usernameAndPass, String chatId) {
        String[] usernameAndPassArr = usernameAndPass.split("/");
        String username = usernameAndPassArr[0];
        String password = usernameAndPassArr[1];
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Username not found!")
        );
        if (!member.getPassword().equals(password)) {
            throw new RuntimeException("Passwords don't match!");
        }
        member.setChatId(chatId);
        memberRepository.save(member);
        bot.sendMessage(SendMessage.builder()
                .chatId(chatId)
                .text("Welcome " + username + "!")
                .build());
    }
}
