package com.company;

public enum ExampleProduct {
    Alcohol,
    Bread,
    Cheese,
    Clothes,
    Drink,
    Fish,
    Meat,
    Milk,
    Papers,
    Sausage,
    Seafood,
    Spices,
    Sweeties,
    Toiletry,
    Vegetables;

    public static ExampleProduct getRandomName() {
        return values()[(int)(Math.random()*values().length)];
    }
    public static int getSize(){
        return values().length;
    }
}
