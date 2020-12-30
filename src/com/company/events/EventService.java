package com.company.events;

import com.company.*;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;
import com.company.utils.CustomerUtils;
import com.company.utils.OutputUtils;
import com.company.utils.ProductUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EventService {
    public void runSupermarket(Supermarket supermarket) throws InterruptedException {
        Queue<EventType> events = supermarket.getEvents();
        events.add(EventType.ADMISSION);
        OutputUtils printer = new OutputUtils();
        CustomerUtils customerUtils = new CustomerUtils();
        ProductUtils productUtils = new ProductUtils();

        while (events.size() > 0) {
            if (events.size() > 1) {
                switch (events.poll()) {
                    case PRICE_FALL: {
                        this.priceFall(supermarket.getShop());
                        printer.printResult(EventType.PRICE_FALL.toString(), supermarket.getShop());
                    }
                    break;
                    case NEW_CUSTOMER: {
                        int n = supermarket.getCustomers().size();
                        supermarket.getCustomers().addAll(customerUtils.generateCustomers());
                        printer.printCustomers(EventType.NEW_CUSTOMER.toString(), supermarket.getCustomers().size() - n, supermarket.getCustomers().size());
                    }
                    break;
                    case DELETE_EXP: {
                        this.deleteExpProducts(supermarket.getShop());
                        printer.printResult(EventType.DELETE_EXP.toString() + " in shop:", supermarket.getShop());
                        this.deleteExpProducts(supermarket.getStock());
                        printer.printResult(EventType.DELETE_EXP.toString() + " in stock:", supermarket.getStock());
                    }
                    break;
                    case ADMISSION: {
                        Storage admission = productUtils.generateProducts();
                        supermarket.getStock().getCountableMap().putAll(admission.getCountableMap());
                        supermarket.getStock().getUncountableMap().putAll(admission.getUncountableMap());
                        printer.printResult(EventType.ADMISSION.toString(), supermarket.getStock());
                    }
                    case MOVE_TO_SHOP: {
                        this.moveProductsToShop(supermarket.getStock(), supermarket.getShop());
                        printer.printResult(EventType.MOVE_TO_SHOP.toString(), supermarket.getShop());
                    }
                    break;
                }
                TimeUnit.SECONDS.sleep(2);
                int n = supermarket.getCustomers().size();
                customerUtils.serveCustomer(supermarket.getCustomers(), supermarket.getShop());
                printer.printCustomers(EventType.SERVE_CUSTOMER.toString(), supermarket.getCustomers().size() - n,
                        supermarket.getCustomers().size());
                productUtils.decreaseExpirationDays(supermarket);
                printer.printDayPassed();
            } else {
                for (int i = 0; i < (int) (Math.random() * 50 + 1); i++)
                    events.add(EventService.castEvent());
            }
        }
    }

    private void priceFall(Storage shoppingRoom) {
        for (Countable v :
                shoppingRoom.getCountableMap().values()) {
            if (v.getExpirationDays() < 15) {
                v.setPrice(v.getPrice() / 2);
                v.setDiscounted(true);
            }
        }
        for (Uncountable v :
                shoppingRoom.getUncountableMap().values()) {
            if (v.getExpirationDays() < 50) {
                v.setPrice(v.getPrice() / 2);
                v.setDiscounted(true);
            }
        }
    }

    private void deleteExpProducts(Storage shoppingRoom) {
        shoppingRoom.getCountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0
                || entry.getValue().getQuantity() <= 0);
        shoppingRoom.getUncountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0
                || entry.getValue().getWeight() <= 0);
    }

    private static EventType castEvent() {
        return EventType.values()[(int) (Math.random() * EventType.values().length)];
    }

    private void moveProductsToShop(Storage stock, Storage shop) {
        Iterator<Map.Entry<ProductType, Uncountable>> iUc = stock.getUncountableMap().entrySet().iterator();
        while (iUc.hasNext()) {
            Map.Entry<ProductType, Uncountable> entry = iUc.next();
            Uncountable description;
            Uncountable value = entry.getValue();
            ProductType key = entry.getKey();
            // либо достаем из торгового зала уже существующую позицию
            if (shop.getUncountableMap().containsKey(key)) {
                int price = shop.getUncountableMap().get(key).getPrice();
                int w = shop.getUncountableMap().get(key).getWeight();
                int exp = shop.getUncountableMap().get(key).getExpirationDays();
                String name = shop.getUncountableMap().get(key).getName();
                ProductType type = shop.getUncountableMap().get(key).getProductType();
                description = new Uncountable(name, type, price, w, exp);
            } else {
                //либо создаем новую, если такой нет
                description = new Uncountable(value.getName(), value.getProductType(),
                        value.getPrice(), 0, value.getExpirationDays());
            }
            // если на складе лежит больше 30 кг товара, то отщипываем от него
            if (value.getWeight() > 30) {
                value.setWeight(value.getWeight() - 30);
                // добавляем в описание, учитывая, что в описании могли быть до этого лишние 5 кило
                try {
                    description.setWeight(description.getWeight() + 30);
                } catch (NullPointerException ex) {
                    description.setWeight(30);
                }
            } else {
                // если на складе лежит меньше 30 кило, обнуляем количество товара
                // об удалении кончившегося товара позаботится другой метод
                try {
                    description.setWeight(description.getWeight() + value.getWeight());
                } catch (NullPointerException ex) {
                    description.setWeight(value.getWeight());
                } finally {
                    value.setWeight(0);
                }
            }
            // если description - измененная ссылка на объект, то put() заменит предыдущую запись собой
            shop.getUncountableMap().put(key, description);
        }

        Iterator<Map.Entry<ProductType, Countable>> iC = stock.getCountableMap().entrySet().iterator();
        while (iC.hasNext()) {
            Map.Entry<ProductType, Countable> entry = iC.next();
            Countable description;
            Countable value = entry.getValue();
            ProductType key = entry.getKey();
            // либо достаем из торгового зала уже существующую позицию
            if (shop.getCountableMap().containsKey(key)) {
                int price = shop.getCountableMap().get(key).getPrice();
                int q = shop.getCountableMap().get(key).getQuantity();
                int exp = shop.getCountableMap().get(key).getExpirationDays();
                int partW = shop.getCountableMap().get(key).getPartialWeight();
                String name = shop.getCountableMap().get(key).getName();
                ProductType type = shop.getCountableMap().get(key).getProductType();
                description = new Countable(name, type, price, partW, q, exp);
            } else {
                //либо создаем новую, если такой нет
                description = new Countable(value.getName(), value.getProductType(),
                        value.getPrice(), value.getPartialWeight(),
                        0, value.getExpirationDays());
            }
            // если на складе лежит больше 30 кг товара, то отщипываем от него
            if (value.getQuantity() > 30) {
                value.setQuantity(value.getQuantity() - 30);
                // добавляем в описание, учитывая, что в описании могли быть до этого лишние 5 кило
                try {
                    description.setQuantity(description.getQuantity() + 30);
                } catch (NullPointerException ex) {
                    description.setQuantity(30);
                }
            } else {
                // если на складе лежит меньше 30 кило, обнуляем количество товара
                // об удалении кончившегося товара позаботится другой метод
                try {
                    description.setQuantity(description.getQuantity() + value.getQuantity());
                } catch (NullPointerException ex) {
                    description.setQuantity(value.getQuantity());
                } finally {
                    value.setQuantity(0);
                }
            }
            // если description - измененная ссылка на объект, то put() заменит предыдущую запись собой
            shop.getCountableMap().put(key, description);
        }
    }
}