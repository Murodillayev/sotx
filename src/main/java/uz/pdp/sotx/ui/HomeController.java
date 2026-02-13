package uz.pdp.sotx.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String todoPage() {
        return "todo";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
