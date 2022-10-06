import com.ironhack.classes.Lead;
import com.ironhack.classes.Menu;
import com.ironhack.classes.Opportunity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OpportunityTests {

    @Test
    @DisplayName("Testing setProduct works Ok")
    void setProduct_works(){
        Lead lead = new Lead("quim1",999888777,"mail@mail.com","company");
        Opportunity opportunity = new Opportunity("Hybrid", 20, Menu.newContact(lead));

        Assertions.assertDoesNotThrow(()->opportunity.setProduct("Hybrid"));
        Assertions.assertEquals("HYBRID", opportunity.getProduct().toString());
        Assertions.assertThrows(IllegalArgumentException.class, ()-> opportunity.setProduct("PacoPorras"));
    }
}
