package com.company;

import com.company.events.EventType;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Supermarket {
    private Warehouse stock = new Warehouse();
    private Warehouse shop = new Warehouse();
    private List<Customer> customers = new LinkedList<>();
    private Queue<EventType> events = new LinkedList<>();

    public Supermarket() {
    }

    public Warehouse getStock() {
        return stock;
    }

    public Warehouse getShop() {
        return shop;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Queue<EventType> getEvents() {
        return events;
    }
}