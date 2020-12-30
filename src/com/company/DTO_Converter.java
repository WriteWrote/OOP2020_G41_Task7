package com.company;

import com.company.events.EventType;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DTO_Converter {
    private static Storage stock = new Storage();
    private Storage shop = new Storage();
    private List<Customer> customers = new LinkedList<>();
    private Queue<EventType> events = new LinkedList<>();

    public static void serializeStorage(){
        Map<ProductType, Countable> map = stock.getCountableMap();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.enableComplexMapKeySerialization().setPrettyPrinting().create();
        Type type = new TypeToken<Countable>(){}.getType();
    }

    public Storage getStock() {
        return stock;
    }

    public Storage getShop() {
        return shop;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Queue<EventType> getEvents() {
        return events;
    }
}
