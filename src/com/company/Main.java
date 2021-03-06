package com.company;

import com.company.events.EventService;
import com.google.gson.Gson;
import hashset.OOP.obukhovaEV.main.multiset.IMultiSet;
import hashset.OOP.obukhovaEV.main.multiset.SimpleHashMultiSet;
import hashset.OOP.obukhovaEV.main.set.ISet;
import hashset.OOP.obukhovaEV.main.set.SimpleHashSet;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world! Starting work");

        ISet<String> testSet = new SimpleHashSet<>();
        testSet.add("test");
        testSet.add("test2");
        testSet.remove("test2");
        testSet.clear();
        IMultiSet<String> testMultiSet = new SimpleHashMultiSet<>();
        testMultiSet.add("a",5);
        testMultiSet.add("d");
        testMultiSet.add("d");
        testMultiSet.add("s");
        testSet = testMultiSet.toSet();

        Supermarket supermarket;
        EventService service = new EventService();
        try (FileReader reader = new FileReader("./src/main/resources/save.txt")) {
            Scanner scn = new Scanner(reader);
            StringBuilder text = new StringBuilder();
            while (scn.hasNextLine()
            ) {
                text.append(scn.nextLine());
            }
            if (!"".contentEquals(text))
                supermarket = new Gson().fromJson(String.valueOf(text), Supermarket.class);
            else supermarket = new Supermarket();
            service.runSupermarket(supermarket);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}