package com.company.utils;

import com.company.Customer;
import com.company.products.ProductType;

import java.util.*;

public class EventUtils {
    static void eventService() {

    }

    static LinkedList<Customer> joiningCustomers() {

        return new LinkedList<>();
    }

    static Map<ProductType, Integer> productsAdmission() {

        return new HashMap<>();
    }

    static void priceFall() {

    }

    static EventType addEvent() {
        return EventType.values()[(int) (Math.random() * ProductType.values().length)];
    }
}
