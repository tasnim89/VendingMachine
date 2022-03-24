package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CandyTests {
    private Candy candy;

    @Before
    public void createCandy(){
        candy = new Candy("MoonPie", 180, 5);
    }


    @Test

    public void twoArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("MoonPie", candy.getName());
        Assert.assertEquals(180, candy.getPrice());

    }

    @Test
    public void threeArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("MoonPie", candy.getName());
        Assert.assertEquals(180, candy.getPrice());
        Assert.assertEquals(5, candy.getInventory());

    }

}
