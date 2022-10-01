package com.ironhack.classes;

public class Contact extends Lead{
    private static int counter2 = 0;
    private final int id2;

    public Contact(String name, Long phoneNumber, String email, String companyName) {
        super(name, phoneNumber, email, companyName);
        this.id2 = counter2++;
    }

    public int getId2() {
        return id2;
    }

    @Override
    public String toString() {
        return "Contact: id = " + getId2() + ", name = " + getName() + ", phoneNumber = " + getPhoneNumber() +
                ", email = " + getEmail() + ", companyName = " + getCompanyName() + "\n";
    }
}
