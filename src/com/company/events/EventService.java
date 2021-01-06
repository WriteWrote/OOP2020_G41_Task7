package com.company.events;

import com.company.*;
import com.company.products.Countable;
import com.company.products.ProductType;
import com.company.products.Uncountable;
import com.company.services.CustomerService;
import com.company.utils.OutputUtils;
import com.company.services.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class EventService {
    public void runSupermarket(Supermarket supermarket) throws InterruptedException {
        Queue<EventType> events = supermarket.getEvents();
        events.add(EventType.ADMISSION);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String jsonSaving;

        Map<EventType, Consumer<Supermarket>> reactEventMap = initEvents();

        while (events.size() > 0) {
            if (events.size() > 1) {
                Consumer<Supermarket> consumer = reactEventMap.get(events.poll());
                consumer.accept(supermarket);
                /*
                switch (events.poll()) {
                    case PRICE_FALL: {
                        this.priceFall(supermarket.getShop());
                        printer.printResult(EventType.PRICE_FALL.toString(), supermarket.getShop());
                    }
                    break;
                    case NEW_CUSTOMER: {
                        int n = supermarket.getCustomers().size();
                        supermarket.getCustomers().addAll(customerService.generateCustomers());
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
                        Storage admission = productService.generateProducts();
                        supermarket.getStock().getCountableMap().putAll(admission.getCountableMap());
                        supermarket.getStock().getUncountableMap().putAll(admission.getUncountableMap());
                        printer.printResult(EventType.ADMISSION.toString(), supermarket.getStock());
                    }
                    case MOVE_TO_SHOP: {
                        this.moveProductsToShop(supermarket);
                        printer.printResult(EventType.MOVE_TO_SHOP.toString(), supermarket.getShop());
                    }
                    break;
                }
*/
                TimeUnit.SECONDS.sleep(2);
/*
                int n = supermarket.getCustomers().size();
                customerService.serveCustomer(supermarket.getCustomers(), supermarket.getShop());

                printer.printCustomers(EventType.SERVE_CUSTOMER.toString(), supermarket.getCustomers().size() - n,
                        supermarket.getCustomers().size());

                productService.decreaseExpirationDays(supermarket);

                printer.printDayPassed();
*/
                try (FileWriter writer = new FileWriter("./src/main/resources/save.txt", false)) {
                    jsonSaving = gson.toJson(supermarket);
                    writer.write(jsonSaving);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                for (int i = 0; i < (int) (Math.random() * 50 + 1); i++)
                    events.add(EventService.castEvent());
            }
        }
    }

    private Map<EventType, Consumer<Supermarket>> initEvents() {
        Map<EventType, Consumer<Supermarket>> result = new HashMap<>();

        OutputUtils printer = new OutputUtils();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();

        Consumer<Supermarket> priceFall = supermarket -> {
            Storage shop = supermarket.getShop();
            priceFall(shop);
            printer.printResult(EventType.PRICE_FALL.toString(), shop);
        };
        Consumer<Supermarket> newCustomer = supermarket -> {
            List<Customer> queue = supermarket.getCustomers();

            int n = queue.size();
            queue.addAll(customerService.generateCustomers());

            printer.printCustomers(EventType.NEW_CUSTOMER.toString(), queue.size() - n, queue.size());
        };
        Consumer<Supermarket> admission = supermarket -> {
            Storage stock = supermarket.getStock();

            Storage newcome = productService.generateProducts();

            stock.getCountableMap().putAll(newcome.getCountableMap());
            stock.getUncountableMap().putAll(newcome.getUncountableMap());

            printer.printResult(EventType.ADMISSION.toString(), stock);
        };
        Consumer<Supermarket> deleteExp = supermarket -> {
            this.deleteExpProducts(supermarket.getShop());
            printer.printResult(EventType.DELETE_EXP.toString() + " in shop:", supermarket.getShop());

            this.deleteExpProducts(supermarket.getStock());
            printer.printResult(EventType.DELETE_EXP.toString() + " in stock:", supermarket.getStock());
        };
        Consumer<Supermarket> moveToShop = supermarket -> {
            moveProductsToShop(supermarket);
            printer.printResult(EventType.MOVE_TO_SHOP.toString(), supermarket.getShop());
        };
        Consumer<Supermarket> serveCustomers = supermarket -> {
            int n = supermarket.getCustomers().size();
            customerService.serveCustomer(supermarket.getCustomers(), supermarket.getShop());
            printer.printCustomers(EventType.SERVE_CUSTOMER.toString(), supermarket.getCustomers().size() - n,
                    supermarket.getCustomers().size());

            productService.decreaseExpirationDays(supermarket);
            printer.printDayPassed();
        };

        result.put(EventType.ADMISSION, admission);
        result.put(EventType.PRICE_FALL, priceFall);
        result.put(EventType.NEW_CUSTOMER, newCustomer);
        result.put(EventType.DELETE_EXP, deleteExp);
        result.put(EventType.MOVE_TO_SHOP, moveToShop);
        result.put(EventType.SERVE_CUSTOMER, serveCustomers);

        return result;
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

    private void moveProductsToShop(Supermarket supermarket) {
        Storage stock = supermarket.getStock();
        Storage shop = supermarket.getShop();
        for (Map.Entry<ProductType, Uncountable> entry : stock.getUncountableMap().entrySet()) {
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