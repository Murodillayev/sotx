package uz.pdp.sotx;

import org.springframework.stereotype.Component;

@Component
public class SecurityContext {
    private static AuthUser sessionUser;

    public static AuthUser getCurrentUser() {
        return sessionUser;
    }

    public static void setCurrentUser(AuthUser currentUser) {
        sessionUser = currentUser;
    }
}
