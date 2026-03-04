package uz.pdp.sotx.servuce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.AuthUser;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class CronService {


    private final CacheService cacheService;
    private final EmailService emailService;

    public CronService(CacheService cacheService, EmailService emailService) {
        this.cacheService = cacheService;
        this.emailService = emailService;
    }


    //    @Scheduled(cron = "59 12 16 * * *")
    @Scheduled(fixedRate = 10000)
    public void sendUsernamePassword() {
        Collection<AuthUser> values = cacheService.getAuthUsers().values();
        if (emailService.isOnSMPTP() && !values.isEmpty()) {
            log.info("Start cache data by cron");
            long start = System.currentTimeMillis();
            int success = 0;
            int fail = 0;

            List<AuthUser> authUsers = values.stream().toList();
            for (AuthUser authUser : authUsers) {

                cacheService.remove(authUser.getId());
                try {
                    emailService.sendUsernamePassword(authUser);
                    success++;
                } catch (Exception e) {
                    fail++;
                }
            }
            log.info("Cache data successfully sent | all = {} , success = {} , fail = {}, time = {} ms", (success + fail), success, fail, System.currentTimeMillis() - start);

        }
    }
}
