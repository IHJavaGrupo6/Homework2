package com.ironhack.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Menu {

    private static Scanner input = new Scanner(System.in);
    private static Map<Integer, Lead> leadMap = new HashMap<>();
    private static List<Contact> totalContacts = new ArrayList<>();
    private static List<Opportunity> totalOpportunities = new ArrayList<>();
    private static List<Account> accountList = new ArrayList<>();

    public static void mainMenu() {
        System.out.println("=========");
        System.out.println("\033[0;1mMAIN MENU\033[0;0m");
        System.out.println("\033[0;1mAvailable commands: \033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mNew lead \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mShow leads \u001B[0m\033[0;0m to show the list of existing leads");
        System.out.println("\u001B[36m    Existing leads: " + leadMap.keySet().size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mLook up lead + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
//        System.out.println("\033[0;1m• \u001B[34mShow opportunities \u001B[0m\033[0;0m to show the list of existing oppportunities (both open and closed)");
//        System.out.println("\u001B[36m    Existing opportunities: " + totalOpportunities.size() + "\u001B[0m");
//        System.out.println("\033[0;1m• \u001B[34mLook up opportunity + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
        System.out.println("\033[0;1m• \u001B[34mClose-Won + id \u001B[0m\033[0;0m to close an oportunity that ended with a sale ");
        System.out.println("\033[0;1m• \u001B[34mClose-Lost + id \u001B[0m\033[0;0m to close a lost oportunity");
        System.out.println("What do you want to do? ");
        try {
            getMethodInput();
        } catch (IllegalArgumentException e) {
            backToMainMenu(e);
        }
    }

    public static void getMethodInput() {
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank() || methodAndId == null) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        int id = parseInt(methodAndId.replaceAll("\\D+", ""));
        switch (method) {
            case "newlead":
                addNewLead();
                break;
            case "showleads":
                showLeads();
                break;
            case "lookuplead":
                try {
                    System.out.println(leadMap.get(id).toString());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                break;
            case "convert":
                try {
                    convertLead(id);
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                break;
            case "closewon":
                try {
                    totalOpportunities.get(id).setStatus(Status.CLOSED_WON);
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                break;
            case "closelost":
                try {
                    totalOpportunities.get(id).setStatus(Status.CLOSED_LOST);
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                break;
            default:
                throw new IllegalArgumentException("No such command found. Please enter a valid command!");
        }
    }

    public static String getAnswer(String question) {
        System.out.println(question);
        String answer = input.nextLine();
        if (answer.isBlank() || answer == null) {
            throw new IllegalArgumentException("Nothing received. Please enter at least one letter or number!");
        }
        return answer;
    }

    public static long getNumber(String question) {
        System.out.println(question);
        String numberString = input.nextLine().replaceAll("\\D+", "");
        if (numberString.isBlank() || numberString == null) {
            throw new IllegalArgumentException("No numbers received. Please enter at least one number!");
        }
        long number = parseLong(numberString);
        return number;
    }

    public static void addNewLead() {
        try {
            String name = getAnswer("Please enter the name of the new lead: ");
            long phoneNumber = getNumber("Please enter a phone number for the new lead: ");
            String email = getAnswer("Please enter an email for the new lead: ");
            String companyName = getAnswer("Please enter the name of the company for the new lead: ");
            Lead lead = new Lead(name, phoneNumber, email, companyName);
            leadMap.put(lead.getId(), lead);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println("Going back to the main menu.");
            mainMenu();
            // better to go to adding a new lead again or all the way back to the main menu?
            //System.err.println("Going back to adding a new lead.");
            //addNewLead();
        }
    }

    public static void convertLead(int id) {
        // step 1: fetching the lead
        Lead lead = null;
        try {
            lead = leadMap.get(id);
        } catch (IllegalArgumentException e) {
            backToMainMenu(e);
        }
        // step 2: creating a contact
        Contact contact = new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
        // step 3: creating an opportunity
        Opportunity opportunity = null;
        try {
            opportunity = newOpportunity();
            opportunity.setDecisionMaker(contact);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        }
        // step 4: creating an account
        Account account = null;
        try {
            account = addNewAccount();
        } catch (IllegalArgumentException e) {
            backToMainMenu(e);
        }
        account.getOpportunityList().add(opportunity);
        // step 5: adding the newly created objects to the lists and removing the lead
        totalContacts.add(contact);
        totalOpportunities.add(opportunity);
        accountList.add(account);
        leadMap.remove(id);
    }

    public static Opportunity newOpportunity() {
        Product product = null;
        long quantity = 0;
        try {
            product = getInputProductDelegate();
            quantity = getNumber("Please enter the number of trucks being considered for purchase: ");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            newOpportunity();
        }
        Opportunity opportunity = new Opportunity(product, quantity, null);
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

    public static Account addNewAccount() {
        Account account = null;
        try {
            Industry industry = getInputIndustryDelegate();
            long employeeCount = getNumber("Please enter the number of employees in the company: ");
            String city = getAnswer("PLease enter the city in which the company is based: ");
            String country = getAnswer("PLease enter the country in which the company is based: ");
            account = new Account(industry, employeeCount, city, country);
            accountList.add(account);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            addNewAccount();
        }
        return account;
    }

    public static Industry getInputIndustryDelegate() {
        String industryString = getAnswer("Please enter industry type: PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER").toUpperCase();
        Industry industry;
        if (industryString.equals("PRODUCE") || industryString.equals("ECOMMERCE") || industryString.equals("MANUFACTURING") || industryString.equals("MEDICAL") || industryString.equals("OTHER")) {
            industry = Industry.valueOf(industryString);
        } else {
            throw new IllegalArgumentException("No such industry type found. Please enter PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER");
        }
        return industry;
    }

    public static void showLeads() {
        System.out.println("\033[0;1m Existing leads: \033[0;0m");
        for (Lead lead : leadMap.values()) {
            System.out.println("•" + lead.toString());
        }
    }

    public static void backToMainMenu(IllegalArgumentException e) {
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

    public static List<Account> getAccountList() {
        return accountList;
    }

    public static void setAccountList(List<Account> accountList) {
        Menu.accountList = accountList;
    }
}
