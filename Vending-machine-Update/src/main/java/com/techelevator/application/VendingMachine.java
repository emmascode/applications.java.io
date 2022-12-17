package com.techelevator.application;

import com.techelevator.products.*;
import com.techelevator.report.SalesReceipt;
import com.techelevator.ui.*;
import java.util.*;

public class VendingMachine
{
    private Inventory inventory;
    private SalesReceipt salesReceipt = new SalesReceipt();

    public VendingMachine() {
        inventory = new Inventory();
        inventory.loadInventory();
    }

    public void run() {
        try {
            UserOutput.displayWelcomeMessage();
            int selectedMenuOption;
            while (true) {
                UserOutput.displayHomeScreenMenu();
                selectedMenuOption = UserInput.selectMainMenuOption();
                if (selectedMenuOption == UserInput.MM_DISPLAY_OPTION) {
                    UserOutput.displayInventory(inventory);
                    UserInput.enterToContinue();

                } else if (selectedMenuOption == UserInput.MM_PURCHASE_OPTION) {
                    handlePurchaseMenu();

                } else if (selectedMenuOption == UserInput.MM_SALE_RECEIPT) {
                    salesReceipt.printSalesReceipt();

                } else if (selectedMenuOption == UserInput.MM_EXIT) {
                    UserOutput.exit();
                    System.out.println();
                    //display welcome message again for the next customer
                    UserOutput.displayWelcomeMessage();
                }
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handlePurchaseMenu() {
        Transactions transactions = new Transactions(); // purchased items
        CashRegister curBalance = new CashRegister();
        List<String> validProductIds = new ArrayList<>(inventory.getProducts().keySet());
        int selectedPurchaseOption;
        String selectedId;
        Product selectedProduct;
        while (true) {
            //choose Purchase menu option
            UserOutput.displayPurchaseMenu(curBalance);
            selectedPurchaseOption = UserInput.selectPurchaseMenuOption();

            if (selectedPurchaseOption == UserInput.PM_FEED_MONEY) {
                curBalance.feedMoney();

            } else if (selectedPurchaseOption == UserInput.PM_LIST_PRODUCTS) {
                //get user's selection on items and perform the transaction
                UserOutput.displayInventory(inventory);
                selectedId = UserInput.enterProductId(validProductIds);
                selectedProduct = inventory.getProductInfo(selectedId);
                transactions.performTransaction(selectedId, selectedProduct, curBalance);

                UserInput.enterToContinue();

            } else if (selectedPurchaseOption == UserInput.PM_FINISH_TRANSACTIONS) {
                //display returned changes message
                UserOutput.displayReturnChanges(curBalance);

                //if user bought at least 1 item
                if (!transactions.getTransactions().isEmpty()) {
                    salesReceipt.addTransactions(transactions);
                    break;
                }
            }
        }
    }
}
