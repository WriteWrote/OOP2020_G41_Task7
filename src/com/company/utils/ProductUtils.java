package com.company.utils;

import com.company.Supermarket;
import com.company.Warehouse;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;

import java.util.HashMap;
import java.util.Map;

public class ProductUtils {
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

    public static void decreaseExpirationDays(Supermarket supermarket) {
        for (Countable p : supermarket.getStock().getCountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
        for (Uncountable p :
                supermarket.getStock().getUncountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
        for (Countable p :
                supermarket.getShop().getCountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
        for (Uncountable p :
                supermarket.getShop().getUncountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
    }

    public static Warehouse generateProducts() {
        Warehouse admission = new Warehouse();
        Map<ProductType, Countable> countableMap = new HashMap<>();
        Map<ProductType, Uncountable> uncountableMap = new HashMap<>();
        int n = (int) (Math.random() * (10 - 3) + 1);
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            countableMap.put(type, new Countable(type.toString(), type,     //name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (5) + 1),           //partialWeight
                    (int) (Math.random() * (300 - 100)),       //quantity
                    ProductUtils.getExpirationDays(type)    //expirationDays
            ));
        }
        n = (int) (Math.random() * (10 - 3) + 1);
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            uncountableMap.put(type, new Uncountable(type.toString(), type, // name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (300 - 100)),           //weight
                    ProductUtils.getExpirationDays(type)    // expirationDays
            ));
        }
        admission.setCountableMap(countableMap);
        admission.setUncountableMap(uncountableMap);
        return admission;
    }
}