package com.company.serialise_utils;

import com.company.Supermarket;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SupermarketSerializer implements JsonSerializer<Supermarket> {
    @Override
    public JsonElement serialize(Supermarket supermarket, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
