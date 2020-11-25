package com.company.utils;

import com.company.Customer;
import com.company.Warehouse;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;

import java.util.*;

public class EventService {

    public static LinkedList<Customer> generateCustomers() {
        List<Customer> list = new LinkedList<>();
        int n = (int) (Math.random() * (10 - 1));
        for (int i = 0; i < n; i++) {
            list.add(new Customer((int) (Math.random() * (3500 - 500))));
        }
        return new LinkedList<>();
    }

    public static Warehouse generateProducts() {
        Warehouse admission = new Warehouse();
        Map<ProductType, Countable> countableMap = new HashMap<>();
        Map<ProductType, Uncountable> uncountableMap = new HashMap<>();
        int n = (int) (Math.random() * (10 - 1));
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            countableMap.put(type, new Countable(type.toString(), type,     //name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (5 - 0.2)),           //weight
                    (int) (Math.random() * (300 - 100)),       //partialWeight
                    ProductUtils.getExpirationDays(type)    //expirationDays
            ));
        }
        n = (int) (Math.random() * (10 - 1));
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            uncountableMap.put(type, new Uncountable(type.toString(), type, // name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (5 - 0.2)),           //weight
                    ProductUtils.getExpirationDays(type)    // expirationDays
            ));
        }
        admission.setCountableMap(countableMap);
        admission.setUncountableMap(uncountableMap);
        return admission;
    }

    public static void priceFall(Warehouse shoppingRoom) {
        for (Countable v :
                shoppingRoom.getСountableMap().values()) {
            if (v.getExpirationDays() < 5) {
                v.setPrice(v.getPrice() / 2);
            }
        }
        for (Uncountable v :
                shoppingRoom.getUncountableMap().values()) {
            if (v.getExpirationDays() < 50) {
                v.setPrice(v.getPrice() / 2);
            }
        }
    }

    public static void deleteExpProducts(Warehouse shoppingRoom) {
        shoppingRoom.getСountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0);
        shoppingRoom.getUncountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0);
    }

    public static EventType castEvent() {
        return EventType.values()[(int) (Math.random() * EventType.values().length)];
    }
}
