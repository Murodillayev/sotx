package uz.pdp.sotx.commad;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import uz.pdp.sotx.AuthUser;
import uz.pdp.sotx.AuthUserRepository;
import uz.pdp.sotx.CalculatorService;
import uz.pdp.sotx.SecurityContext;

@ShellComponent
public class CalculatorCommand {
    private final CalculatorService service;

    public CalculatorCommand(CalculatorService service) {
        this.service = service;
    }


    @ShellMethod(value = "Bu ikkita sonni qoshib beradi")
    public Double add(
            @ShellOption(defaultValue = "1") Double a,
            @ShellOption(defaultValue = "1") Double b
    ) {
        return service.add(a, b);
    }

    @ShellMethod
    public Double sub(
            @ShellOption(defaultValue = "1") Double a,
            @ShellOption(defaultValue = "1") Double b) {
        return service.subtract(a, b);
    }

    @ShellMethod(key = "mul")
    public Double multiply(@ShellOption(defaultValue = "1") Double a, @ShellOption(defaultValue = "1") Double b) {
        return service.multiply(a, b);
    }

    @ShellMethod(key = "div")
    public Double divide(@ShellOption(defaultValue = "1") Double a, @ShellOption(defaultValue = "1") Double b) {
        return service.divide(a, b);
    }


    //    @ShellMethodAvailability(value = {"add", "sub"})
    @ShellMethodAvailability
    public Availability isAuthenticated() {
        AuthUser authUser = SecurityContext.getCurrentUser();
        if (authUser != null) {
            return Availability.available();
        }
        return Availability.unavailable("You are not logged in!");
    }

}
