package com.company.events;

import com.company.*;
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
        events.add(EventType.DeleteExpProducts);
        events.add(EventType.JoiningCustomers);

        while (events.size() > 0) {
            //events.add(EventService.castEvent());
            if (events.size() > 1) {
                switch (events.poll()) {
                    case PriceFall: {
                        System.out.println("PriceFall");
                        System.out.println();
                        EventService.priceFall(supermarket.getShop());
                        printWarehouse(supermarket.getShop());
                        System.out.println("-------------------------------------------------------------------------------------------");
                    }
                    break;
                    case JoiningCustomers: {
                        int n = supermarket.getCustomers().size();
                        supermarket.getCustomers().addAll(EventService.generateCustomers());
                        n = supermarket.getCustomers().size() - n;
                        System.out.println("JoiningCustomers: + " + n + " customers");
                        System.out.println("Now " + supermarket.getCustomers().size() + " customers in supermarket");
                        System.out.println("-------------------------------------------------------------------------------------------");
                    }
                    break;
                    case DeleteExpProducts: {
                        System.out.println("Deleting ExpProducts in shop:");
                        System.out.println();
                        EventService.deleteExpProducts(supermarket.getShop());
                        printWarehouse(supermarket.getShop());
                        System.out.println("Deleting ExpProducts in stock");
                        System.out.println();
                        EventService.deleteExpProducts(supermarket.getStock());
                        printWarehouse(supermarket.getStock());
                        System.out.println("-------------------------------------------------------------------------------------------");
                    }
                    break;
                    case ProductsAdmission: {
                        System.out.println("ProductAdmission");
                        System.out.println();
                        Warehouse admission = EventService.generateProducts();
                        supermarket.getStock().getCountableMap().putAll(admission.getCountableMap());
                        supermarket.getStock().getUncountableMap().putAll(admission.getUncountableMap());
                        printWarehouse(supermarket.getStock());
                        System.out.println("-------------------------------------------------------------------------------------------");
                    }
                    case ProductsMovingToTheShoppingRoom: {
                        System.out.println("Moving Products to Shopping Room:");
                        System.out.println();
                        EventService.moveProductsToShop(supermarket.getStock(), supermarket.getShop());
                        printWarehouse(supermarket.getShop());
                        System.out.println("-------------------------------------------------------------------------------------------");
                    }
                    break;
                }
                TimeUnit.SECONDS.sleep(2);
                EventService.decreaseExpirationDays(supermarket);
                System.out.println("DAY PASSED");
                System.out.println("-------------------------------------------------------------------------------------------");
                //ResultFrame.setCurrentEvent(EventType.ServingCustomer);
                serveCustomer(supermarket.getCustomers(), supermarket.getShop());
                System.out.println("Serving customer");
                System.out.println("Now " + supermarket.getCustomers().size() + " customers in supermarket");
                System.out.println("-------------------------------------------------------------------------------------------");
                //ResultFrame.setCustomers(supermarket.getCustomers());
            } else {
                for (int i = 0; i < (int) (Math.random() * 50 + 1); i++)
                    events.add(EventService.castEvent());
            }
        }
    }

    private static List<Customer> generateCustomers() {
        List<Customer> list = new LinkedList<>();
        int n = (int) (Math.random() * (10 - 1) + 1);
        for (int i = 0; i < n; i++) {
            list.add(new Customer((int) (Math.random() * (3500 - 500))));
        }
        return list;
    }

    private static Warehouse generateProducts() {
        Warehouse admission = new Warehouse();
        Map<ProductType, Countable> countableMap = new HashMap<>();
        Map<ProductType, Uncountable> uncountableMap = new HashMap<>();
        int n = (int) (Math.random() * (10 - 3) + 1);
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            countableMap.put(type, new Countable(type.toString(), type,     //name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (5) + 1),           //partialWeight
                    (int) (Math.random() * (300 - 100)),       //quantity
                    ProductUtils.getExpirationDays(type)    //expirationDays
            ));
        }
        n = (int) (Math.random() * (10 - 3) + 1);
        for (int i = 0; i < n; i++) {
            ProductType type = ProductUtils.getRandomProductType();
            uncountableMap.put(type, new Uncountable(type.toString(), type, // name, type
                    (int) (Math.random() * (1000 - 50)),         //price
                    (int) (Math.random() * (300 - 100)),           //weight
                    ProductUtils.getExpirationDays(type)    // expirationDays
            ));
        }
        admission.setCountableMap(countableMap);
        admission.setUncountableMap(uncountableMap);
        return admission;
    }

    private static void priceFall(Warehouse shoppingRoom) {
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

    private static void deleteExpProducts(Warehouse shoppingRoom) {
        shoppingRoom.getCountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0
                || entry.getValue().getQuantity() == 0);
        shoppingRoom.getUncountableMap().entrySet().removeIf(entry -> entry.getValue().getExpirationDays() <= 0
                || entry.getValue().getWeight() == 0);
    }

    public static EventType castEvent() {
        return EventType.values()[(int) (Math.random() * EventType.values().length)];
    }

    private static void moveProductsToShop(Warehouse stock, Warehouse shop) {
        Iterator<Map.Entry<ProductType, Uncountable>> iUc = stock.getUncountableMap().entrySet().iterator();
        while (iUc.hasNext()) {
            Map.Entry<ProductType, Uncountable> entry = iUc.next();
            Uncountable description;
            // либо достаем из торгового зала уже существующую позицию
            if (shop.getUncountableMap().containsKey(entry.getKey())) {
                int price = shop.getUncountableMap().get(entry.getKey()).getPrice();
                int w = shop.getUncountableMap().get(entry.getKey()).getWeight();
                int exp = shop.getUncountableMap().get(entry.getKey()).getExpirationDays();
                String name = shop.getUncountableMap().get(entry.getKey()).getName();
                description = new Uncountable(name, shop.getUncountableMap().get(entry.getKey()).getProductType(),
                        price, w, exp);
            } else {
                //либо создаем новую, если такой нет
                description = new Uncountable(entry.getValue().getName(), entry.getValue().getProductType(),
                        entry.getValue().getPrice(), 0, entry.getValue().getExpirationDays());
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

        Iterator<Map.Entry<ProductType, Countable>> iC = stock.getCountableMap().entrySet().iterator();
        while (iC.hasNext()) {
            Map.Entry<ProductType, Countable> entry = iC.next();
            Countable description;
            // либо достаем из торгового зала уже существующую позицию
            if (shop.getCountableMap().containsKey(entry.getKey())) {
                int price = shop.getCountableMap().get(entry.getKey()).getPrice();
                int q = shop.getCountableMap().get(entry.getKey()).getQuantity();
                int exp = shop.getCountableMap().get(entry.getKey()).getExpirationDays();
                int partW = shop.getCountableMap().get(entry.getKey()).getPartialWeight();
                String name = shop.getCountableMap().get(entry.getKey()).getName();
                description = new Countable(name, shop.getCountableMap().get(entry.getKey()).getProductType(),
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
            shop.getCountableMap().put(entry.getKey(), description);

        }
    }

    private static void decreaseExpirationDays(Supermarket supermarket) {
        for (Countable p : supermarket.getStock().getCountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
        for (Uncountable p :
                supermarket.getStock().getUncountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
        for (Countable p :
                supermarket.getShop().getCountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
        for (Uncountable p :
                supermarket.getShop().getUncountableMap().values()) {
            p.setExpirationDays(p.getExpirationDays() - 1);
        }
    }

    private static void serveCustomer(List<Customer> customers, Warehouse shop) {
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer c = iterator.next();
            for (Map.Entry<ProductType, Integer> entry :
                    c.getScaleOfDesires().entrySet()) {
                //if (entry.getValue() > 5) {
                    if (shop.getCountableMap().containsKey(entry.getKey())
                            && entry.getValue() > 0) {
                        int q = shop.getCountableMap().get(entry.getKey()).getPartialWeight();
                        int Q = shop.getCountableMap().get(entry.getKey()).getQuantity();
                        shop.getCountableMap().get(entry.getKey()).setQuantity(q < entry.getValue() ? Q - (entry.getValue() / q + 1) : Q - q);
                        entry.setValue(q < entry.getValue() ? Q - (entry.getValue() / q + 1) : Q - q);
                    } else if (shop.getUncountableMap().containsKey(entry.getKey())
                            && entry.getValue() > 0) {
                        int Q = shop.getUncountableMap().get(entry.getKey()).getWeight();
                        shop.getUncountableMap().get(entry.getKey()).setWeight(Q < entry.getValue() ? 0 : Q - entry.getValue());
                        entry.setValue(Q < entry.getValue() ? 0 : Q - entry.getValue());
                    }
                /*} else {
                    if (shop.getCountableMap().containsKey(entry.getKey())
                            && shop.getCountableMap().get(entry.getKey()).isDiscounted()
                            && entry.getValue() > 0) {
                        int q = shop.getCountableMap().get(entry.getKey()).getPartialWeight();
                        int Q = shop.getCountableMap().get(entry.getKey()).getQuantity();
                        shop.getCountableMap().get(entry.getKey()).setQuantity(q < entry.getValue() ? Q - (entry.getValue() / q + 1) : Q - q);
                        entry.setValue(q < entry.getValue() ? Q - (entry.getValue() / q + 1) : Q - q);
                    } else if (shop.getUncountableMap().containsKey(entry.getKey())
                            && shop.getUncountableMap().get(entry.getKey()).isDiscounted()
                            && entry.getValue() > 0) {
                        int Q = shop.getUncountableMap().get(entry.getKey()).getWeight();
                        shop.getUncountableMap().get(entry.getKey()).setWeight(Q < entry.getValue() ? 0 : Q - entry.getValue());
                        entry.setValue(Q < entry.getValue() ? 0 : Q - entry.getValue());
                    }
                }*/
            }
            iterator.remove();
        }
    }

    private static void printWarehouse(Warehouse warehouse) {
        System.out.println("Product\tQuantity\tWeight\tPrice\tExpDays");
        for (Map.Entry<ProductType, Countable> entry :
                warehouse.getCountableMap().entrySet()) {
            System.out.println(entry.getKey().toString() + "\t\t" + entry.getValue().getQuantity() + "\t\t" + entry.getValue().getPartialWeight() +
                    "\t\t" + entry.getValue().getPrice() + "\t\t" + entry.getValue().getExpirationDays());
        }
        for (Map.Entry<ProductType, Uncountable> entry :
                warehouse.getUncountableMap().entrySet()) {
            System.out.println(entry.getKey().toString() + "\tNotC\t\t" + entry.getValue().getWeight() +
                    "\t\t" + entry.getValue().getPrice() + "\t\t" + entry.getValue().getExpirationDays());
        }
        System.out.println();
    }
}