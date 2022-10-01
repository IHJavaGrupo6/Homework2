package com.ironhack.classes;

public class Contact{
    private static int counter2 = 0;
    private final int id2;
    private String name;
    private long phoneNumber;
    private String email;
    private String companyName;

    public Contact(String name, long phoneNumber, String email, String companyName) {
        this.id2 = counter2++;
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    public int getId2() {
        return id2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Contact: id = " + getId2() + ", name = " + getName() + ", phoneNumber = " + getPhoneNumber() +
                ", email = " + getEmail() + ", companyName = " + getCompanyName() + "\n";
    }
}
