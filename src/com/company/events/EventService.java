package com.company.events;

import com.company.Customer;
import com.company.Supermarket;
import com.company.Warehouse;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;
import com.company.utils.ProductUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class EventService {
    public static void runSupermarket(Supermarket supermarket) throws InterruptedException {
        Queue<EventType> events = supermarket.getEvents();
        events.add(EventType.ProductsAdmission);
        //events.add(EventType.ProductsAdmission);
        //events.add(EventType.ProductsToTheShoppingRoom);
        events.add(EventType.JoiningCustomers);
        //events.add(EventType.PriceFall);
        //events.add(EventType.DeleteExpProducts);

        //events.add(EventService.castEvent());

        while (events.size() > 0) {
            //events.add(EventService.castEvent());
            //if (events.size() > 1) {
            switch (events.poll()) {
                case PriceFall: {
                    System.out.println("PriceFall");
                    EventService.priceFall(supermarket.getShop());
                }
                break;
                case JoiningCustomers: {
                    System.out.println("JoiningCustomers");
                    supermarket.getCustomers().addAll(EventService.generateCustomers());
                }
                break;
                case DeleteExpProducts: {
                    System.out.println("deleteExpProducts in shop");
                    EventService.deleteExpProducts(supermarket.getShop());
                    System.out.println("deleteExpProducts in stock");
                    EventService.deleteExpProducts(supermarket.getStock());
                }
                break;
                case ProductsAdmission: {
                    System.out.println("ProductAdmission");
                    Warehouse admission = EventService.generateProducts();
                    supermarket.getStock().getСountableMap().putAll(admission.getСountableMap());
                    supermarket.getStock().getUncountableMap().putAll(admission.getUncountableMap());
                }
                case ProductsToTheShoppingRoom: {
                    EventService.moveProductsToShop(supermarket.getStock(), supermarket.getShop());
                }
                break;
            }
            TimeUnit.SECONDS.sleep(1);
            /*} else {
                for (int i = 0; i < (int) (Math.random() * 50 + 1); i++)
                    events.add(EventService.castEvent());
            }*/
        }
    }

    public static LinkedList<Customer> generateCustomers() {
        List<Customer> list = new LinkedList<>();
        int n = (int) (Math.random() * (10 - 1));
        for (int i = 0; i < n; i++) {
            list.add(new Customer((int) (Math.random() * (3500 - 500))));
        }
        return new LinkedList<>();
    }

    public static Warehouse generateProducts() {
        Warehouse admission = new Warehouse();
        Map<ProductType, Countable> countableMap = new HashMap<>();
        Map<ProductType, Uncountable> uncountableMap = new HashMap<>();
        int n = (int) (Math.random() * (10 - 1));
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            countableMap.put(type, new Countable(type.toString(), type,     //name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (5 - 1)),           //partialWeight
                    (int) (Math.random() * (300 - 100)),       //quantity
                    ProductUtils.getExpirationDays(type)    //expirationDays
            ));
        }
        n = (int) (Math.random() * (10 - 1));
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            uncountableMap.put(type, new Uncountable(type.toString(), type, // name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (150 - 1)),           //weight
                    ProductUtils.getExpirationDays(type)    // expirationDays
            ));
        }
        admission.setCountableMap(countableMap);
        admission.setUncountableMap(uncountableMap);
        return admission;
    }

    public static void priceFall(Warehouse shoppingRoom) {
        for (Countable v :
                shoppingRoom.getСountableMap().values()) {
            if (v.getExpirationDays() < 5) {
                v.setPrice(v.getPrice() / 2);
            }
        }
        for (Uncountable v :
                shoppingRoom.getUncountableMap().values()) {
            if (v.getExpirationDays() < 50) {
                v.setPrice(v.getPrice() / 2);
            }
        }
    }

    public static void deleteExpProducts(Warehouse shoppingRoom) {
        shoppingRoom.getСountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0
                || entry.getValue().getQuantity() == 0);
        shoppingRoom.getUncountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0
                || entry.getValue().getWeight() == 0);
    }

    public static EventType castEvent() {
        return EventType.values()[(int) (Math.random() * EventType.values().length)];
    }

    public static void moveProductsToShop(Warehouse stock, Warehouse shop) {
        Iterator<Map.Entry<ProductType, Uncountable>> iUc = stock.getUncountableMap().entrySet().iterator();
        while (iUc.hasNext()) {
            Map.Entry<ProductType, Uncountable> entry = iUc.next();
            Uncountable description;
            // либо достаем из торгового зала уже существующую позицию
            if (shop.getUncountableMap().containsKey(entry.getKey())) {
                description = shop.getUncountableMap().get(entry.getKey());
            } else {
                //либо создаем новую, если такой нет
                description = new Uncountable(entry.getValue().getName(), entry.getValue().getProductType(),
                        entry.getValue().getPrice(), entry.getValue().getWeight(), entry.getValue().getExpirationDays());
            }
            // если на складе лежит больше 30 кг товара, то отщипываем от него
            if (entry.getValue().getWeight() > 30) {
                entry.getValue().setWeight(entry.getValue().getWeight() - 30);
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
                    description.setWeight(description.getWeight() + entry.getValue().getWeight());
                } catch (NullPointerException ex) {
                    description.setWeight(entry.getValue().getWeight());
                } finally {
                    entry.getValue().setWeight(0);
                }
            }
            // если description - измененная ссылка на объект, то put() заменит предыдущую запись собой
            shop.getUncountableMap().put(entry.getKey(), description);
        }

        Iterator<Map.Entry<ProductType, Countable>> iC = stock.getСountableMap().entrySet().iterator();
        while (iC.hasNext()) {
            Map.Entry<ProductType, Countable> entry = iC.next();
            Countable description;
            // либо достаем из торгового зала уже существующую позицию
            if (shop.getUncountableMap().containsKey(entry.getKey())) {
                int price = shop.getСountableMap().get(entry.getKey()).getPrice();
                int q = shop.getСountableMap().get(entry.getKey()).getQuantity();
                int exp = shop.getСountableMap().get(entry.getKey()).getExpirationDays();
                int partW = shop.getСountableMap().get(entry.getKey()).getPartialWeight();
                String name = shop.getСountableMap().get(entry.getKey()).getName();
                description = new Countable(name, shop.getСountableMap().get(entry.getKey()).getProductType(),
                        price, partW, q, exp);
            } else {
                //либо создаем новую, если такой нет
                description = new Countable(entry.getValue().getName(), entry.getValue().getProductType(),
                        entry.getValue().getPrice(), entry.getValue().getPartialWeight(),
                        0, entry.getValue().getExpirationDays());
            }
            // если на складе лежит больше 30 кг товара, то отщипываем от него
            if (entry.getValue().getQuantity() > 30) {
                entry.getValue().setQuantity(entry.getValue().getQuantity() - 30);
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
                    description.setQuantity(description.getQuantity() + entry.getValue().getQuantity());
                } catch (NullPointerException ex) {
                    description.setQuantity(entry.getValue().getQuantity());
                } finally {
                    entry.getValue().setQuantity(0);
                }
            }
            // если description - измененная ссылка на объект, то put() заменит предыдущую запись собой
            shop.getСountableMap().put(entry.getKey(), description);

        }
    }
}
