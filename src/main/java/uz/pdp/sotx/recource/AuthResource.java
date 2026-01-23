package uz.pdp.sotx.recource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sotx.model.dto.LoginRequest;
import uz.pdp.sotx.model.dto.LoginResponse;
import uz.pdp.sotx.model.dto.RegisterDto;
import uz.pdp.sotx.service.AuthUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final AuthUserService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto dto) {
        service.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestParam String refreshToken) {
        return service.refreshToken(refreshToken);
    }
}

// 1. login ni refresh token ham qaytaradigan qilish
// 2. refresh token orqali tokenni yangilash api
// 3. access token orqali murojat qilganda shartga userni
//      db dan olib Auth yasasin yoki token orqali yasasin