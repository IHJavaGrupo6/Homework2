package com.ironhack;

public class Opportunity {
    private int id;
    private int quantity;
    private Product product;
    private Status status;

    public Opportunity(int id, int quantity, Product product, Status status) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
}
