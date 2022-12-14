package com.techelevator.products;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity;

    public Product() {
    }

    public Product(String name, BigDecimal price, String type, int quantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDispensingMessage(String type) {
        String message = "";
        if (type.equalsIgnoreCase("Chip")) {
            message = "Crunch Crunch, Yum!";
        } else if (type.equalsIgnoreCase("Candy")) {
            message = "Munch Munch, Yum!";
        } else if (type.equalsIgnoreCase("Drink")) {
            message = "Glug Glug, Yum!";
        } else {
            message = "Chew Chew, Yum!";
        }
        return message;
    }
}


