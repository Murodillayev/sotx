package uz.pdp.sotx;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.sotx.bot.TelegramService;

import java.util.Random;

@RestController
@RequestMapping
@Slf4j
public class LogExampleController {

    @GetMapping("/log")
    public void testLog() {
        sendNotify();
    }


    private void sendNotify() {

        long start = System.currentTimeMillis();
        int success = 0;
        int fail = 0;
        // --
        int phoneCount = 100;
        for (int i = 0; i < phoneCount; i++) {

            log.debug("Sending notification... | phone = {}", i);
            try {
                callSmsProviderApi(i, " Hello guys");
                success++;
            } catch (Exception e) {
                fail++;
                log.error("Sending notification...| phone= {}", i, e);
            }

            log.debug("Successfully send notification.. | phone = {}", i);
        }
        // --

        long end = System.currentTimeMillis();
        log.info("Successfully send notification... | all = {}, success = {}, fail = {}, time = {} ms", phoneCount, success, fail, end - start);
    }

    @SneakyThrows
    private void callSmsProviderApi(int i, String s) {
        Thread.sleep(100);
        if (!new Random().nextBoolean()) {
            if (new Random().nextBoolean()) {
                throw new RuntimeException();
            }
        }
    }
}

// Appender
