package uz.pdp.sotx.servuce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.AuthUser;

@Service
@Slf4j
public class TelegramService {
    public void sendMessageToModerator(AuthUser authUser) {


        log.info("Send message to moderator.... | {}", authUser);
    }
}
