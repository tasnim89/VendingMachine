package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GumTests {

    private Gum gum;

    @Before
    public void createGum(){
        gum = new Gum("U-Chews", 85, 5);
    }


    @Test

    public void twoArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("U-Chews", gum.getName());
        Assert.assertEquals(85, gum.getPrice());

    }

    @Test

    public void threeArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("U-Chews", gum.getName());
        Assert.assertEquals(85, gum.getPrice());
        Assert.assertEquals(5, gum.getInventory());

    }

}
