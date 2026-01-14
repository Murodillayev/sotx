package uz.pdp.sotx.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "query")
public class QueryProps {
    private String select;
    private String update;
    private String delete;
    private String insert;
}



