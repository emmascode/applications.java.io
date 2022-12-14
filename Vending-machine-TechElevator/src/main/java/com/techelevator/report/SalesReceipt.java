package com.techelevator.report;

import com.techelevator.products.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesReceipt
{
    //colors
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RESET = "\u001B[0m";

    //variables
    private List<Transactions> salesList = new ArrayList<>();

    public SalesReceipt(List<Transactions> salesList)
    {
        this.salesList = salesList;
    }

    public SalesReceipt() {}

    public List<Transactions> getSalesList()
    {
        return salesList;
    }

    public void setSalesList(List<Transactions> salesList)
    {
        this.salesList = salesList;
    }

    //add transactions to the sale report
    public Map<String, Integer> generateSalesReceipt()
    {
        Map<String, Integer> saleMap = new HashMap<>();
        for (Transactions transactions : this.salesList){
            for (Map.Entry<String, Integer> row : transactions.getTransactions().entrySet()){
                if (saleMap.containsKey(row.getKey()))
                {
                    int saleQuantity = saleMap.get(row.getKey());
                    saleMap.put(row.getKey(), saleQuantity + row.getValue());

                } else {
                    saleMap.put(row.getKey(), row.getValue());
                }
            }
        }
        return saleMap;
    }

    public void addTransactions(Transactions transactions) {
        this.salesList.add(transactions);
    }

    public void printSalesReceipt() {
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        //get date and time
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss-aa");
        String dateAndTime = formatter.format(date);

        //uniquely named sales report file
        File filePath = new File("SalesReceipt_" + dateAndTime + ".txt");
        Product product;
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(filePath, true))){
            dataOutput.println("**** SALES RECEIPT ****");
            for (Map.Entry<String, Integer> row : this.generateSalesReceipt().entrySet()){
                product = inventory.getProducts().get(row.getKey());
                dataOutput.println("\n" + row.getKey() + " | " + product.getName() +
                        " | " + product.getPrice()  + " | " + row.getValue() + "\n");
            }

            System.out.println(TEXT_YELLOW + "***********************************************" + TEXT_RESET);
            System.out.println("Sales report has been generated!");
            dataOutput.println("\n****TOTAL SALES**** $" + this.calculateTotalSales());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public BigDecimal calculateTotalSales() {
        Inventory inventory = new Inventory();
        inventory.loadInventory();
        Transactions transactions = new Transactions();
        Product product;
        int quantity;
        BigDecimal totalSale = new BigDecimal(0.00);

        for(Map.Entry<String, Integer> row : generateSalesReceipt().entrySet()){
            product = inventory.getProducts().get(row.getKey());
            quantity = generateSalesReceipt().get(row.getKey());
            // quantity * product price
            totalSale = totalSale.add(BigDecimal.valueOf(quantity).multiply(product.getPrice()));
        }
        return totalSale;
    }
}

