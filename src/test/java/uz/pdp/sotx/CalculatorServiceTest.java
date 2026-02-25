package uz.pdp.sotx;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorServiceTest {

    private static CalculatorService service;

    @BeforeAll
    static void setUp() {
        service = new CalculatorService();
    }


    @Test
    @Order(2)
    @DisplayName("kopi bilan 100 millisekundda javob qaytishi kerak")
//    @TestMethodOrder
    void testDiv_shouldDoneOneMillisecond() {
        Assertions.assertTimeout(Duration.ofMillis(1), () -> {
            service.div(100, 100);
        });
    }

    @Test
    @DisplayName(value = "1 ga 2 ni qoshsa 3 chiqishi kerak enable bolishi kerak")
    @EnabledIf(value = "isDisable")
    void add() {
        int a = 1;
        int b = 2;
        int expected = a + b;
        int result = service.add(a, b);

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName(value = "-1 ga -2 ni qoshsa -3 chiqishi kerak")
    void testAddWithNegatives() {
        int a = -1;
        int b = -2;
        int expected = a + b;
        int result = service.add(a, b);
        Assertions.assertEquals(expected, result);
    }


    @Test
    @DisplayName(value = "2 ni 2 ga bolsa 1 chiqishi kerak")
    @DisabledOnOs(value = OS.MAC)
    void div() {
        int a = 2;
        int b = 2;
        int expected = a / b;
        int result = service.div(a, b);
        Assertions.assertEquals(expected, result);
    }

    // agar b=0   ->400 -> "not zero"
    @Test
//    @Disabled
    @EnabledOnOs(value = {OS.LINUX, OS.MAC, OS.WINDOWS})
    void testDiv_ShouldThrowBadRequestExceptionWhenBZero() {
        int a = 2;
        int b = 0;
        assertThrows(BadRequestException.class, () -> {
            service.div(a, b);
        });

    }

    @Test
    @Order(1)
    @DisplayName("disable bolishi kerak")
    @DisabledIf(value = "isDisable")
    void testDiv_Should_Message_Not_ZeroMessageWhenBZero() {
        int a = 2;
        int b = 0;
        RuntimeException exception = assertThrows(BadRequestException.class, () -> {
            service.div(a, b);
        });
        Assertions.assertEquals("not zero", exception.getMessage());
    }


    boolean isDisable() {
        return true;
    }

}