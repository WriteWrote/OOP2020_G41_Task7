package com.company.utils.serializers;

import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Map;

public class UncountableMapSerializer implements JsonSerializer<Map<ProductType, Uncountable>> {
    @Override
    public JsonElement serialize(Map<ProductType, Uncountable> uncountableMap, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        for (Map.Entry<ProductType, Uncountable> entry : uncountableMap.entrySet()
        ) {
            result.add(entry.getKey().toString(), context.serialize(entry.getValue()));
        }
        return result;
    }
}
