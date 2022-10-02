import com.ironhack.classes.Lead;
import com.ironhack.classes.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



import static org.junit.jupiter.api.Assertions.*;


public class MenuTests {

    @Test
    @DisplayName("getMethodInput() throws exception with empty input")
    void getMethodInput_throwsIllegalArgumentException() {


        String userInput = " ";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, () -> Menu.getMethodInput());

    }

    @Test
    @DisplayName("getMethodInput() throws exception wrong input")
    void getMethodInput_throwsIllegalArgumentException2() {


        String userInput = "blabla";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, () -> Menu.getMethodInput());

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
        Long expected = Long.valueOf(666888999);
        Long actual = Menu.getNumber(" ");
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("getInputProductDelegate() throws exception wrong input")
    void getInputProductDelegate_throwsIllegalArgumentException() {


        String userInput = "blabla";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, () -> Menu.getInputProductDelegate());

    }

    @Test
    @DisplayName("getInputIndustryDelegate() throws exception wrong input")
    void getInputIndustryDelegate_throwsIllegalArgumentException() {


        String userInput = "blabla";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(IllegalArgumentException.class, () -> Menu.getInputIndustryDelegate());

    }
}