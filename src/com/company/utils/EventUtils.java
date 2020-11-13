package com.company.utils;

import com.company.Customer;
import com.company.products.ProductType;

import java.util.*;

public class EventUtils {

    public static LinkedList<Customer> joiningCustomers() {

        return new LinkedList<>();
    }

    public static Map<ProductType, Integer> productsAdmission() {

        return new HashMap<>();
    }

    public static void priceFall() {

    }

    public static void deleteExpProducts() {

    }

    public static EventType castEvent() {
        return EventType.values()[(int) (Math.random() * ProductType.values().length)];
    }
}
