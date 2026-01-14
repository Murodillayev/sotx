package uz.pdp.sotx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.pdp.sotx.config.QueryProps;

@SpringBootApplication
@EnableConfigurationProperties({QueryProps.class})
public class SotxApplication {
	public static void main(String[] args) {
		SpringApplication.run(SotxApplication.class, args);
	}
}
