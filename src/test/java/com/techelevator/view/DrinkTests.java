package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DrinkTests {

    private Drink drink;

    @Before
    public void createDrink(){
        drink = new Drink("Cola", 125, 5);
    }


    @Test

    public void twoArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("Cola", drink.getName());
        Assert.assertEquals(125, drink.getPrice());

    }

    @Test

    public void threeArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("Cola", drink.getName());
        Assert.assertEquals(125, drink.getPrice());
        Assert.assertEquals(5, drink.getInventory());

    }
}
