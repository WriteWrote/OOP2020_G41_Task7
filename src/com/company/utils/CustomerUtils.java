package com.company.utils;

import com.company.products.ProductType;

import java.util.HashMap;
import java.util.Map;

public class CustomerUtils {
    public static Map<ProductType, Integer> generateSimpleDesires() {
        HashMap<ProductType, Integer> scaleOfDesires = new HashMap<>();
        int n = (int) (Math.random() * (ProductUtils.getCountOfProductTypes() - 3));
        for (int i = 0; i < n; i++) {
            scaleOfDesires.put(ProductUtils.getRandomProductType(), (int) (Math.random() * (9 - 3)));
        }
        return scaleOfDesires;
    }
}
