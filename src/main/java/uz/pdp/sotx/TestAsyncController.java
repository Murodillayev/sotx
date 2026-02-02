package uz.pdp.sotx;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAsyncController {

    private final TestService service;

    public TestAsyncController(TestService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void register(@RequestParam String name) {
        service.register(name);
    }
}
