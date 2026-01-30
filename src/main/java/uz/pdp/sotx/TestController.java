package uz.pdp.sotx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@Slf4j
@RestController
public class TestController {
    private final Props props;

    public TestController(Props props) {
        this.props = props;
    }


    @GetMapping("/prop1")
    public String testReadProperty1() {
        String exampleAppName = props.getExampleAppName();
        log.info(exampleAppName);
        return exampleAppName;
    }

    @GetMapping("/prop2")
    public List<String> testReadProperty2() {
        return props.getSkills();
    }

    @GetMapping("/my-prop")
    public String testMyProp() {
        Properties props = new Properties();
        try (FileReader fileReader = new FileReader(new File("src/main/resources/myprop.properties"))) {
            props.load(fileReader);
            return props.getProperty("my-prop");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/my-prop")
    public void testMyProp(@RequestBody Map<String, Object> newProps) {
        File file = new File("src/main/resources/myprop.properties");
        Properties props = new Properties();

        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                props.load(in);
            } catch (IOException e) {
                throw new RuntimeException("Faylni o'qishda xatolik: " + e.getMessage());
            }
        }

        newProps.forEach((k, v) -> {
            if (v != null) {
                props.setProperty(k, v.toString());
            }
        });

        try (FileOutputStream out = new FileOutputStream(file)) {
            String comment = "Muhammadkomil tomonidan ozgartirildi";
            props.store(out, comment);
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }
}
