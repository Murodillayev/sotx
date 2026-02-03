package uz.pdp.sotx;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAsyncController {

    private final TestService service;
    private final NotifyService notifyService;

    public TestAsyncController(TestService service,  NotifyService notifyService) {
        this.service = service;
        this.notifyService = notifyService;
    }

    @PostMapping("/register")
    public void register(@RequestParam String name) {
        service.register(name);
    }


    @PostMapping("/send")
    public void send(@RequestParam String message) {
        notifyService.send(message);
    }
}
