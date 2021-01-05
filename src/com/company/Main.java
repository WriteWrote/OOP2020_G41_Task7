package com.company;

import com.company.events.EventService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world! Starting work");
        Supermarket supermarket = new Supermarket();
        EventService service = new EventService();
        try (FileReader reader = new FileReader("./src/main/resources/save.txt")) {
            Scanner scn = new Scanner(reader);
            StringBuilder text = new StringBuilder();
            while (scn.hasNextLine()
            ) {
                text.append(scn.nextLine());
            }
            supermarket = new Gson().fromJson(String.valueOf(text), Supermarket.class);

            service.runSupermarket(supermarket);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}