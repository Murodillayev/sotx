package uz.pdp.sotx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dev")
public class MockNotifyService implements NotifyService {
    @Override
    public void send(String message) {

        log.info("Mock Notify service send message: {}", message);

    }
}
