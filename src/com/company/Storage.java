package com.company;

import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<ProductType, Countable> countableMap;
    private Map<ProductType, Uncountable> uncountableMap;

    public Storage() {
        countableMap = new HashMap<>();
        uncountableMap = new HashMap<>();
    }

    public void setCountableMap(Map<ProductType, Countable> countableMap) {
        this.countableMap = countableMap;
    }

    public void setUncountableMap(Map<ProductType, Uncountable> uncountableMap) {
        this.uncountableMap = uncountableMap;
    }

    public Map<ProductType, Countable> getCountableMap() {
        return countableMap;
    }

    public Map<ProductType, Uncountable> getUncountableMap() {
        return uncountableMap;
    }
}