import com.ironhack.classes.Lead;
import com.ironhack.classes.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MenuNewLeadTests {
    @Test
    @DisplayName("Created a new Lead correctly")
    void newLead_worksOK(){
        Assertions.assertEquals("quim",Menu.newLead("quim",999888777,"mail@mail.com","company").getName());
        Assertions.assertThrows(IllegalArgumentException.class,()->Menu.newLead("",999888777,"fsdgds","company"));
        Assertions.assertThrows(IllegalArgumentException.class,()->Menu.newLead("",0,"fsdgds@mail.com","company"));

    }

    @Test
    @DisplayName("Showing Leads correctly ")
    void showLeads_worksOK(){
        Lead lead1 = new Lead("quim",999888777,"mail@mail.com","company");
        Lead lead2 = new Lead("quim",999888777,"mail@mail.com","company");
        Lead lead3 = new Lead("quim",999888777,"mail@mail.com","company");
        Lead lead4 = new Lead("quim",999888777,"mail@mail.com","company");
        Map<Integer, Lead> leadMap = new HashMap<>();
        leadMap.put(lead1.getId(),lead1);
        leadMap.put(lead2.getId(),lead2);
        leadMap.put(lead3.getId(),lead3);
        leadMap.put(lead4.getId(),lead4);
        assertDoesNotThrow(()->Menu.showLeads(leadMap));

        Map<Integer, Lead> leadMap1 = new HashMap<>();
        assertThrows(IllegalArgumentException.class,()->Menu.showLeads(leadMap1));
    }




}
