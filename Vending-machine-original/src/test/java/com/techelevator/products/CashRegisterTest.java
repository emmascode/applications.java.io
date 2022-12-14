package com.techelevator.products;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CashRegisterTest
{
    // initializers
    CashRegister cashRegister = new CashRegister();

    // tests
    @Test
    public void dispensingChanges_ShouldBe_Return_8Quarters_1Dimes_1Nickles_For_TwoDollar_And_Fifteen_Cents()
    {
        //arrange
        CashRegister remainingBalance = new CashRegister();
        remainingBalance.setTotalCash(BigDecimal.valueOf(2.15));
        int[] expectedChanges = new int[] {8, 1, 1};
        //act
        int[] actualChanges = cashRegister.dispensingChanges(remainingBalance);
        //assert
        assertArrayEquals("There should be 8 quarters, 1 dimes and 1 nickle", expectedChanges, actualChanges);
    }

    //Note: Since cash inserted limit to whole dollar amount & item price is a multiple of nickles,
    //changes would also be a multiple of nickle. Therefore, we only test remaining balance as a multiple of nickle
    //ie: 0.05; 1.25...

    @Test
    public void dispensingChanges_ShouldNotReturn_Changes_If_RemainingBalance_Is5Cents()
    {
        //arrange
        CashRegister remainingBalance = new CashRegister();
        remainingBalance.setTotalCash(BigDecimal.valueOf(0.05));
        int[] expectedChanges = new int[] {0, 0, 1};
        //act
        int[] actualChanges = cashRegister.dispensingChanges(remainingBalance);
        //assert
        assertArrayEquals("There should be one nickle", expectedChanges, actualChanges);
    }

}
