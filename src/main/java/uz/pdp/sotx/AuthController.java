package uz.pdp.sotx;


import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

    @SchemaMapping(typeName = "Query", value = "sessionUserName")
    public String sessionUsername() {
        return "Abudulahad";

    }
}
