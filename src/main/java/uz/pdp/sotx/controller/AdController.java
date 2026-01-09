package uz.pdp.sotx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.sotx.service.AdService;

@Controller
@RequestMapping("/ad")
@RequiredArgsConstructor
public class AdController {

    private final AdService service;



}
