package com.company;

import com.company.events.EventType;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Supermarket {
    private Warehouse stock = new Warehouse();
    private Warehouse shoppingRoom = new Warehouse();
    private List<Customer> customers = new LinkedList<>();
    private Queue<EventType> events = new LinkedList<>();

    public Supermarket() {
    }

    public Warehouse getStock() {
        return stock;
    }

    public Warehouse getShop() {
        return shoppingRoom;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Queue<EventType> getEvents() {
        return events;
    }
}
