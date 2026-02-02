package uz.pdp.sotx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class TestService {
    private final SmsService smsService;

    public TestService(SmsService smsService) {
        this.smsService = smsService;
    }

    public void register1() {
        validateData();
        mappingData();

        new Thread(() -> {
            smsService.sendVerificationCode("asd");
        }).start();
        saveDb();
    }

    public void register2() {
        validateData();
        mappingData();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            smsService.sendVerificationCode("213");
        });


        saveDb();
    }

    public void register3() {
        validateData();
        mappingData();
        CompletableFuture.runAsync(() -> {
            smsService.sendVerificationCode("sa");
        });

        saveDb();
    }


    public void register(String name) {
        validateData();
        mappingData();
        smsService.sendVerificationCode(name);
        saveDb();
    }


    private void saveDb() {


        log.info("saving db.....");
    }


    private void mappingData() {
        log.info("mapping from dto...");
    }

    private void validateData() {
        log.info("validate data...");
    }
}
