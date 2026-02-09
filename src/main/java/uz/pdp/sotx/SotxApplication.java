package uz.pdp.sotx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SotxApplication {
	public static void main(String[] args) {
		SpringApplication.run(SotxApplication.class, args);
	}
}
