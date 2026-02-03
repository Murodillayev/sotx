package uz.pdp.sotx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("!dev")
public class ProdNotifyService implements NotifyService {
    @Override
    public void send(String message) {
        log.info("Notify service send message: {}", message);
    }
}
