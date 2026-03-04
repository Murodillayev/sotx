package uz.pdp.sotx.servuce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.AuthUser;

@Service
@Slf4j
public class EmailService {
    private final CacheService cacheService;

    private Boolean smptpServerOn = false;

    public EmailService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public void sendUsernamePassword(AuthUser authUser) {
        if (smptpServerOn) {
            log.info("Sending username password email... | {}", authUser);
        } else {
            cacheService.putAuthUser(authUser.getId().toString(), authUser);
            log.error("Smtp server off... | {}", authUser);
        }
    }

    public void onSmptp(Boolean on) {
        this.smptpServerOn = on;
    }

    public Boolean isOnSMPTP() {
        return this.smptpServerOn;
    }
}
