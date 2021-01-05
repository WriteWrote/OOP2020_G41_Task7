package com.company.services;

import com.company.Supermarket;
import com.company.Storage;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProductService {
    private final Map<ProductType, Integer> expMap = initExpMap();

    ProductType getRandomProductType() {
        return ProductType.values()[(int) (Math.random() * (ProductType.values().length - 1))];
    }

    int getCountOfProductTypes() {
        return ProductType.values().length;
    }

    private int getExpirationDays(ProductType productType) {
        return expMap.get(productType);
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

    private Map<ProductType, Integer> initExpMap() {
        Map<ProductType, Integer> result = new HashMap<>();
        try (FileReader reader = new FileReader("./src/main/resources/products.txt")) {
            Scanner scn = new Scanner(reader);
            String text = "";
            while (scn.hasNextLine()
            ) {
                text = text.concat(scn.nextLine() + "\n");
            }
            String[] parts = text.split("\n");
            for (String s :
                    parts) {
                String[] pair = s.split(" ");
                result.put(ProductType.valueOf(pair[0].toUpperCase()), Integer.parseInt(pair[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}