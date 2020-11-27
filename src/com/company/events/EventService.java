package com.company.events;

import com.company.*;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;
import com.company.utils.CustomerUtils;
import com.company.utils.OutputUtils;
import com.company.utils.ProductUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class EventService {
    public static void runSupermarket(Supermarket supermarket) throws InterruptedException {
        Queue<EventType> events = supermarket.getEvents();
        events.add(EventType.ProductsAdmission);
        events.add(EventType.DeleteExpiredProducts);
        events.add(EventType.JoiningCustomers);

        while (events.size() > 0) {
            if (events.size() > 1) {
                switch (events.poll()) {
                    case PriceFall: {
                        EventService.priceFall(supermarket.getShop());
                        OutputUtils.printResult(EventType.PriceFall.toString(), supermarket.getShop());
                    }
                    break;
                    case JoiningCustomers: {
                        int n = supermarket.getCustomers().size();
                        supermarket.getCustomers().addAll(CustomerUtils.generateCustomers());
                        OutputUtils.printCustomers(EventType.JoiningCustomers.toString(), supermarket.getCustomers().size() - n, supermarket.getCustomers().size());
                    }
                    break;
                    case DeleteExpiredProducts: {
                        EventService.deleteExpProducts(supermarket.getShop());
                        OutputUtils.printResult(EventType.DeleteExpiredProducts.toString() + " in shop:", supermarket.getShop());
                        EventService.deleteExpProducts(supermarket.getStock());
                        OutputUtils.printResult(EventType.DeleteExpiredProducts.toString() + " in stock:", supermarket.getStock());
                    }
                    break;
                    case ProductsAdmission: {
                        Warehouse admission = ProductUtils.generateProducts();
                        supermarket.getStock().getCountableMap().putAll(admission.getCountableMap());
                        supermarket.getStock().getUncountableMap().putAll(admission.getUncountableMap());
                        OutputUtils.printResult(EventType.ProductsAdmission.toString(), supermarket.getStock());
                    }
                    case MovingProductsToTheShoppingRoom: {
                        EventService.moveProductsToShop(supermarket.getStock(), supermarket.getShop());
                        OutputUtils.printResult(EventType.MovingProductsToTheShoppingRoom.toString(), supermarket.getShop());
                    }
                    break;
                }
                TimeUnit.SECONDS.sleep(2);
                ProductUtils.decreaseExpirationDays(supermarket);
                OutputUtils.printDayPassed();
                int n = supermarket.getCustomers().size();
                CustomerUtils.serveCustomer(supermarket.getCustomers(), supermarket.getShop());
                OutputUtils.printCustomers(EventType.ServingCustomer.toString(), supermarket.getCustomers().size() - n,
                        supermarket.getCustomers().size());
            } else {
                for (int i = 0; i < (int) (Math.random() * 50 + 1); i++)
                    events.add(EventService.castEvent());
            }
        }
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
}