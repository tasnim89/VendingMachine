package com.techelevator.view;

import java.util.Map;
import java.util.TreeMap;

public abstract class Product {

    private String name;
    private int price;
    private int inventory = 5;

    public Product(String name, int price, int inventory) {
        this.name = name;
        this.price= price;
        this.inventory = inventory;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price= price;

    }


    //getters

    public String getName(){
        return name;
    }
    public int getPrice() {
        return price;
    }

    public abstract String getMessage();

    public int getInventory() {
        return inventory;
    }
    // setters

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }


}