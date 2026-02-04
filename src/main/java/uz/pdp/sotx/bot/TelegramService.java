package uz.pdp.sotx.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.pdp.sotx.bot.model.Member;
import uz.pdp.sotx.bot.repository.MemberRepository;

import java.util.List;

@Service
public class TelegramService {
    private final MemberRepository memberRepository;
    private final ErrorPosterBot bot;

    public TelegramService(MemberRepository memberRepository, ErrorPosterBot bot) {
        this.memberRepository = memberRepository;
        this.bot = bot;
    }

    public void sendError(String message) {
        List<Member> members = memberRepository.findAll();
        members.forEach(member -> {
            if (member.getChatId() != null) {
                bot.sendMessage(SendMessage.builder()
                        .text("<b>⚠️ Error bo'ldi: </b> | " + message)
                        .chatId(member.getChatId())
                        .build());
            }
        });
    }
}
