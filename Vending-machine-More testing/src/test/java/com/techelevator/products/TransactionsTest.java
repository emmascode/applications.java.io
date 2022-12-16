package com.techelevator.products;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransactionsTest
{
        @Test
        public void perform_Transactions_Should_Update_Quantity_To_4_When_Quantity_Is5() {
                //arrange
                Transactions transactions = new Transactions();
                String selectedId;
                selectedId = "C1";
                Product selectedProduct = new Product("Cola", BigDecimal.valueOf(1.25), "Drink", 5);
                CashRegister curBalance = new CashRegister();
                curBalance.setTotalCash(BigDecimal.valueOf(1.5));
                int expectedNumCola = 4;
                //act
                transactions.performTransaction(selectedId, selectedProduct, curBalance);
                int actualNumCola = selectedProduct.getQuantity();
                //assert
                assertEquals("The number of Cola is now four", expectedNumCola, actualNumCola);
        }

        @Test
        public void perform_Transactions_Should_Update_Quantity_To_0_When_Quantity_Is1() {
                //arrange
                Transactions transactions = new Transactions();
                String selectedId;
                selectedId = "C1";
                Product selectedProduct = new Product("Cola", BigDecimal.valueOf(1.25), "Drink", 1);
                CashRegister curBalance = new CashRegister();
                curBalance.setTotalCash(BigDecimal.valueOf(1.5));
                int expectedNumCola = 0;
                //act
                transactions.performTransaction(selectedId, selectedProduct, curBalance);
                int actualNumCola = selectedProduct.getQuantity();
                //assert
                assertEquals("The number of Cola is now zero", expectedNumCola, actualNumCola);
        }

        @Test
        public void perform_Transactions_Should_PrintOut_SoldOut_Message_When_Quantity_Is0() {
                //arrange
                Transactions transactions = new Transactions();
                String selectedId;
                selectedId = "C1";
                Product selectedProduct = new Product("Cola", BigDecimal.valueOf(1.25), "Drink", 0);
                CashRegister curBalance = new CashRegister();
                curBalance.setTotalCash(BigDecimal.valueOf(1.5));
                int expectedNumCola = 0;
                //act
                transactions.performTransaction(selectedId, selectedProduct, curBalance);
                int actualNumCola = selectedProduct.getQuantity();
                //assert
                assertEquals("Cola (C1) is sold out", expectedNumCola, actualNumCola);
        }

        @Test
        public void perform_Transactions_Should_Update_Balance_To_25Cents_When_Quantity_Is1() {
                //arrange
                Transactions transactions = new Transactions();
                String selectedId;
                selectedId = "C1";
                Product selectedProduct = new Product("Cola", BigDecimal.valueOf(1.25), "Drink", 1);
                CashRegister curBalance = new CashRegister();
                curBalance.setTotalCash(BigDecimal.valueOf(1.5));
                BigDecimal expectedBalance = BigDecimal.valueOf(.25);
                //act
                transactions.performTransaction(selectedId, selectedProduct, curBalance);
                BigDecimal actualBalance = curBalance.getTotalCash();
                //assert
                org.junit.Assert.assertTrue(expectedBalance.compareTo(actualBalance) == 0);
        }

        @Test
        public void perform_Transactions_Should_PrintOut_NotEnoughMoney_When_NotEnough_Money() {
                //arrange
                //arrange
                Transactions transactions = new Transactions();
                String selectedId;
                selectedId = "C1";
                Product selectedProduct = new Product("Cola", BigDecimal.valueOf(1.25), "Drink", 1);
                CashRegister curBalance = new CashRegister();
                curBalance.setTotalCash(BigDecimal.valueOf(1.00));
                int expectedNumCola = 1;
                //act
                transactions.performTransaction(selectedId, selectedProduct, curBalance);
                int actualNumCola = selectedProduct.getQuantity();
                //assert
                assertEquals("Not enough money, insert more cash.", expectedNumCola, actualNumCola);
        }

        @Test
        public void perform_Transactions_Should_Increase_Quantity_When_Customer_Buys_The_SameItem_Again() {
                //arrange
                Transactions transactions = new Transactions();
                transactions.getTransactions().put("C1", 4);
                String selectedId;
                selectedId = "C1";
                Product selectedProduct = new Product("Cola", BigDecimal.valueOf(1.25), "Drink", 5);
                CashRegister curBalance = new CashRegister();
                curBalance.setTotalCash(BigDecimal.valueOf(1.5));
                int expectedC1Num = 5;
                //act
                transactions.performTransaction(selectedId, selectedProduct, curBalance);
                int actualC1Num = transactions.getTransactions().get("C1");
                //assert
                assertEquals("The number of items in transactions is now one", expectedC1Num, actualC1Num);
        }

        @Test
        public void perform_Transactions_Should_Update_Transactions_When_SaleTransactions_Succeed() {
                //arrange
                Transactions transactions = new Transactions();
                String selectedId;
                selectedId = "C1";
                Product selectedProduct = new Product("Cola", BigDecimal.valueOf(1.25), "Drink", 5);
                CashRegister curBalance = new CashRegister();
                curBalance.setTotalCash(BigDecimal.valueOf(1.5));
                int expectedTransactionsLen = 1;
                //act
                transactions.performTransaction(selectedId, selectedProduct, curBalance);
                int actualTransactionsLen = transactions.getTransactions().size();
                //assert
                assertEquals("The number of items in transactions is now one", expectedTransactionsLen, actualTransactionsLen);
        }

}
