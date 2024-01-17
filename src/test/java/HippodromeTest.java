import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {


    @Test
    void testHorseConstructorException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testHorseConstructorExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void testHorseConstructorExceptionEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }


    @Test
    void testHorseConstructorExceptionEmptyListMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    void getHorses() {
    }

    @Test
    void move() {
    }

    @Test
    void getWinner() {
    }
}