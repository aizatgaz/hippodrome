import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void testShouldThrowExceptionWhenConstructorArgumentNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void testShouldThrowCorrectMessageWhenConstructorArgumentNullOrEmpty() {
        String messageWhenNull = null;
        String messageWhenEmpty = null;
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException ex) {
            messageWhenNull = ex.getMessage();
        }
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException ex) {
            messageWhenEmpty = ex.getMessage();
        }
        assertEquals("Horses cannot be null.", messageWhenNull);
        assertEquals("Horses cannot be empty.", messageWhenEmpty);
    }

    @Test
    public void testShouldReturnListOfHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse("Horse" + i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testShouldCallHorseMethodMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (int i = 1; i < 51; i++) {
            Mockito.verify(horses.get(i - 1)).move();
        }
    }

    @Test
    public void testShouldReturnWinnerHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse("Horse" + i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        double distance = 0;
        Horse winner = null;
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getDistance() > distance) {
                distance = horses.get(i).getDistance();
                winner = horses.get(i);
            }
        }
        assertSame(winner, hippodrome.getWinner());
    }
}