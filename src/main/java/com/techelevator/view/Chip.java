package com.techelevator.view;

public class Chip extends Product {

    public Chip(String name, int price, int inventory) {
        super(name, price, inventory);
    }

    public Chip(String name, int price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Crunch Crunch, Yum!";
    }


}
