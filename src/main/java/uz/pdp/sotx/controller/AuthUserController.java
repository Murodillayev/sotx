package uz.pdp.sotx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.sotx.model.dto.LoginRequest;
import uz.pdp.sotx.model.dto.LoginResponse;
import uz.pdp.sotx.service.AuthUserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthUserController {
    private final AuthUserService service;

    public AuthUserController(AuthUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}
