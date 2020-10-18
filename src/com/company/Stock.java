package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    private List<Product> productList;
    private Map<ExampleProduct, Integer> capacityOfStock = new HashMap<>();

    public Stock() {
        capacityOfStock = setRandomCapacityOfStock();
        productList = generateBasicProducts();
    }

    private Map<ExampleProduct, Integer> setRandomCapacityOfStock() {
        HashMap<ExampleProduct, Integer> map = new HashMap<>();
        for (int i = 0; i < ExampleProduct.getSize(); i++) {
            int capacity = (int) (Math.random() * (500 - 100) + 100);
            map.put(ExampleProduct.getRandomName(), capacity);
        }
        return map;
    }

    private List<Product> generateBasicProducts() {
        List<Product> list = new ArrayList<>();
        for (var e :
                capacityOfStock.entrySet()) {
            // генерация объекта по имени класса
        }
        return new ArrayList<>();
    }
}
