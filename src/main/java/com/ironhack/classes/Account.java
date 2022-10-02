package com.ironhack.classes;

import com.ironhack.enums.Industry;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private final int id;
    private static int counter = 0;
    private Industry industry;
    private long employeeCount;
    private String city;
    private String country;
    private List<Contact> contactList;
    private List<Opportunity> opportunityList;
//  Constructor
    public Account(Industry industry, long employeeCount, String city, String country) {
        this.id = counter++;
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        contactList = new ArrayList<>();
        opportunityList = new ArrayList<>();
    }
//  Getters
    public int getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Contact> getContactList() {

        return contactList;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }
//  Setters
    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addContactToList(Contact contact) {
        contactList.add(contact);
    }

    public void addOpportunityToList(Opportunity opportunity) {
        opportunityList.add(opportunity);
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "Account: id = " + id + ", industry=" + industry + ", employeeCount=" + employeeCount + ", city='" + city + ", country='" + country +
                "\n Contact List \n" + contactList + "\n Opportunity List \n" + opportunityList;
    }
}
