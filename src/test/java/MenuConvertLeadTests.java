import com.ironhack.classes.*;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MenuConvertLeadTests {

    Lead lead1;
    Lead lead2;
    Lead lead3;
    Lead lead4;
    Map<Integer, Lead> leadMap;

    @BeforeEach
    void setUp(){
        lead1 = new Lead("quim1",999888777,"mail@mail.com","company");
        lead2 = new Lead("quim2",999888777,"mail@mail.com","company");
        lead3 = new Lead("quim3",999888777,"mail@mail.com","company");
        lead4 = new Lead("quim4",999888777,"mail@mail.com","company");
        leadMap = new HashMap<>();
        leadMap.put(lead1.getId(),lead1);
        leadMap.put(lead2.getId(),lead2);
        leadMap.put(lead3.getId(),lead3);
        leadMap.put(lead4.getId(),lead4);
    }

    @Test
    @DisplayName("Creates a new contact from a lead correctly")
    void newContact_worksOK(){
        assertEquals(lead1.getName(), Menu.newContact(lead1).getName());
        assertEquals(lead1.getPhoneNumber(), Menu.newContact(lead1).getPhoneNumber());
        assertEquals(lead1.getEmail(), Menu.newContact(lead1).getEmail());
        assertEquals(lead1.getCompanyName(), Menu.newContact(lead1).getCompanyName());
    }

    @Test
    @DisplayName("Creates a new opportunity correctly")
    void newOpportunity_worksOK(){
        Opportunity opportunity = Menu.newOpportunity("box", 35, Menu.newContact(lead1));
        assertEquals(Product.BOX, opportunity.getProduct());
        assertEquals(35, opportunity.getQuantity());
        assertEquals(Menu.newContact(lead1).getName(), opportunity.getDecisionMaker().getName());
    }

    @Test
    @DisplayName("Creates an account correctly")
    void newAccount_worksOK(){
        Account account = Menu.newAccount("other", 300, "Bangladesh", "India",
                Menu.newContact(lead1), Menu.newOpportunity("box", 35, Menu.newContact(lead1)));
        assertEquals(Industry.OTHER, account.getIndustry());
        assertEquals(300, account.getEmployeeCount());
        assertEquals("Bangladesh", account.getCity());
        assertEquals("India", account.getCountry());
        assertEquals(1, account.getContactList().size());
        assertEquals(1, account.getOpportunityList().size());
    }

}
