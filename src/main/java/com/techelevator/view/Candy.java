package com.techelevator.view;

import java.util.Map;
import java.util.TreeMap;

public class Candy extends Product{

    public Candy(String name, int price, int inventory) {
        super(name, price, inventory);
    }

    public Candy(String name, int price) {

        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Munch Munch, Yum!";
    }



}
