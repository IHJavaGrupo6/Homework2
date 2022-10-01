package com.ironhack.classes;

public class Opportunity {
    private final int id;
    private static int contador = 0;
    private long quantity;
    private Product product;
    private Status status;

    private Contact decisionMaker;

    public Opportunity(long quantity, Product product, Contact decisionMaker) {
        this.id = contador++;
        setQuantity(quantity);
        setProduct(product);
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

    public void setProduct(Product product) {
        this.product = product;
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
}
