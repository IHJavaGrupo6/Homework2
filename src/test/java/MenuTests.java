import com.ironhack.classes.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MenuTests {

    Scanner input;

    @BeforeEach
    void setUp() {

        input = new Scanner(System.in);
    }

    @Test
    @DisplayName("getMethodInput() throws exception with empty input")
    void getMethodInput_throwsIllegalArgumentException() {


        String userInput = " ";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, Menu::getMethodInput);

    }

    @Test
    @DisplayName("getMethodInput() throws exception wrong input")
    void getMethodInput_throwsIllegalArgumentException2() {


        String userInput = "blabla";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, Menu::getMethodInput);

    }

    @Test
    @DisplayName("getAnswer() throws exception empty input")
    void getAnswer_throwsIllegalArgumentException() {


        String userInput = " ";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, () -> Menu.getAnswer(" "));

    }

    @Test
    @DisplayName("getNumber() throws exception empty input")
    void getNumber_throwsIllegalArgumentException() {


        String userInput = " ";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, () -> Menu.getNumber(" "));

    }

    @Test
    @DisplayName("getAnswer returns a String")
    void getAnswer_returnsString() {


        String userInput = "answer";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        String expected = "answer";
        String actual = Menu.getAnswer(" ");
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("getNumber() returns a Long")
    void getNumber_returnsLong() {


        String userInput = "666888999";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        Long expected = 666888999L;
        Long actual = Menu.getNumber(" ");
        assertEquals(expected, actual);

    }
}
