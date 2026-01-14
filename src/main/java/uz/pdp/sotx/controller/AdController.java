package uz.pdp.sotx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-ad")
public class AdController {

    @GetMapping
    public String myAds() {
        return "ads";
    }
}
