package com.company.utils;

import com.company.products.ProductType;

import java.util.HashMap;
import java.util.Map;

public class ProductUtils {
//    private Map<ProductType, Integer> expirationDays = new HashMap<>();
    public static ProductType getRandomName() {
        return ProductType.values()[(int) (Math.random() * ProductType.values().length)];
    }

    public static int getSize() {
        return ProductType.values().length;
    }

    public int getExpirationDays() {
        if (this.equals(ProductType.Bread) || this.equals(ProductType.Sweeties))
            return 14;
        if (this.equals(ProductType.Cheese))
            return 40;
        if (this.equals(ProductType.Vegetables) || this.equals(ProductType.Meat) || this.equals(ProductType.Sausage))
            return 30;
        if (this.equals(ProductType.Drink))
            return 21;
        if (this.equals(ProductType.Fish))
            return 90;
        if (this.equals(ProductType.Milk))
            return 10;
        if (this.equals(ProductType.Seafood))
            return 60;
        if (this.equals(ProductType.Spices))
            return 365;
        return 3650;
    }
}
