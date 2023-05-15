import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void testConstructorArgumentNameCantBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.0));
    }

    @Test
    public void testShouldThrowCorrectMessageWhenNameNull() {
        String message = null;
        try {
            new Horse(null, 2.0);
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }
        assertEquals("Name cannot be null.", message);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", ""})
    public void testShouldThrowExceptionWhenNameBlank(String str) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(str, 2.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void testShouldThrowCorrectMessageWhenNameBlank(String str) {
        String message = null;
        try {
            new Horse(str, 2.0);
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }
        assertEquals("Name cannot be blank.", message);
    }

    @Test
    public void testShouldThrowExceptionWhenSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -2.0));
    }

    @Test
    public void testShouldThrowCorrectMessageWhenSpeedNegative() {
        String message = null;
        try {
            new Horse("Horse", -2.0);
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }
        assertEquals("Speed cannot be negative.", message);
    }

    @Test
    public void testShouldThrowExceptionWhenDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 2.0, -2.0));
    }

    @Test
    public void testShouldThrowCorrectMessageWhenDistanceNegative() {
        String message = null;
        try {
            new Horse("Horse", 2.0, -2.0);
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }
        assertEquals("Distance cannot be negative.", message);
    }

    @Test
    public void testShouldReturnCorrectName() {
        String name = "Horse";
        Horse horse = new Horse(name, 2.0);
        assertEquals(name, horse.getName());
    }

    @Test
    public void testShouldReturnCorrectSpeed() {
        double speed = 2.0;
        Horse horse = new Horse("Horse", speed);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void testShouldReturnCorrectDistance() {
        double distance = 2.0;
        Horse horse = new Horse("Horse", 2.0, distance);
        assertEquals(distance, horse.getDistance());

        Horse horse1 = new Horse("Horse", 2.0);
        assertEquals(0, horse1.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.2, 0.5, 0.9, 1.0, 2.0, 5.0, 1000.0})
    public void testMoveAndGetRandomDoubleMethods(double a) {
        try (MockedStatic mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 4.0, 15.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(a);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(15 + 4*a, horse.getDistance());
        }
    }
}