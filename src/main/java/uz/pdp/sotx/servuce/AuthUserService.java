package uz.pdp.sotx.servuce;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.sotx.events.RegisterUserEvent;
import uz.pdp.sotx.mapper.AuthUserMapper;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.dto.AuthUserDto;
import uz.pdp.sotx.model.dto.AuthUserRegisterDto;
import uz.pdp.sotx.repository.AuthUserRepository;

@Service
@Slf4j
public class AuthUserService {
    private final AuthUserMapper mapper;
    private final AuthUserRepository repository;


    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthUserService(AuthUserMapper mapper, AuthUserRepository repository, ApplicationEventPublisher applicationEventPublisher) {
        this.mapper = mapper;
        this.repository = repository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public AuthUserDto register(AuthUserRegisterDto dto) {
        // create user
        AuthUser authUser = mapper.fromDto(dto);
        AuthUser save = repository.save(authUser);

        // publish event
        applicationEventPublisher.publishEvent(new RegisterUserEvent(this, save));

        log.info("Register user {} | {}", authUser, save);
//        throw new RuntimeException("Register failed");
        return mapper.toDto(save);
    }
}
