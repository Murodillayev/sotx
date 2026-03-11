package uz.pdp.sotx;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.POST;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/bot")
    public String sendMessage(@RequestParam String message) {
        botService.sendMessage(message);
        return "Success";
    }
}
