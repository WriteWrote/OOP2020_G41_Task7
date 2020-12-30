package com.company.serialise_utils;

import com.company.products.ProductType;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class StorageSerializer implements JsonSerializer<ProductType> {
    @Override
    public JsonElement serialize(ProductType productType, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
