import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {


    @Test
    void testHorseConstructorException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));

    }

    @Test
    void testHorseConstructorExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void testHorseConstructorEmpty(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 2.5));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testHorseConstructorExceptionSecondNegativeNumber() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegasus", -2.5));
    }

    @Test
    void testHorseConstructorExceptionSecondNegativeNumberMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegasus", -2.5));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void testHorseConstructorExceptionThirdNegativeNumber() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegasus", 2.5, -3.0));
    }

    @Test
    void testHorseConstructorExceptionThirdNegativeNumberMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegasus", 2.5, -3.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        assertEquals("Pegasus", new Horse("Pegasus", 2.5).getName());
    }

    @Test
    void getSpeed() {
        assertEquals(2.5, new Horse("Pegasus", 2.5).getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(3.0, new Horse("Pegasus", 2.5, 3.0).getDistance());
        assertEquals(0, new Horse("Pegasus", 2.5).getDistance());
    }

    @Test
    void move() {

    }

    @Test
    void getRandomDouble() {
    }
}