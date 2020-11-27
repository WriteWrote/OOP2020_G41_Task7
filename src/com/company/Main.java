package com.company;

import com.company.events.EventService;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        Supermarket supermarket = new Supermarket();
        try {
            EventService.runSupermarket(supermarket);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}