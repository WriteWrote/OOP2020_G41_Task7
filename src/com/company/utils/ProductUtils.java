package com.company.utils;

import com.company.products.ProductType;

import java.util.HashMap;
import java.util.Map;

public class ProductUtils {
    //    private Map<ProductType, Integer> expirationDays = new HashMap<>();
    public static ProductType getRandomProductType() {
        return ProductType.values()[(int) (Math.random() * (ProductType.values().length - 1))];
    }

    public static int getCountOfProductTypes() {
        return ProductType.values().length;
    }

    public static int getExpirationDays(ProductType productType) {
        if (productType.equals(ProductType.Bread) || productType.equals(ProductType.Sweeties))
            return 14;
        if (productType.equals(ProductType.Cheese))
            return 40;
        if (productType.equals(ProductType.Vegetables) || productType.equals(ProductType.Meat) || productType.equals(ProductType.Sausage))
            return 30;
        if (productType.equals(ProductType.Drink))
            return 21;
        if (productType.equals(ProductType.Fish))
            return 90;
        if (productType.equals(ProductType.Milk))
            return 10;
        if (productType.equals(ProductType.Seafood))
            return 60;
        if (productType.equals(ProductType.Spices))
            return 365;
        return 3650;
    }
}
