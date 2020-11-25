package com.company.utils;

import com.company.products.ProductType;

import java.util.HashMap;
import java.util.Map;

public class ServiceUtils {
    public static Map<ProductType, Integer> generateSimpleDesires() {
        HashMap<ProductType, Integer> scaleOfDesires = new HashMap<>();
        for (int i = 0; i < Math.random() * ProductUtils.getCountOfProductTypes(); i++) {
            scaleOfDesires.put(ProductUtils.getRandomProductType(), (int)(Math.random()*9+1));
        }
        return scaleOfDesires;
    }
}
