package uz.pdp.sotx;

import jakarta.annotation.security.PermitAll;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceTest {

    private static CalculatorService service;

    @BeforeAll
    static void setUp() {
        service = new CalculatorService();
    }


    @Test
    void testDiv_shouldDoneOneMillisecond() {
        Assertions.assertTimeout(Duration.ofMillis(1), () -> {
            service.div(100, 100);
        });
    }

    @RepeatedTest(value = 100, name = "{displayName} | {currentRepetition} | {totalRepetitions}")
    void add() {
        int a = new Random().nextInt(1, 100);
        int b = new Random().nextInt(1, 100);
        int expected = a + b;
        int result = service.add(a, b);

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void testAddWithNegatives1(int a) {
        int b = -2;
        int expected = a + b;
        int result = service.add(a, b);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource(value = {"nums"})
    void testAddWithNegatives2(int a) {
        int b = -2;
        int expected = a + b;
        int result = service.add(a, b);
        Assertions.assertEquals(expected, result);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "a , b",
            "1, 2",
            "2, 3",
            "12, 13",
            "24, 31"
    }, useHeadersInDisplayName = true)
    void testAddWithNegatives3(int a, int b) {
        int expected = a + b;
        int result = service.add(a, b);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv", useHeadersInDisplayName = true)
    void testAddWithNegatives4(int a, int b, int expected) {
        int result = service.add(a, b);
        Assertions.assertEquals(expected, result);
    }


    public static Stream<Integer> nums() {
        return Stream.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );
    }


}