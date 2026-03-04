package uz.pdp.sotx;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.sotx.model.dto.AuthUserDto;
import uz.pdp.sotx.model.dto.AuthUserRegisterDto;
import uz.pdp.sotx.servuce.AuthUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthUserDto> register(@RequestBody AuthUserRegisterDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }


}
