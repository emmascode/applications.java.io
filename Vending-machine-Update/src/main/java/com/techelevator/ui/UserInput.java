package com.techelevator.ui;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.*;

public class UserInput {

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public static final int MM_DISPLAY_OPTION = 1;
    public static final int MM_PURCHASE_OPTION = 2;
    public static final int MM_SALE_RECEIPT = 3;
    public static final int MM_EXIT = 4;
    public static final int PM_FEED_MONEY = 1;
    public static final int PM_LIST_PRODUCTS = 2;
    public static final int PM_FINISH_TRANSACTIONS = 3;

    static final List<Integer> VALID_MAIN_MENU_OPTION = new ArrayList<>(Arrays.asList(MM_DISPLAY_OPTION, MM_PURCHASE_OPTION, MM_SALE_RECEIPT, MM_EXIT));
    static final List<Integer> VALID_PURCHASE_MENU_OPTION = new ArrayList<>(Arrays.asList(PM_FEED_MONEY, PM_LIST_PRODUCTS, PM_FINISH_TRANSACTIONS));

    public static int selectMainMenuOption() {
        return getUserSelection(VALID_MAIN_MENU_OPTION);
    }
    public static int selectPurchaseMenuOption() {
        return getUserSelection(VALID_PURCHASE_MENU_OPTION);
    }

    public static int getUserSelection(List<Integer> validOptions) {
        while(true) {
            System.out.print("\n" + "Please make a selection: ");
            Scanner input = new Scanner(System.in);
            try {
                int selection = input.nextInt();
                for (Integer validOption : validOptions) {
                    if (validOption == selection) {
                        return selection;
                    }
                }
                System.out.println(TEXT_RED + "\n" + "Please select valid option." + TEXT_RESET);
            }
            catch (Exception ex) {
                System.out.println(TEXT_RED + "\n" + "Invalid! Please select again." + TEXT_RESET);
            }
        }
    }

    public static void enterToContinue() {
        System.out.println("\n" + "Press Enter to continue.");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    public static BigDecimal insertCash() {
        System.out.print("\n" + "Insert money (whole dollar amounts only): $");
        Scanner input = new Scanner(System.in);
        String cashInserted = input.nextLine();
        return new BigDecimal(cashInserted);
    }

    public static String enterProductId(List<String> validProductIDs) {
        while (true) {
            System.out.print("\nEnter the ID of the product you want: ");
            Scanner input = new Scanner(System.in);
            try {
                String inputId = input.nextLine().toUpperCase();
                for (String validProductID : validProductIDs)
                {
                    if (inputId.equalsIgnoreCase(validProductID))
                    {
                        return inputId;
                    }
                }
                System.out.println(TEXT_RED + "\n" + "Please enter a valid slot number." + TEXT_RESET);
            } catch (Exception e) {
                System.out.println(TEXT_RED + "\n" + "Invalid! Please enter again." + TEXT_RESET);
            }
        }
    }
}
