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

    public Map<Product, Integer> getStock() {
        return stock;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Product name, int quantity) {
        //if (stock.containsKey(name)){
        //       int prevQ =

        //}
        this.stock.put(name, quantity);
    }
}
