package com.ironhack.classes;

import com.ironhack.enums.Product;
import com.ironhack.enums.Status;

public class Opportunity {
    private final int id;
    private static int counter3 = 0;
    private long quantity;
    private Product product;
    private Status status;

    private Contact decisionMaker;

    public Opportunity() {
        this.id = counter3++;
        setStatus(Status.OPEN);
    }

    public Opportunity(String product, long quantity, Contact decisionMaker) {
        this.id = counter3++;
        setProduct(product);
        setQuantity(quantity);
        setStatus(Status.OPEN);
        setDecisionMaker(decisionMaker);
    }

    public int getId() {
        return id;
    }


    public long getQuantity() {

        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(String product) {
        product = product.toUpperCase();
        if (product.equals("HYBRID") || product.equals("FLATBED") || product.equals("BOX")) {
            this.product = Product.valueOf(product);
        } else {
            throw new IllegalArgumentException("No such product type found. Please enter HYBRID, FLATBED or BOX");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    @Override
    public String toString() {
        return "Opportunity: id = " + id + ", product = " + product + ", trucks quantity = " + quantity + ", status = " + status +
                "\n Decision maker " + decisionMaker;
    }
}
