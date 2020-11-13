package com.company;

import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<ProductType, Countable> countableMap;
    private Map<ProductType, Uncountable> uncountableMap;

    public Warehouse() {
        countableMap = new HashMap<>();
        uncountableMap = new HashMap<>();
    }

    Countable getCountable(ProductType productType) {
        return countableMap.get(productType);
    }

    Uncountable getUncountable(ProductType productType) {
        return uncountableMap.get(productType);
    }

    public void addAll(Map<ProductType, Countable> countable, Map<ProductType, Uncountable> uncountable) {
        countableMap.putAll(countable);
        uncountableMap.putAll(uncountable);
    }

    public Map<ProductType, Countable> getCountables() {
        return countableMap;
    }

    public Map<ProductType, Uncountable> getUncountables() {
        return uncountableMap;
    }
}
