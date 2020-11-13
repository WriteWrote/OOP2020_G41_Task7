package com.company.products;

public class Countable{
    private String name;
    private ProductType productType;
    private int price, partialWeight, quantity, expirationDays;

    public Countable(String name, ProductType productType, int price, int partialWeight, int quantity, int expirationDays) {
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.partialWeight = partialWeight;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public int getPartialWeight() {
        return partialWeight;
    }
}
