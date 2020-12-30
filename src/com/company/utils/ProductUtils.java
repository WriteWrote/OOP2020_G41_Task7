package com.company.utils;

import com.company.Supermarket;
import com.company.Storage;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;

import java.util.HashMap;
import java.util.Map;

public class ProductUtils {
    ProductType getRandomProductType() {
        return ProductType.values()[(int) (Math.random() * (ProductType.values().length - 1))];
    }

    int getCountOfProductTypes() {
        return ProductType.values().length;
    }

    private int getExpirationDays(ProductType productType) {
        if (productType.equals(ProductType.BREAD) || productType.equals(ProductType.SWEETIES))
            return 14;
        if (productType.equals(ProductType.CHEESE))
            return 40;
        if (productType.equals(ProductType.VEGETABLES) || productType.equals(ProductType.MEAT) || productType.equals(ProductType.SAUSAGE))
            return 30;
        if (productType.equals(ProductType.DRINK))
            return 21;
        if (productType.equals(ProductType.FISH))
            return 90;
        if (productType.equals(ProductType.MILK))
            return 10;
        if (productType.equals(ProductType.SEAFOOD))
            return 60;
        if (productType.equals(ProductType.SPICES))
            return 365;
        return 3650;
    }

    public void decreaseExpirationDays(Supermarket supermarket) {
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

    public Storage generateProducts() {
        Storage admission = new Storage();
        Map<ProductType, Countable> countableMap = new HashMap<>();
        Map<ProductType, Uncountable> uncountableMap = new HashMap<>();
        int n = (int) (Math.random() * (10 - 3) + 1);
        for (int i = 0; i < n; i++) {
            ProductType type = this.getRandomProductType();
            countableMap.put(type, new Countable(type.toString(), type,     //name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (5) + 1),           //partialWeight
                    (int) (Math.random() * (300 - 100)),       //quantity
                    this.getExpirationDays(type)    //expirationDays
            ));
        }
        n = (int) (Math.random() * (10 - 3) + 1);
        for (int i = 0; i < n; i++) {
            ProductType type = this.getRandomProductType();
            uncountableMap.put(type, new Uncountable(type.toString(), type, // name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (300 - 100)),           //weight
                    this.getExpirationDays(type)    // expirationDays
            ));
        }
        admission.setCountableMap(countableMap);
        admission.setUncountableMap(uncountableMap);
        return admission;
    }
}