package uz.pdp.sotx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import uz.pdp.sotx.model.AppProps;

@SpringBootApplication
@EnableConfigurationProperties({AppProps.class})
@EnableFeignClients
public class SotxApplication {
	public static void main(String[] args) {
		SpringApplication.run(SotxApplication.class, args);
	}


}
