package com.company.utils;

import com.company.Storage;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;

import java.util.Map;

public class OutputUtils {
    private void printWarehouse(Storage storage) {
        System.out.println("Product\tQuantity\tWeight\tPrice\tExpDays");
        for (Map.Entry<ProductType, Countable> entry :
                storage.getCountableMap().entrySet()) {
            System.out.println(entry.getKey().toString() + "\t\t" + entry.getValue().getQuantity() + "\t\t" + entry.getValue().getPartialWeight() +
                    "\t\t" + entry.getValue().getPrice() + "\t\t" + entry.getValue().getExpirationDays());
        }
        for (Map.Entry<ProductType, Uncountable> entry :
                storage.getUncountableMap().entrySet()) {
            System.out.println(entry.getKey().toString() + "\tNotC\t\t" + entry.getValue().getWeight() +
                    "\t\t" + entry.getValue().getPrice() + "\t\t" + entry.getValue().getExpirationDays());
        }
        System.out.println();
    }
    private void printLine() {
        for (int i = 0; i < 49; i++)
            System.out.print("-");
        System.out.println("-");
    }

    public void printResult(String event, Storage storage) {
        System.out.println(event);
        System.out.println();
        this.printWarehouse(storage);
        this.printLine();
    }

    public void printCustomers(String type, int diff, int after) {
        System.out.println(type + ": " + (diff > 0 ? "+ " : "") + diff + " customers");
        System.out.println("Now " + after + " customers in supermarket");
        this.printLine();
    }

    public void printDayPassed() {
        System.out.println("DAY PASSED");
        this.printLine();
    }
}
