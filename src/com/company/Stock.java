package com.company;

import com.company.products.Description;
import com.company.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<Product, Integer> stock;
    private int capacity = 500;

    public Stock() {
        stock = setBasicProducts(capacity);
    }

    public Stock(int capacity) {
        this.capacity = capacity;
        stock = setBasicProducts(capacity);
    }

    private Map<Product, Integer> setBasicProducts(int capacity) {
        HashMap<Product, Integer> map = new HashMap<>();
        for (var l :
                Product.values()) {
            int quantity = (int) (Math.random() * (capacity - 100) + 100);
            map.put(l, quantity);
        }
        return map;
    }

    Map<Product, Integer> getStock() {
        return stock;
    }

    int getCapacity() {
        return capacity;
    }

    Integer get(Product product) {
        return stock.get(product);
    }

    void addAll(Map<Product, Integer> newMap) {
        this.stock.putAll(newMap);
    }

    void add(Product name, int quantity) {

        this.stock.put(name, quantity);
    }
}
