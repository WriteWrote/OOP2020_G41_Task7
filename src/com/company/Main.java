package com.company;

import com.company.events.EventService;
import com.company.events.EventType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.internal.org.jline.utils.Log;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world! Starting work");
        Supermarket supermarket = new Supermarket();
        EventService service = new EventService();
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Log.info("GSON", gson.toJson(supermarket));
            service.runSupermarket(supermarket);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}