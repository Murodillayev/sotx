package uz.pdp.sotx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/page")
public class HomeController {
    @GetMapping({"/index", "/"})
    public String index() {

        return "index";
    }
}
