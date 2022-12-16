package com.techelevator.products;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Inventory {
    private Map<String, Product> products; // <slot_ID, Product>

    public Inventory() {}

    public Map<String, Product> getProducts() {
        return this.products;
    }

    public Product getProductInfo(String productID) {
        if (this.products.containsKey(productID)) {
            return this.products.get(productID);
        } else {
            System.out.println("Invalid product ID, try again.");
            return null;
        }
    }

    public void loadInventory() {
        products = new HashMap<>();
        File inputFile = new File("data/vendingmachine.csv");
        try(Scanner input = new Scanner(inputFile)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] columns = line.split("\\|");

                String id = columns[0];
                String name = columns[1];
                BigDecimal price = new BigDecimal(columns[2]);
                String type = columns[3];
                int quantity = 5;

                Product product = new Product(name, price, type, quantity);
                products.put(id, product);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Reading file exception");
        }
    }
}
