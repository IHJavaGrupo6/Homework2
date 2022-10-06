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
        System.out.println("\033[0;1m• \u001B[34mConvert + id \u001B[0m\033[0;0m to find a lead by its id number and convert it into a new opportunity");
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
                Lead lead = null;
                boolean repeatLead = true;
                while (repeatLead) {
                    try {
                        String name = getAnswer("Please enter the name of the new lead: ");
                        Long number = getNumber("Please enter a phone number for the new lead: ");
                        if (!Account.validatePhone(String.valueOf(number)))
                            throw new IllegalArgumentException("Invalid phone format");
                        String mail = getAnswer("Please enter an email for the new lead: ");
                        if (!Account.validate(mail)) throw new IllegalArgumentException("Invalid email format");
                        String company = getAnswer("Please enter the name of the company for the new lead: ");
                        lead = newLead(name, number, mail, company);
                        repeatLead = false;
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        System.err.println("Going back to new lead creation");
                    }
                }
                leadMap.put(lead.getId(), lead);
                mainMenu();
            case "showleads":
                try {
                    showLeads(getLeadMap());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                mainMenu();
            case "lookuplead":
                try {
                    System.out.println(leadMap.get(id).toString());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }

                mainMenu();
            case "convert":
                boolean repeatConvertLead = true;
                while (repeatConvertLead) {
                    try {
                        convertLead(id);
                        repeatConvertLead = false;
                    } catch (IllegalArgumentException e) {
                        backToMainMenu(e);
                    }
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
        if (answer.isBlank() || answer.replaceAll("\\d+", "").isBlank()) {
            throw new IllegalArgumentException("No text received. Please enter at least one letter!");
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

    public static Lead newLead(String name, long phoneNumber, String email, String company) {
        System.out.println("Creating a new lead: ");
        if (!Account.validatePhone(String.valueOf(phoneNumber)))
            throw new IllegalArgumentException("Invalid phone format");
        if (!Account.validate(email)) throw new IllegalArgumentException("Invalid email format");
        Lead lead = new Lead(name, phoneNumber, email, company);
        System.out.println("New lead created: ");
        System.out.println(lead);
        return lead;
    }

    public static void convertLead(int id) {
        // step 1: fetching the lead
        if (id < 0 || id >= leadMap.size()) throw new IllegalArgumentException("No lead found with this id!");
        // step 2: creating a contact
        Contact contact = null;
        contact = newContact(leadMap.get(id));
        // step 3: creating an opportunity
        Opportunity opportunity = null;
        boolean repeatOpportunity = true;
        while (repeatOpportunity) {
            try {
                System.out.println("Creating a new opportunity: ");
                opportunity = newOpportunity(getAnswer("Please enter product type: HYBRID, FLATBED or BOX"),
                        getNumber("Please enter the number of trucks considered for purchase: "), contact);
                repeatOpportunity = false;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
        // step 4: creating an account
        Account account = null;
        boolean repeatAccount = true;
        while (repeatAccount) {
            try {
                System.out.println("Creating a new account: ");
                account = newAccount(getAnswer("Please enter industry type: PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER"),
                        getNumber("Please enter the number of employees in the company: "),
                        getAnswer("PLease enter the city in which the company is based: "),
                        getAnswer("PLease enter the country in which the company is based: "), contact, opportunity);
                repeatAccount = false;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
        // step 5: adding the newly created objects to the lists and removing the lead
        totalContacts.add(contact);
        totalOpportunities.add(opportunity);
        totalAccounts.add(account);
        leadMap.remove(id);
    }

    public static Contact newContact(Lead lead) {
        Contact contact = new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
        System.out.println("Lead converted into a new contact: ");
        System.out.println(contact);
        return contact;
    }

    public static Opportunity newOpportunity(String product, long quantity, Contact contact) {
        Opportunity opportunity = new Opportunity(product, quantity, contact);
        System.out.println("Created a new opportunity: ");
        System.out.println(opportunity);
        return opportunity;
    }

    public static Account newAccount(String industry, long employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        Account account = new Account(industry, employeeCount, city, country, contact, opportunity);
        System.out.println("Created a new account: ");
        System.out.println(account);
        return account;
    }

    public static void showLeads(Map<Integer, Lead> leadMap) {
        if (leadMap.isEmpty()) {
            throw new IllegalArgumentException("There are no leads to show.");
        } else {
            System.out.println("\033[0;1m Existing leads: \033[0;0m\n");
            for (Lead lead : leadMap.values()) {
                System.out.println("•" + lead.toString());
            }
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
