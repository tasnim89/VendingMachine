package com.techelevator;

import com.techelevator.view.*;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine {

    private Map<String, Product> productMap = new TreeMap<>();
    private int currentMoneyProvided = 0;


    public VendingMachine() {}


    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public int getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public void setCurrentMoneyProvided(int currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    //Display menu items
    public void fileReader() throws FileNotFoundException {

        File vendingMachineProducts = new File("vendingmachine.csv");

        try (Scanner scanner = new Scanner(vendingMachineProducts)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Split all values
                String[] values = line.split("\\|");

                String slotId = values[0];
                String snackName = values[1];
                String priceString = values[2];
                int price = (int) ((Double.parseDouble(priceString.trim()) * 100));
                String type = values[3];

                Product product = null;

                if (type.equals("Chip")) {
                    product = new Chip(snackName, price);
                } else if (type.equals("Drink")) {
                    product = new Drink(snackName, price);
                } else if (type.equals("Gum")) {
                    product = new Gum(snackName, price);
                } else {
                    product = new Candy(snackName, price);
                }
                productMap.put(slotId, product);

            }
        }
    }


    public int feedMoney(int money) throws InvalidMoneyException, FileNotFoundException {

        if (money == 1 || money == 5 || money == 10 || money == 20) {
            currentMoneyProvided += money * 100;
            //log money and currentMoneyProvided
            String nowAsString = DateTimeUtils.getCurrentTimeAsString(DateTimeUtils.FORMAT);
            appendingToLog(nowAsString + " FEED MONEY: " + " $" + String.format("%.2f",(double)money) + " $" + String.format("%.2f",((double)currentMoneyProvided)/100.00) + "\n");
            return currentMoneyProvided;
        } else {
            throw new InvalidMoneyException(String.format("You entered $%d, must be $1,$5,$10,$20",money));
        }
    }


    public String selectProduct(String slotId) throws FileNotFoundException {

        //slotId is valid- if valid we continue..//if the slotId does not exist, we notify the customer and return the purchase menu
        if (!productMap.containsKey(slotId)) {

            return "Item not found";
            //return a message and back to purchase menu
        } else {
            Product item = productMap.get(slotId);
            // make sure that the inventory is more than 0, to continue with the purchase
            if (item.getInventory() < 1) {
                //print message SOLD OUT and return to Purchase Menu
                return "SOLD OUT";


            } else if (currentMoneyProvided < item.getPrice()) {
                return "Insufficient Funds";
            } else {
                // after the product is dispensed, it updates the inventory, returns to the purchase menu.
                item.setInventory(item.getInventory() - 1);

                //calculates change
                int customerChange = currentMoneyProvided - item.getPrice();
                currentMoneyProvided -= item.getPrice();

                //log.txt information
                String nowAsString = DateTimeUtils.getCurrentTimeAsString(DateTimeUtils.FORMAT); // logs current date-time
                appendingToLog(nowAsString + " " + item.getName() + " " + slotId + " $" + String.format("%.2f",((double)currentMoneyProvided)/100.00) + " $" + String.format("%.2f",((double)customerChange)/100.00) + "\n");


                //if valid product is selected, dispenses the product name , price and the change with the message related to the product type,also want to return the message
                return item.getName() + " costs $" + String.format("%.2f",((double)item.getPrice())/100.00) + " and here is your change: $" + String.format("%.2f",((double)customerChange)/100.00) + " " + item.getMessage();

            }
        }
    }


    public static String creatingAFile() throws IOException {
        File logFile = new File("Log.txt");
        logFile.createNewFile();
        return "File created";
    }


    public static void appendingToLog(String log) throws FileNotFoundException {
        File logFile = new File("Log.txt");
        boolean append = logFile.exists() ? true : false;
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, append))) {
            writer.append(log);
            writer.flush();
        }
    }


}

