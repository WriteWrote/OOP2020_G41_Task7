package com.company.utils.serializers;

import com.company.products.Countable;
import com.company.products.ProductType;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;

public class CountableMapSerializer implements JsonSerializer<Map<ProductType, Countable>> {
    @Override
    public JsonElement serialize(Map<ProductType, Countable> countableMap, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        for (Map.Entry<ProductType, Countable> entry : countableMap.entrySet()
        ) {
            result.add(entry.getKey().toString(), context.serialize(entry.getValue()));
        }
        return result;
    }
}
