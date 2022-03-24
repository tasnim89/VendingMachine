package com.techelevator.view;

public class Gum extends Product{

    public Gum(String name, int price, int inventory) {
        super(name, price, inventory);
    }

    public Gum(String name, int price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return  "Chew Chew, Yum!";
    }
}
