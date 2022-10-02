import com.ironhack.classes.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static java.awt.SystemColor.menu;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuNewLeadTest {
    /*@Test
    @DisplayName("lalla")
    void newlead_worksOK(){

        String simulatedUserInput = "name" + System.getProperty("line.separator")
                + "832999777" + System.getProperty("line.separator")
                + "mail@mail.com" + System.getProperty("line.separator")
                + "company name" + System.getProperty("line.separator");

        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(outPut);
        System.setOut(print);
        Menu.newLead();
        String[] lines = outPut.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertEquals("Lead: id = 0, name = name, phoneNumber = 832999777, email = mail@mail.com, companyName = company name\n", actual);

        System.setIn(savedStandardInputStream);
    }*/
}
