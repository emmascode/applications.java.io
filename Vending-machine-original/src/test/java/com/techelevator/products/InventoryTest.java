package com.techelevator.products;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class InventoryTest
{
    //initializers
    Inventory inventory = new Inventory();
    //variables
    String productId, expectedString, actualString;
    BigDecimal expectedBigDecimal, actualBigDecimal;
    //tests
    @Test
    public void getProductName_ShouldBe_Cowtales_When_InputIs_B2() {
        //arrange
        inventory.loadInventory();
        productId = "B2";
        expectedString = "Cowtales";
        //act
        actualString = inventory.getProductInfo(productId).getName();
        //assert
        assertEquals("The output should be \"Cowtales\" when the input is \"B4\".", expectedString, actualString);
    }

    @Test
    public void getProductType_ShouldBe_Gum_When_InputIs_D4() {
        //arrange
        inventory.loadInventory();
        productId = "D4";
        expectedString = "Gum";
        //act
        actualString = inventory.getProductInfo(productId).getType();
        //assert
        assertEquals("The output should be \"Gum\" when the input is \"D4\".", expectedString, actualString);
    }

    @Test
    public void getProductPrice_ShouldBe_Cowtales_When_InputIs_B2() {
        //arrange
        inventory.loadInventory();
        productId = "C1";
        expectedBigDecimal = BigDecimal.valueOf(1.25);
        //act
        actualBigDecimal = inventory.getProductInfo(productId).getPrice();
        //assert
        assertEquals("The output should be \"1.25\" when the input is \"C1\".", expectedBigDecimal, actualBigDecimal);
    }
}
