package com.ironhack.classes;

import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import com.ironhack.enums.Status;

import java.util.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Menu {

    private static Scanner input;
    private static Map<Integer, Lead> leadMap = new HashMap<>();
    private static List<Contact> totalContacts = new ArrayList<>();
    private static List<Opportunity> totalOpportunities = new ArrayList<>();
    private static List<Account> totalAccounts = new ArrayList<>();

    public static void mainMenu() {
        System.out.println("=========");
        System.out.println("\033[0;1mMAIN MENU\033[0;0m");
        System.out.println("\033[0;1mAvailable commands: \033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mNew lead \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mShow leads \u001B[0m\033[0;0m to show the list of existing leads");
        System.out.println("\u001B[36m    Existing leads: " + leadMap.keySet().size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mLook up lead + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
        System.out.println("\033[0;1m• \u001B[34mConvert lead + id \u001B[0m\033[0;0m to find a lead by its id number and convert it into a new opportunity");
        System.out.println("\033[0;1m• \u001B[34mShow opportunities \u001B[0m\033[0;0m to show the list of existing opportunities (both open and closed)");
        System.out.println("\u001B[36m    Existing opportunities: " + totalOpportunities.size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mLook up opportunity + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
        System.out.println("\033[0;1m• \u001B[34mClose-Won + id \u001B[0m\033[0;0m to close an oportunity that ended with a sale ");
        System.out.println("\033[0;1m• \u001B[34mClose-Lost + id \u001B[0m\033[0;0m to close a lost oportunity");
        System.out.println("\033[0;1m• \u001B[34mExit \u001B[0m\033[0;0m");
        System.out.println("What do you want to do? ");
        try {
            getMethodInput();
        } catch (IllegalArgumentException e) {
            backToMainMenu(e);
        }
    }

    public static void getMethodInput() {
        input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        int id = 0;
        try {
            id = parseInt(methodAndId.replaceAll("\\D+", ""));
        } catch (NumberFormatException ignored) {
        }
        switch (method) {
            case "newlead":
                newLead();
                mainMenu();
            case "showleads":
                showLeads();
                mainMenu();
            case "lookuplead":
                try {
                    System.out.println(leadMap.get(id).toString());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "convert":
                try {
                    convertLead(id);
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "showopportunities":
                showOpportunities();
                mainMenu();
            case "lookupopportunity":
                try {
                    System.out.println(totalOpportunities.get(id).toString());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "closewon":
                try {
                    totalOpportunities.get(id).setStatus(Status.CLOSED_WON);
                    System.out.println(totalOpportunities.get(id));
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "closelost":
                try {
                    totalOpportunities.get(id).setStatus(Status.CLOSED_LOST);
                    System.out.println(totalOpportunities.get(id));
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "exit":
                System.out.println("Good bye!");
                System.exit(0);
            default:
                throw new IllegalArgumentException("No such command found. Please enter a valid command!");
        }
    }

    public static String getAnswer(String question) {
        input = new Scanner(System.in);
        System.out.println(question);
        String answer = input.nextLine();
        if (answer.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at least one letter or number!");
        }
        return answer;
    }

    public static long getNumber(String question) {
        input = new Scanner(System.in);
        System.out.println(question);
        String numberString = input.nextLine().replaceAll("\\D+", "");
        if (numberString.isBlank()) {
            throw new IllegalArgumentException("No numbers received. Please enter at least one number!");
        }
        return parseLong(numberString);
    }

    public static void newLead() {
        System.out.println("Creating a new lead: ");
        try {
            String name = getAnswer("Please enter the name of the new lead: ");
            long phoneNumber = getNumber("Please enter a phone number for the new lead: ");
            if(!Account.validatePhone(String.valueOf(phoneNumber))) throw new IllegalArgumentException("Invalid phone format");
            String email = getAnswer("Please enter an email for the new lead: ");
            if(!Account.validate(email)) throw new IllegalArgumentException("Invalid email format");
            String companyName = getAnswer("Please enter the name of the company for the new lead: ");
            Lead lead = new Lead(name, phoneNumber, email, companyName);
            leadMap.put(lead.getId(), lead);
            System.out.println("New lead created: ");
            System.out.println(lead);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println("Going back to new Lead creation.");
            newLead();
        }
    }

    public static void convertLead(int id) {
        // step 1: fetching the lead
        Lead lead;
        Contact contact = null;
        try {
            lead = leadMap.get(id);
            // step 2: creating a contact
            contact = new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
            totalContacts.add(contact);
            System.out.println("Lead converted into a new contact: ");
            System.out.println(contact);
        } catch (IllegalArgumentException | NullPointerException e) {
            backToMainMenu(e);
        }
        // step 3: creating an opportunity
        try {
            newOpportunity();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        // step 4: creating an account
        try {
            newAccount();
        } catch (IllegalArgumentException | NullPointerException e) {
            backToMainMenu(e);
        }
        // step 5: adding the newly created objects to the lists and removing the lead
        //totalContacts.add(contact);
        //totalOpportunities.add(opportunity);
        //accountList.add(account);
        leadMap.remove(id);
    }

    public static Opportunity newOpportunity() {
        System.out.println("Creating a new opportunity: ");
        Product product;
        long quantity;
        Opportunity opportunity = null;
        try {
            product = getInputProductDelegate();
            quantity = getNumber("Please enter the number of trucks being considered for purchase: ");
            opportunity = new Opportunity(product, quantity, totalContacts.get(totalContacts.size()-1));
            totalOpportunities.add(opportunity);
            System.out.println("Created a new opportunity: ");
            System.out.println(opportunity);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            newOpportunity();
        }
        return opportunity;
    }

    public static Product getInputProductDelegate() {
        String productString = getAnswer("Please enter product type: HYBRID, FLATBED or BOX").toUpperCase();
        Product product;
        if (productString.equals("HYBRID") || productString.equals("FLATBED") || productString.equals("BOX")) {
            product = Product.valueOf(productString);
        } else {
            throw new IllegalArgumentException("No such product type found. Please enter HYBRID, FLATBED or BOX");
        }
        return product;
    }

    public static Account newAccount() {
        System.out.println("Creating a new account: ");
        Industry industry;
        long employeeCount;
        String city;
        String country;
        Account account = null;
        try {
            industry = getInputIndustryDelegate();
            employeeCount = getNumber("Please enter the number of employees in the company: ");
            city = getAnswer("PLease enter the city in which the company is based: ");
            country = getAnswer("PLease enter the country in which the company is based: ");
            account = new Account(industry, employeeCount, city, country);
            account.addOpportunityToList(totalOpportunities.get(totalOpportunities.size()-1));
            account.addContactToList(totalContacts.get(totalContacts.size()-1));
            totalAccounts.add(account);
            System.out.println("Created a new account: ");
            System.out.println(account);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            newAccount();
        }
        return account;
    }

    public static Industry getInputIndustryDelegate() {
        String industryString = getAnswer("Please enter industry type: PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER").toUpperCase();
        Industry industry;
        if (industryString.equals("PRODUCE")
                || industryString.equals("ECOMMERCE")
                || industryString.equals("MANUFACTURING")
                || industryString.equals("MEDICAL")
                || industryString.equals("OTHER")) {
            industry = Industry.valueOf(industryString);
        } else {
            throw new IllegalArgumentException("No such industry type found. Please enter PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER");
        }
        return industry;
    }

    public static void showLeads() {
        System.out.println("\033[0;1m Existing leads: \033[0;0m\n");
        for (Lead lead : leadMap.values()) {
            System.out.println("•" + lead.toString());
        }
    }

    public static void showOpportunities() {
        System.out.println("\033[0;1m Existing opportunities: \033[0;0m\n");
        for (Opportunity opportunity : totalOpportunities) {
            System.out.println("•" + opportunity.toString());
        }
    }

    public static void backToMainMenu(Exception e) {
        System.err.println(e.getMessage());
        System.err.println("Going back to the main menu.");
        mainMenu();
    }


    public static Map<Integer, Lead> getLeadMap() {
        return leadMap;
    }

    public static void setLeadMap(Map<Integer, Lead> leadMap) {
        Menu.leadMap = leadMap;
    }

    public static List<Contact> getTotalContacts() {
        return totalContacts;
    }

    public static void setTotalContacts(List<Contact> totalContacts) {
        Menu.totalContacts = totalContacts;
    }

    public static List<Opportunity> getTotalOpportunities() {
        return totalOpportunities;
    }

    public static void setTotalOpportunities(List<Opportunity> totalOpportunities) {
        Menu.totalOpportunities = totalOpportunities;
    }

    public static List<Account> getTotalAccounts() {
        return totalAccounts;
    }

    public static void setTotalAccounts(List<Account> totalAccounts) {
        Menu.totalAccounts = totalAccounts;
    }
}
