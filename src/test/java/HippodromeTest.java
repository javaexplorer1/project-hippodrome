import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

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

//        List<Horse> expectedList = new LinkedList<>();
//        for (int i = 0; i < 30; i++) {
//            expectedList.add(new Horse("Horse" + i, 1.0));
//        }
        List<Horse> horses = IntStream.range(0, 30).mapToObj(i -> new Horse("Horse" + i, i)).toList();
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
//        assertSame(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = IntStream.range(0, 50).mapToObj(i -> Mockito.mock(Horse.class)).toList();
        new Hippodrome(horses).move();
        horses.forEach(horse -> Mockito.verify(horse, Mockito.times(1)).move());
    }

    @Test
    void getWinner() {
        Horse horsesOne = new Horse("Horse1", 1, 10);
        Horse horsesTwo = new Horse("Horse2", 1, 20);
        Horse horsesThree = new Horse("Horse3", 1, 30);

        Hippodrome hippodrome = new Hippodrome(List.of(horsesOne, horsesTwo, horsesThree));
        assertSame(horsesThree, hippodrome.getWinner());
    }
}