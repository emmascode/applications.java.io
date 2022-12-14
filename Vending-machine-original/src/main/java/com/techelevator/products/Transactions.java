package com.techelevator.products;

import com.techelevator.report.LogGenerator;
import com.techelevator.ui.*;
import java.math.BigDecimal;
import java.util.*;

//declare Transactions class, containing one or more sale items for each buyer
public class Transactions {
    private Map<String, Integer> transactions = new HashMap<>(); //<product_id, purchasing_quantity>
    private LogGenerator logGenerator = new LogGenerator();

    public Transactions() {
    }

    public Transactions(Map<String, Integer> transactions) {
        this.transactions = transactions;
    }

    public Map<String, Integer> getTransactions() {
        return transactions;
    }

    //method to find the product information from input ID
    public void performTransaction(String selectedId, Product selectedProduct, CashRegister curBalance) {
        int selectedProductQuantity = selectedProduct.getQuantity();
        BigDecimal selectedProductPrice = selectedProduct.getPrice();
        BigDecimal currentCashBalance = curBalance.getTotalCash();

        if (selectedProductQuantity > 0 && currentCashBalance.compareTo(selectedProductPrice) >= 0) {
            //increase sale quantity of selected product by 1
            this.addItem(selectedId);

            //reduce the inventory quantity by 1 & update the cash balance
            selectedProduct.setQuantity(selectedProductQuantity - 1);
            curBalance.deductCashAmount(selectedProductPrice);

            //display and add to log
            UserOutput.displaySaleTransaction(selectedProduct, curBalance);
            this.logGenerator.writeToLog( selectedProduct.getName() + " " + selectedId, selectedProduct.getPrice(), curBalance.getTotalCash());

        } else if (selectedProductQuantity== 0) {
            System.out.println("\n" + selectedProduct.getName() + " (" + selectedId + ") is sold out, try again.");

        } else if (currentCashBalance.compareTo(selectedProductPrice) < 0) {
            System.out.println("\n" + "Not enough money, insert more cash.");
        }
    }

    //increase sale quantity by 1 & add sale item to the transactions map.
    public void addItem(String selectedId) {
        if (this.transactions.containsKey(selectedId)) {
            int saleQuantity = this.transactions.get(selectedId);
            this.transactions.put(selectedId, saleQuantity + 1);

        } else {
            this.transactions.put(selectedId, 1);
        }
    }
}

