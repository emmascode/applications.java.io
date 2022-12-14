package com.techelevator.products;

import com.techelevator.report.LogGenerator;
import com.techelevator.ui.UserInput;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashRegister {

    private BigDecimal totalCash = BigDecimal.valueOf(0.00);

    public CashRegister() {
        this.totalCash = BigDecimal.valueOf(0);
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public void deductCashAmount(BigDecimal deductAmount) {
        this.totalCash = this.totalCash.subtract(deductAmount);
    }

    public void feedMoney() {
        BigDecimal cash;
        while (true) {
            try {
                cash = UserInput.insertCash();
                if (cash.remainder(BigDecimal.ONE).compareTo((BigDecimal.ZERO)) != 0) {
                    System.out.println("Cash inserted is not a whole amount.");

                } else {
                    this.totalCash = this.totalCash.add(cash);
                    LogGenerator logGenerator = new LogGenerator();
                    logGenerator.writeToLog("FEED MONEY", cash, totalCash);
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid! Please insert a whole amount");
            }
        }
    }

    public int[] dispensingChanges(CashRegister remainingChanges) {
        //generate log entry
        LogGenerator logGenerator = new LogGenerator();
        logGenerator.writeToLog("GIVE CHANGE", totalCash, BigDecimal.valueOf(0.00));

        //define variables for coins
        BigDecimal remainingBalance = remainingChanges.getTotalCash();

        //declare variables & output
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        RoundingMode roundDown = RoundingMode.DOWN;
        RoundingMode roundUp = RoundingMode.UP;

        //calculate the modulus
        int numQuarters =  remainingBalance.divide(quarter, roundDown).intValue();
        BigDecimal amountDimes =  remainingBalance.remainder(quarter);
        int numDimes = amountDimes.divide(dime, roundDown).intValue();
        BigDecimal amountNickels = amountDimes.remainder(dime);
        int numNickels = amountNickels.divide(nickel, roundUp).intValue();

        //reset the current cash balance to zero
        remainingChanges.setTotalCash(BigDecimal.valueOf(0));

        //return output array
        return new int[] {numQuarters, numDimes, numNickels};
    }
}
