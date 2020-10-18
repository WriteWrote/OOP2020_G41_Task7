package com.company.products;

public enum Product {
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

    public static Product getRandomName() {
        return values()[(int) (Math.random() * values().length)];
    }

    public static int getSize() {
        return values().length;
    }

    public int getExpirationDays() {
        if (this.equals(Bread) || this.equals(Sweeties))
            return 14;
        if (this.equals(Cheese))
            return 40;
        if (this.equals(Vegetables) || this.equals(Meat) || this.equals(Sausage))
            return 30;
        if (this.equals(Drink))
            return 21;
        if (this.equals(Fish))
            return 90;
        if (this.equals(Milk))
            return 10;
        if (this.equals(Seafood))
            return 60;
        if (this.equals(Spices))
            return 365;
        return 3650;
    }
}
