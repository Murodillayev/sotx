package uz.pdp.sotx;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
public class Props {

    @Value("${example.app-name:none}")
    private String exampleAppName;

    @Value("${example.my-skills:null}")
    private List<String> skills;
}
