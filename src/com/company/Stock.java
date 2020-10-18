package com.company;

import com.company.products.Product;
import com.company.products.Description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    private List<Description> productList;
    private Map<Product, Integer> capacityOfStock = new HashMap<>();

    public Stock() {
        capacityOfStock = setRandomCapacityOfStock();
        productList = generateBasicProducts();
    }

    private Map<Product, Integer> setRandomCapacityOfStock() {
        HashMap<Product, Integer> map = new HashMap<>();
        for (int i = 0; i < Product.getSize(); i++) {
            int capacity = (int) (Math.random() * (500 - 100) + 100);
            map.put(Product.getRandomName(), capacity);
        }
        return map;
    }

    private List<Description> generateBasicProducts() {
        List<Description> list = new ArrayList<>();
        for (var e :
                capacityOfStock.entrySet()) {
            // генерация объекта по имени класса
        }
        return new ArrayList<>();
    }
}
