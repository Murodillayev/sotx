package uz.pdp.sotx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.sotx.model.AuthUser;
import uz.pdp.sotx.model.enums.AuthRole;
import uz.pdp.sotx.repository.AuthUserRepository;

@SpringBootApplication
@EnableCaching
public class SotxApplication {
	public static void main(String[] args) {
		SpringApplication.run(SotxApplication.class, args);
	}


//	@Bean
	public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder, AuthUserRepository repository) {
		return args -> {
            AuthUser authUser = new AuthUser();
            authUser.setRole(AuthRole.ADMIN);
            authUser.setPassword(passwordEncoder.encode("123"));
            authUser.setPhone("123456789");
            authUser.setFullName("Muhammadkomil Moridillayev");
            repository.save(authUser);
        };
	}
}
