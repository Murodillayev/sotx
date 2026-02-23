package uz.pdp.sotx.commad;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import uz.pdp.sotx.AuthUser;
import uz.pdp.sotx.AuthUserRepository;
import uz.pdp.sotx.SecurityContext;
import uz.pdp.sotx.provider.UsernameProvider;

import java.util.List;

@ShellComponent
public class AuthUserCommand {


    private final AuthUserRepository repository;

    public AuthUserCommand(AuthUserRepository authUserRepository) {
        this.repository = authUserRepository;
    }


    @ShellMethod
    public String delete(@ShellOption(valueProvider = UsernameProvider.class) String username) {
        repository.findByUsername(username).ifPresent(repository::delete);
        return "Successfully deleted!";
    }

    @ShellMethod(value = "Barcha foydalanuvchilarni jadval ko'rinishida chiqarish")
    public String users() {
        List<AuthUser> users = repository.findAll();
        // Jadval ma'lumotlarini 2D massivga o'tkazamiz
        String[][] data = new String[users.size() + 1][2];
        data[0][0] = "id";
        data[0][1] = "username";

        for (int i = 0; i < users.size(); i++) {
            data[i + 1][0] = users.get(i).getId().toString();
            data[i + 1][1] = users.get(i).getUsername();
        }

        TableModel model = new ArrayTableModel(data);
        TableBuilder tableBuilder = new TableBuilder(model);

        tableBuilder.addFullBorder(BorderStyle.fancy_light);

        return tableBuilder.build().render(80);
    }


    @ShellMethodAvailability
    public Availability isAdmin() {
        AuthUser currentUser = SecurityContext.getCurrentUser();
        if (currentUser == null) {
            return Availability.unavailable("You are not logged in!");
        }
        if ("ADMIN".equals(currentUser.getRole())) {
            return Availability.available();
        }
        return Availability.unavailable("You are not an admin!");
    }
}
