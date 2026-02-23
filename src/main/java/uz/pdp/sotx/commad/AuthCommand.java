package uz.pdp.sotx.commad;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uz.pdp.sotx.AuthUser;
import uz.pdp.sotx.AuthUserRepository;
import uz.pdp.sotx.SecurityContext;

@ShellComponent
public class AuthCommand {
    private final AuthUserRepository repository;

    public AuthCommand(AuthUserRepository repository) {
        this.repository = repository;
    }

    @ShellMethod
    public String register(
            @ShellOption("-u") String username,
            @ShellOption("-p") String password
    ) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(username);
        authUser.setPassword(password);
        repository.save(authUser);
        return "Successfully registered!";
    }

    @ShellMethod
    public String login(
            @ShellOption("-u") String username,
            @ShellOption("-p") String password
    ) {
        AuthUser authUser = repository.findByUsernameAndPassword(username,password).orElse(null);
        if (authUser == null) {
            return "Invalid username or password";
        }
        SecurityContext.setCurrentUser(authUser);
        return "Successfully registered!";
    }

    @ShellMethod
    public String logout(){
        SecurityContext.setCurrentUser(null);
        return "Successfully logged out!";
    }

}
