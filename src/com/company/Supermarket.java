package com.company;

public class Supermarket {
    public static void runSupermarket() {
        Stock stock = new Stock();
        ShoppingRoom shoppingRoom = new ShoppingRoom();
        stock.addAll(Events.productsAdmission());
        shoppingRoom.setCustomers(Events.newCustomersComing());
        Events.priceFall();
        Events.dayPassing();
    }
}
