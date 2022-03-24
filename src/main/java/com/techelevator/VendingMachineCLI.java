package com.techelevator;

import com.techelevator.view.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_FINISH_TRANSACTION = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_FINISH_TRANSACTION};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};



	private Menu menu;
	private VendingMachine vendingMachine;
	private String[] menuOptions = MAIN_MENU_OPTIONS;

	// Scanner
	Scanner scanner = new Scanner(System.in);

	// Constructor
	public VendingMachineCLI(Menu menu, VendingMachine vendingMachine) {
		this.menu = menu;
		this.vendingMachine = vendingMachine;

	}

	public void run() {
		boolean runProgram = true;
		while (runProgram) {
			String choice = (String) menu.getChoiceFromOptions(menuOptions);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase - METHOD-SELECT PROD
				menuOptions = PURCHASE_MENU_OPTIONS;

			} else if(choice.equals(MAIN_MENU_FINISH_TRANSACTION)){
				runProgram = false;
			} else if(choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)){
				// feed money method ... ask user for payment
				System.out.println("Please enter money (accepting $1,$5,$10,$20) : ");

				String customerInput = scanner.nextLine();
				int convertedMoney = Integer.parseInt(customerInput);

				try {
					int customerMoney= vendingMachine.feedMoney(convertedMoney);
					double convertedCustomerMoney = ((double)customerMoney)/100.00;
					System.out.println("Current Money Provided: $" + String.format("%.2f",convertedCustomerMoney));
				} catch (InvalidMoneyException e) {
					System.out.print("Cannot feed money " + e.getMessage());
				}catch (FileNotFoundException e) {
					System.out.println("File not found: " + e.getMessage());
				}


			}else if(choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
				//display menu items
				displayItems();

				// scanner to get item
					System.out.println("Please enter the item code:");
					String customerInputTwo = scanner.nextLine().toUpperCase();
				String output = null;
				try {
					output = vendingMachine.selectProduct(customerInputTwo);
				} catch (FileNotFoundException e) {
					System.out.println("File not found: " + e.getMessage());
				}

				System.out.println(output);

					// method on vending machine to select product


			}else if(choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)){
				menuOptions = MAIN_MENU_OPTIONS;

				int change1 = vendingMachine.getCurrentMoneyProvided();
				int change = vendingMachine.getCurrentMoneyProvided();

				int quarters = change/ 25;

				change = change - (quarters * 25);
				int dimes = change / 10;

				change = change - dimes * 10;
				int nickels = change / 5;

				change = change - nickels * 5;
				int pennies = change ;

				System.out.println("Your change is : " + quarters + " quarters " + dimes + " dimes " + nickels + " nickels " + pennies + " pennies"  );
				vendingMachine.setCurrentMoneyProvided(0);
				System.out.println( );
				System.out.println("Current Money Provided: $" + String.format("%.2f",(double)vendingMachine.getCurrentMoneyProvided()));

				//log.txt information
				String nowAsString = DateTimeUtils.getCurrentTimeAsString(DateTimeUtils.FORMAT); // logs current date-time
				try {
					vendingMachine.appendingToLog(nowAsString + " GIVE CHANGE: " + " $" + String.format("%.2f",(double)change1/100.00) + " $" + String.format("%.2f",((double)vendingMachine.getCurrentMoneyProvided())) + "\n");
				} catch (FileNotFoundException e) {
					System.out.println("File not found:" + e.getMessage());;
				}

			}

		}
	}

	private void displayItems() {
		// display vending machine items
		Map<String, Product> ourProductMap = vendingMachine.getProductMap();
		System.out.println(String.format("   %-18.18s %s %s", "Product Name", "Price", "Quantity"));
		for (Map.Entry<String, Product> entry : ourProductMap.entrySet()) {
			if (entry.getValue().getInventory() < 1){
				System.out.println("SOLD OUT");
			} else {
				System.out.println(String.format("%-2.2s %-18.18s %3.2f    %d", entry.getKey(), entry.getValue().getName(),
						(double) (entry.getValue().getPrice()) / 100.00, entry.getValue().getInventory()));
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachine vendingMachine = new VendingMachine();
		try {
			vendingMachine.fileReader();
		} catch (FileNotFoundException e) {
			System.out.println("Could not read file" + e.getMessage());
		}

		try {
			vendingMachine.creatingAFile();
		} catch (IOException e) {
			System.out.println("File already exists " + e.getMessage());
		}

		VendingMachineCLI cli = new VendingMachineCLI(menu,vendingMachine);
		cli.run();


	}










}
