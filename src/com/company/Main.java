package com.company;

import com.company.events.EventService;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;
import com.company.serialise_utils.StorageSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world! Starting work");
        Supermarket supermarket = new Supermarket();
        EventService service = new EventService();
        try {
            Type storageCountableType = new TypeToken<Map<ProductType, Countable>>() {
            }.getType();
            Type storageUncountableType = new TypeToken<Map<ProductType, Uncountable>>() {
            }.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(storageCountableType, new StorageSerializer())
                    .registerTypeAdapter(storageUncountableType, new StorageSerializer())
                    .setPrettyPrinting()
                    .create();
            String json = gson.toJson(supermarket);
            service.runSupermarket(supermarket);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}