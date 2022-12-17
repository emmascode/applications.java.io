package com.techelevator.products;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest
{
    Product product = new Product();
    String type, expectedString, actualString;

    @Test
    public void getDispensingMessage_ShouldBe_CrunchCrunchYum_If_InputIs_Chip()
    {
        //arrange
        type = "Chip";
        expectedString = "Crunch Crunch, Yum!";
        //act
        actualString = product.getDispensingMessage(type);
        //assert
        assertEquals("Output should be \"Crunch Crunch, Yum!\" if the input type is \"Chip\"", expectedString, actualString);
    }

    @Test
    public void getDispensingMessage_ShouldBe_MunchMunchYum_If_InputIs_Candy()
    {
        //arrange
        type = "Candy";
        expectedString = "Munch Munch, Yum!";
        //act
        actualString = product.getDispensingMessage(type);
        //assert
        assertEquals("Output should be \"Munch Munch, Yum!\" if the input type is \"Candy\"", expectedString, actualString);
    }

    @Test
    public void getDispensingMessage_ShouldBe_GlugGlugYum_If_InputIs_Drink()
    {
        //arrange
        type = "Drink";
        expectedString = "Glug Glug, Yum!";
        //act
        actualString = product.getDispensingMessage(type);
        //assert
        assertEquals("Output should be \"Glug Glug, Yum!\" if the input type is \"Drink\"", expectedString, actualString);
    }

    @Test
    public void getDispensingMessage_ShouldBe_ChewChewYum_If_InputIs_Gum()
    {
        //arrange
        type = "Gum";
        expectedString = "Chew Chew, Yum!";
        //act
        actualString = product.getDispensingMessage(type);
        //assert
        assertEquals("Output should be \"Chew Chew, Yum!\" if the input type is \"Gum\"", expectedString, actualString);
    }
}
