package uz.pdp.sotx;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.dto.AuthUserDto;
import uz.pdp.sotx.model.dto.AuthUserRegisterDto;
import uz.pdp.sotx.servuce.AuthUserService;
import uz.pdp.sotx.servuce.CacheService;
import uz.pdp.sotx.servuce.EmailService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService service;
    private final CacheService cacheService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<AuthUserDto> register(@RequestBody AuthUserRegisterDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @GetMapping("/cache")
    public ResponseEntity<Map<String, AuthUser>> cashes() {
        return ResponseEntity.ok(cacheService.getAuthUsers());
    }

    @PutMapping("/smptp")
    public ResponseEntity<Void> smtp(@RequestParam Boolean on) {
        emailService.onSmptp(on);
        return ResponseEntity.ok().build();
    }


}

// create,
// send login parol to email agar email null bolmasa,
// mv_user_stats materialized viewni refresh qilamiz,
// moderatorga user qoshilgani haqida xabar yuboramiz
