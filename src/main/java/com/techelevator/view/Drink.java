package com.techelevator.view;

public class Drink extends Product {

        public Drink(String name, int price, int inventory) {
                super(name, price, inventory);
        }


        public Drink(String name, int price) {
                super(name, price);
        }

        @Override
        public String getMessage() {
                return "Glug Glug, Yum!";
        }


}
