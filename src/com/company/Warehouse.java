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

    /*Countable getCountableProduct(ProductType productType) {
        return countableMap.get(productType);
    }*/

    /*Uncountable getUncountableProduct(ProductType productType) {
        return uncountableMap.get(productType);
    }*/

    public void addAll(Map<ProductType, Countable> countable, Map<ProductType, Uncountable> uncountable) {
        countableMap.putAll(countable);
        uncountableMap.putAll(uncountable);
    }

    public void setCountableMap(Map<ProductType, Countable> countableMap) {
        this.countableMap = countableMap;
    }

    public void setUncountableMap(Map<ProductType, Uncountable> uncountableMap) {
        this.uncountableMap = uncountableMap;
    }

    public Map<ProductType, Countable> getСountableMap() {
        return countableMap;
    }

    public Map<ProductType, Uncountable> getUncountableMap() {
        return uncountableMap;
    }
}
