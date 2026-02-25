package uz.pdp.sotx;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {


    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    @SneakyThrows
    public int div(int a, int b) {


        Thread.sleep(100);
        if (b == 0) {
            throw new BadRequestException("not zero");
        }
        return a / b;
    }
}

