package com.company.products;

public class Uncountable{
    private String name;
    private ProductType productType;
    private int price, weight, expirationDays;

    public Uncountable(String name, ProductType productType, int price, int weight, int expirationDays) {
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.weight = weight;
        this.expirationDays = expirationDays;
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getPrice() {
        return price;
    }

    public int getExpirationDays() {
        return expirationDays;
    }

    public int getWeight() {
        return weight;
    }
}
