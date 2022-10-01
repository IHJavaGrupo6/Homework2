package com.ironhack.classes;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final int id;
    private static int counter = 0;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Contact> contactList = new ArrayList<Contact>();
    private List<Oportunity> oportunityList = new ArrayList<Oportunity>();
//  Constructor
    public Account(Industry industry, int employeeCount, String city, String country, Contact contact, Oportunity oportunity) {
        this.id = counter;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        setContactList(contact);
        setOportunityList(oportunity);
        counter++;
    }
//  Getters
    public int getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public int getEmployeeCount() {
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

    public List<Oportunity> getOportunityList() {
        return oportunityList;
    }
//  Setters
    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setContactList(Contact contact) {
        contactList.add(contact);
    }

    public void setOportunityList(Oportunity oportunity) {
        oportunityList.add(oportunity);
    }
}
