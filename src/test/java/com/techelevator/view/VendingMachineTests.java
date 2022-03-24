package com.techelevator.view;

import com.techelevator.VendingMachine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.fail;

public class VendingMachineTests {


    @Test
    public void fileReaderTests() throws FileNotFoundException {
        VendingMachine vendingMachine = new VendingMachine();

        //act
        vendingMachine.fileReader();

        Map<String,Product> result = vendingMachine.getProductMap();

        Assert.assertEquals(16, result.size());
        Product a1 = result.get("A1");
        Assert.assertEquals("Potato Crisps", a1.getName());
        Assert.assertEquals("Moonpie", result.get("B1").getName());
        Assert.assertEquals(180, result.get("B1").getPrice());
    }

    @Test
    public void feedMoney() throws Exception{
        VendingMachine vendingMachine = new VendingMachine();
        Assert.assertEquals(0, vendingMachine.getCurrentMoneyProvided());
        Assert.assertEquals(100, vendingMachine.feedMoney(1));
        Assert.assertEquals(600, vendingMachine.feedMoney(5));


    }

    @Test
    public void feedMoney_InvalidData() throws Exception {
        VendingMachine vendingMachine = new VendingMachine();
        Assert.assertEquals(0, vendingMachine.getCurrentMoneyProvided());
        try {
             vendingMachine.feedMoney(3);
            fail("Expected invalid money exception");
        } catch (InvalidMoneyException e) {
            // expected
            Assert.assertEquals(0, vendingMachine.getCurrentMoneyProvided());
        }


    }

    @Test
    public void selectProductTests() throws FileNotFoundException, InvalidMoneyException {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(5);
        Map<String, Product> productMap = new TreeMap<>();
        vendingMachine.fileReader();

       String expected1 = "Potato Crisps costs $3.05 and here is your change: $1.95 Crunch Crunch, Yum!";
       Assert.assertEquals(expected1, vendingMachine.selectProduct("A1"));
    }

    @Test
    public void selectProductTestsInsufficientFunds() throws FileNotFoundException, InvalidMoneyException {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(1);
        Map<String, Product> productMap = new TreeMap<>();
        vendingMachine.fileReader();

       String expected = "Insufficient Funds";
       Assert.assertEquals(expected, vendingMachine.selectProduct("A2"));

    }

    @Test
    public void selectProductTestsInvalidData() throws FileNotFoundException, InvalidMoneyException {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(5);
        Map<String, Product> productMap = new TreeMap<>();
        vendingMachine.fileReader();


        String expected3 = "Item not found";
        Assert.assertEquals(expected3, vendingMachine.selectProduct("Z1"));
    }

    @Test
    public void selectProductTestsSoldOut() throws FileNotFoundException, InvalidMoneyException {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.feedMoney(20);
        Map<String, Product> productMap = new TreeMap<>();
        vendingMachine.fileReader();

        vendingMachine.selectProduct("A1");
        vendingMachine.selectProduct("A1");
        vendingMachine.selectProduct("A1");
        vendingMachine.selectProduct("A1");
        vendingMachine.selectProduct("A1");

        String expected6 = "SOLD OUT";
        Assert.assertEquals(expected6, vendingMachine.selectProduct("A1"));
    }






}
