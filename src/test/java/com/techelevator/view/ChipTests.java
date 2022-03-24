package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChipTests {

    private Chip chip;

    @Before
    public void createChip(){
        chip = new Chip("PotatoCrisps", 305);
    }


    @Test

    public void twoArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("PotatoCrisps", chip.getName());
        Assert.assertEquals(305, chip.getPrice());

    }

    @Test

    public void threeArgConstructor_Tests(){


        //Assert
        Assert.assertEquals("Chip", chip.getName());
        Assert.assertEquals(305, chip.getPrice());
        Assert.assertEquals(5, chip.getInventory());

    }


}
