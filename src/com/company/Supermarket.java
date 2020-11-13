package com.company;

import com.company.utils.EventType;
import com.company.utils.EventUtils;

import java.util.LinkedList;
import java.util.Queue;

public class Supermarket {
    public static void runSupermarket() {
        Warehouse stock = new Warehouse();
        Warehouse shoppingRoom = new Warehouse();
        Queue<Customer> customers = new LinkedList<>();
        Queue<EventType> events = new LinkedList<>();

        events.add(EventUtils.castEvent());

        while (events.size() > 0) {
            if (events.size() > 1) {
                switch (events.poll()) {
                    case PriceFall:
                        break;
                    case JoiningCustomers:
                        break;
                    case DeleteExpProducts:
                        break;
                    case ProductsAdmission:
                        break;
                }
            } else {
                for (int i = 0; i < (int) (Math.random() * 50 + 1); i++)
                    events.add(EventUtils.castEvent());
            }
        }
    }
}
