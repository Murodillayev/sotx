package uz.pdp.sotx.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import uz.pdp.sotx.events.RegisterUserEvent;
import uz.pdp.sotx.servuce.EmailService;
import uz.pdp.sotx.servuce.MVRefreshService;
import uz.pdp.sotx.servuce.TelegramService;

@Component
@Slf4j
public class AuthUserServiceListener {
    private final MVRefreshService mvRefreshService;
    private final TelegramService telegramService;
    private final EmailService emailService;

    public AuthUserServiceListener(MVRefreshService mvRefreshService, TelegramService telegramService, EmailService emailService) {
        this.mvRefreshService = mvRefreshService;
        this.telegramService = telegramService;
        this.emailService = emailService;
    }


    //    @EventListener({RegisterUserEvent.class})
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void sendUsernamePassword(RegisterUserEvent event) {
        emailService.sendUsernamePassword(event.getAuthUser());

    }


    //    @EventListener
    @TransactionalEventListener
    @Async
    public void sendMessageToModerator(RegisterUserEvent event) {
        telegramService.sendMessageToModerator(event.getAuthUser());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    @Async
    public void sendErrorToModerator(RegisterUserEvent event) {
        System.out.println("fail boldi");
    }

    //    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void refreshMv(RegisterUserEvent event) {
        mvRefreshService.refreshUserStatMV();
    }


}


// ASYNC
// DATA CONSISTENCY
