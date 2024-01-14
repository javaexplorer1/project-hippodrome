import lombok.SneakyThrows;
import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    private final String name = "Pegasus";
    private final double speed = 2.5;
    private final double distance = 3.0;

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
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    void testHorseConstructorEmpty(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 2.5));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testHorseConstructorExceptionSecondNegativeNumber() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -2.5));
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
    void getSpeed() {               // переписать
        assertEquals(2.5, new Horse("Pegasus", 2.5).getSpeed());
    }

    @Test
    @SneakyThrows
    void getDistance() {
        double expectedDistance = 0;
        Horse horse = new Horse(name, speed);
        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        double actualDistance = (double) field.get(horse);
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void move() {

        try (MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            new Horse(name, speed, distance).move();
            mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

                  //test{Method}_Should{Do}_When{Condition}
    @ParameterizedTest
    @ValueSource(doubles = {0, 0.2, 0.5, 1.0, 1000.0})
    void getRandomDouble(double fakeValue) {
        Horse horse = new Horse(name, speed, distance);
        double expectedDistance = horse.getDistance() + horse.getSpeed() * fakeValue;
        try (MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(fakeValue);
            horse.move();
            double actualDistance = horse.getDistance();
            assertEquals(expectedDistance, actualDistance);
        }


    }
}