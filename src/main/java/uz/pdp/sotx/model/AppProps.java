package uz.pdp.sotx.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class AppProps {
    private String postApi;
}
