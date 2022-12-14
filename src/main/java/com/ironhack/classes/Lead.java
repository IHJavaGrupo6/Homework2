package com.ironhack.classes;

public class Lead {

    private final int id;
    private static int counter1 = 0;
    private String name;
    private long phoneNumber;
    private String email;
    private String companyName;

    public Lead(String name, long phoneNumber, String email, String companyName) {
        this.id = counter1++;
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    public int getId() {
        return id;
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
        return "Lead: id = " + getId() + ", name = " + name + ", phoneNumber = " + phoneNumber +
                ", email = " + email + ", companyName = " + companyName + "\n";
    }
}
