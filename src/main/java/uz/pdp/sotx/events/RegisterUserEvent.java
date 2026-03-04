package uz.pdp.sotx.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import uz.pdp.sotx.model.AuthUser;

@Getter
public final class RegisterUserEvent extends ApplicationEvent {
    private final AuthUser authUser;

    public RegisterUserEvent(Object source, AuthUser authUser) {
        super(source);
        this.authUser = authUser;
    }
}
