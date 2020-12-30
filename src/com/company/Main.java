package com.company;

import com.company.events.EventService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world! Starting work");
        Supermarket supermarket = new Supermarket();
        EventService service = new EventService();
        try (FileReader reader = new FileReader("src/main/java/startGame")) {
            Scanner scn = new Scanner(reader);
            StringBuilder json = new StringBuilder();
            while (scn.hasNext()) {
                //System.out.print(scn.next());
                json.append(scn.next());
            }
            service.runSupermarket(supermarket);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}