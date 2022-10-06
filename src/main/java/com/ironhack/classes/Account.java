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
    private List<Contact> contactList = new ArrayList<>();
    private List<Opportunity> opportunityList = new ArrayList<>();

    public Account() {
        this.id = counter++;
    }

    //  Constructor with empty contact list and opportunity list
    public Account(String industry, long employeeCount, String city, String country) {
        this.id = counter++;
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        contactList = new ArrayList<>();
        opportunityList = new ArrayList<>();
    }

    //  Constructor with adding a contact and an opportunity to the lists
    public Account(String industry, long employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        this.id = counter++;
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        contactList.add(contact);
        opportunityList.add(opportunity);
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
    public void setIndustry(String industry) {
        industry = industry.toUpperCase();
        if (industry.equals("PRODUCE")
                || industry.equals("ECOMMERCE")
                || industry.equals("MANUFACTURING")
                || industry.equals("MEDICAL")
                || industry.equals("OTHER")) {
            this.industry = Industry.valueOf(industry);
        } else {
            throw new IllegalArgumentException("No such industry type found. Please enter PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER");
        }
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

    public static final Pattern VALID_PHONENUMBER_REGEX =
            Pattern.compile("\\A[0-9]{3}[0-9]{3}[0-9]{3}\\z", Pattern.CASE_INSENSITIVE);

    public static boolean validatePhone(String phoneStr) {
        Matcher matcher = VALID_PHONENUMBER_REGEX.matcher(phoneStr);
        return matcher.find();
    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "Account: id = " + id + ", industry= " + industry + ", employeeCount= " + employeeCount + ", city= " + city + ", country= " + country +
                "\n Contact List \n" + contactList + "\n Opportunity List \n" + opportunityList;
    }
}
