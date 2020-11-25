package com.company;

import com.company.utils.EventType;
import com.company.utils.EventService;

import java.util.LinkedList;
import java.util.Queue;

public class Supermarket {
    public Supermarket() {
    }

    public static void runSupermarket() {
        Warehouse stock = new Warehouse();
        Warehouse shoppingRoom = new Warehouse();
        Queue<Customer> customers = new LinkedList<>();
        Queue<EventType> events = new LinkedList<>();

        events.add(EventType.ProductsAdmission);
        events.add(EventService.castEvent());

        while (events.size() > 0) {
            if (events.size() > 1) {
                switch (events.poll()) {
                    case PriceFall: {
                        System.out.println("PriceFall");
                        EventService.priceFall(shoppingRoom);
                    }
                    break;
                    case JoiningCustomers: {
                        System.out.println("JoiningCustomers");
                        customers.addAll(EventService.generateCustomers());
                    }
                    break;
                    case DeleteExpProducts: {
                        System.out.println("deleteExpProducts");
                        EventService.deleteExpProducts(shoppingRoom);
                    }
                    break;
                    case ProductsAdmission: {
                        System.out.println("ProductAdmission");
                        Warehouse admission = EventService.generateProducts();
                        stock.getСountableMap().putAll(admission.getСountableMap());
                        stock.getUncountableMap().putAll(admission.getUncountableMap());
                    }
                    break;
                }
            } else {
                for (int i = 0; i < (int) (Math.random() * 50 + 1); i++)
                    events.add(EventService.castEvent());
            }
        }
    }
}
