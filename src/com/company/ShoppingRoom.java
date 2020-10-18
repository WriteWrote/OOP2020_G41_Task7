package com.company;

import com.company.products.Description;
import com.company.products.Product;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ShoppingRoom {
    private Map<Product, Description> shelves;
    private Queue<Customer> customers;

    public ShoppingRoom() {
        shelves = new HashMap<>();
        customers = new LinkedList<>();
    }

    public ShoppingRoom(Map<Product, Description> shelves, Queue<Customer> customers) {
        this.shelves = shelves;
        this.customers = customers;
    }

    public Map<Product, Description> getShelves() {
        return shelves;
    }

    public Queue<Customer> getCustomers() {
        return customers;
    }

    public void setShelves(Map<Product, Description> shelves) {
        this.shelves = shelves;
    }

    void setCustomers(Queue<Customer> customers) {
        this.customers.addAll(customers);
    }

    public int getQuantity(Product product) {
        return shelves.get(product).getQuantity();
    }
}
