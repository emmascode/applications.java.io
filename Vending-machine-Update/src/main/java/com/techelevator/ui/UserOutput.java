package com.techelevator.ui;

import com.techelevator.products.*;

import java.math.BigDecimal;
import java.util.*;

public class UserOutput {
    // colors
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_BLUE = "\u001b[34;1m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RESET = "\u001B[0m";

    // welcome message
    public static void displayWelcomeMessage()
    {
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println(TEXT_YELLOW +"|        WELCOME TO THE VENDING MACHINE!        |" + TEXT_RESET);
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
    }

    public static void displayHomeScreenMenu() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Get sale receipt");
        System.out.println("(4) Exit");
    }

    public static void displayInventory(Inventory inventory) {
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println(TEXT_YELLOW +"|    Product ID | Name | Price | Quantities    |" + TEXT_RESET);
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);

//        Map<String, Product> treeMap = new TreeMap<String, Product>(inventory.getProducts());
//        for (Map.Entry<String, Product> row : inventory.sortedInventory().entrySet()) {
        for (String key: inventory.sortByKey()) {
            Product product = inventory.getProductInfo(key);
            String type = product.getType();
            String name = product.getName();
            BigDecimal price = product.getPrice();
            int quantity = product.getQuantity();
            if(type.equalsIgnoreCase("chip"))
            {
                System.out.println(TEXT_RED + key + " | " + name  + " | " + type + " | $" + price +  " | " + quantity + "pcs." + TEXT_RESET);
            }
            else if(type.equalsIgnoreCase("candy"))
            {
                System.out.println(TEXT_PURPLE  +  key + " | " + name  + " | " + type + " | $" + price +  " | " + quantity +  "pcs." + TEXT_RESET);
            }
            else if(type.equalsIgnoreCase("drink"))
            {
                System.out.println(TEXT_CYAN +  key + " | " + name  + " | " + type + " | $" + price +  " | " + quantity + "pcs." + TEXT_RESET);
            }
            else if(type.equalsIgnoreCase("gum"))
            {
                System.out.println(TEXT_GREEN +  key + " | " + name  + " | " + type + " | $" + price +  " | " + quantity + "pcs." + TEXT_RESET);
            }
        }
    }

    public static void displayPurchaseMenu(CashRegister curBalance) {
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println("Current Cash Available: " + TEXT_GREEN + "$" + curBalance.getTotalCash() + TEXT_RESET);
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
    }

    public static void displaySaleTransaction(Product product, CashRegister remainingBalance) {
        //if ()
        System.out.println("\n" + "Item sold: " + product.getName());
        System.out.println("Item price: $" + product.getPrice());
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println("Remaining cash balance: " + TEXT_GREEN + "$" + remainingBalance.getTotalCash() + TEXT_RESET);
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println(product.getDispensingMessage(product.getType()));
    }

    public static void displayReturnChanges(CashRegister curBalance) {
        System.out.println("\n" + "You will be received $" + curBalance.getTotalCash() + " as:");
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        int[] arrChanges = curBalance.dispensingChanges((curBalance));
        System.out.println(arrChanges[0] + " quarters.");
        System.out.println(arrChanges[1] + " dimes.");
        System.out.println(arrChanges[2] + " nickels.");
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println("Current cash available: " + TEXT_GREEN + "$" + curBalance.getTotalCash() + "\n" + TEXT_RESET);
    }

    public static void exit() {
        System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
        System.out.println("Thank you for your purchase! See you next time!");
        System.out.println(TEXT_YELLOW + "***********************************************\n" + TEXT_RESET);
    }
}



