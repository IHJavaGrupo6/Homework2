import com.ironhack.classes.Account;
import com.ironhack.classes.Lead;
import com.ironhack.classes.Menu;
import com.ironhack.classes.Opportunity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTests {

    @Test
    @DisplayName("Test setIndustry works")
    void setIndustry_works() {
        Lead lead = new Lead("quim1", 999888777, "mail@mail.com", "company");
        Opportunity opportunity = new Opportunity("Hybrid", 20, Menu.newContact(lead));
        Account account = new Account("OTHER", 10, "Cambrils", "Españita", Menu.newContact(lead), opportunity);

        Assertions.assertDoesNotThrow(()->account.setIndustry("PRODUCE"));
        Assertions.assertEquals("PRODUCE", account.getIndustry().toString());
        Assertions.assertThrows(IllegalArgumentException.class, ()-> account.setIndustry("MariaTeresa"));
    }
}
