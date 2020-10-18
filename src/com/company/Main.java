package com.company;

import com.company.products.Countable;
import com.company.products.Product;
import com.company.products.Uncountable;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello world!");

        /*Papers paper = new Papers("Magazine", 0, 100, 150);
        ExampleProduct n = ExampleProduct.Papers;
        String c = paper.getClass().toString();
        String s = c.substring(c.length() - n.toString().length(), c.length());
        String n2 = n.toString();
        System.out.println(n.toString().equals(s));

        YoungFemale girl = new YoungFemale();
        for (var l :
                girl.getScaleOfDesires().entrySet()) {
            System.out.println(l.getKey() + " - " + l.getValue());
        }*/
        Stock stock = new Stock();
        Uncountable milk = (Uncountable) Product.Milk.getDescription(100, 100);
        if (stock.getCapacity() - stock.getStock().get(milk.getName()) < milk.getWeight()) {
            stock.add(milk.getName(), stock.getCapacity());
        } else {
            int a = stock.getStock().get(milk.getName());
            stock.add(milk.getName(), a + milk.getWeight());
        }
    }
}
