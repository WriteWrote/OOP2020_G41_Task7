package com.company;

import com.company.events.EventService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Supermarket supermarket = new Supermarket();
        try {
            EventService.runSupermarket(supermarket);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
