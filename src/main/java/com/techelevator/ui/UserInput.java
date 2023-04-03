package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.VendingMachineItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);
    private static boolean discount = false;
    private static BigDecimal moneyInVendingMachine = new BigDecimal("0.00");

    public static String getHomeScreenOption() {
            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println("D) Display Vending Machine Items");
            System.out.println("P) Purchase");
            System.out.println("E) Exit");

            System.out.println();
            System.out.print("Please select an option: ");

            String selectedOption = scanner.nextLine();
            String option = selectedOption.trim().toLowerCase();

            if (option.equals("d")) {
                return "display";
            } else if (option.equals("p")) {
                return "purchase";
            }else if (option.equals("s")){
                return "sales";
            } else if (option.equals("e")) {
                return "exit";
            }
        return "";
    }

    public static String getPurchaseScreenOption(List<VendingMachineItem> vendingMachineItemList) {

            System.out.println("M) Feed Money");
            System.out.println("S) Select Item");
            System.out.println("F) Finish Transaction");

            System.out.println();
            System.out.print("Please select an option: ");

            String selectedOption = scanner.nextLine();
            String option = selectedOption.trim().toLowerCase();

            if (option.equals("m"))
            {
                return "feed";
            }
            else if (option.equals("s"))
            {
                return "selection";
            }
            else if (option.equals("f"))
            {
                return "finish";
            }
        return "";
    }

    public static void feedMoneyFunction() {
        System.out.println("Money in the Vending machine: $" + moneyInVendingMachine); //displays amount of money in machine

        System.out.println("How much do you want to add?");
        Scanner scanner = new Scanner(System.in);

        BigDecimal money = new BigDecimal("0.00"); //grabs user input
        int moneyInt = 0;
        try {
            BigDecimal moneyTestInput = new BigDecimal(scanner.nextLine());
            money = moneyTestInput;
            moneyInt = money.intValue();

        } catch (NumberFormatException e) {
            System.out.println("Not a number!");
        }




        if (moneyInt == 1 || moneyInt == 5 || moneyInt == 10 || moneyInt == 20) { //Vending machine won't accepts bill larger than 20
            moneyInVendingMachine = moneyInVendingMachine.add(money); //updates the money in machine
            System.out.println("Money in the Vending machine: $" + moneyInVendingMachine); //displays updated money in machine
            Logs.auditLog("MONEY FED: ", "", money, moneyInVendingMachine);
        } else {
            System.out.println("Money was not updated due to invalid input");
            System.out.print("Money in the Vending machine: $" + moneyInVendingMachine);
        }

    }

    public static void selectItem(List<VendingMachineItem> list) {
        UserOutput.displayVendingMachineItems(list); //display items in the vending machine
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the item you wish to purchase: ");

        String input = scanner.nextLine().trim(); //grabs user input

        String message = "";

        for (VendingMachineItem item : list) {

            if (input.equals(item.getSlot())) {
                if (item.getInventory() > 0) {
                    if (moneyInVendingMachine.doubleValue() >= item.getPrice().doubleValue()) {

                        //If we reached this point it means that we've successfully selected an item
                        BigDecimal sale = new BigDecimal(0);
                        BigDecimal moneyBeforePurchase = moneyInVendingMachine;

                        if(!discount) { //boolean variable found at top of this class UserInput decides whether a discount should be applied
                            moneyInVendingMachine = moneyInVendingMachine.subtract(item.getPrice()); //subtracts money from VendingMachine without discount

                            item.setInventoryBoughtFullPrice(item.getInventoryBoughtFullPrice() + 1);

                            sale = item.getPrice();  //sale is regular price because there is no discount

                            discount = true;
                        } else {                     //discount is true
                            moneyInVendingMachine = moneyInVendingMachine.subtract(item.getPrice().subtract(new BigDecimal("1.0"))); //discount applies $1.00 off

                            item.setInventoryBoughtWithDiscount(item.getInventoryBoughtWithDiscount() + 1); //updates the amount of items bought with discount

                            sale = item.getPrice().subtract(new BigDecimal("1.0")); //sale is discounted price
                            System.out.println("Discount applied");
                            discount = false; //switches discount on and off
                        }

                        item.setInventory(item.getInventory() - 1); //subtracts from inventory
                        VendingMachine.sales = VendingMachine.sales.add(sale); //adds to total sales list
                        UserOutput.displayVendingMachineItems(list); // display vending machine items again

                        message = item.getName()+ " $" + sale + " " + item.getMessage() + "\nMoney in the Vending machine: $" + moneyInVendingMachine; //puts in message
                        Logs.auditLog(item.getName(), item.getSlot(), moneyBeforePurchase, moneyInVendingMachine);
                        break;
                    } else {message = "You don't have enough money.\nMoney in the Vending machine: $" + moneyInVendingMachine; break; }


                } else message = "NO LONGER AVAILABLE."; break;

            } else message = "Invalid Input!"; //break;

        }
        System.out.println(message);
        //Function might want to return a map or list for sales report?? return salesMap
    }

    public static void getChange(BigDecimal moneyInVending) {
        BigDecimal change = moneyInVending.multiply(new BigDecimal("100"));

        int dollars = 0;
        int quarts = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        while (change.compareTo(BigDecimal.ZERO) == 1) {
            if (change.compareTo(new BigDecimal(100)) == 1 || change.compareTo(new BigDecimal(100)) == 0) {
                change = change.subtract(new BigDecimal(100));
                dollars += 1;
            } else if (change.compareTo(new BigDecimal(25)) == 1 || change.compareTo(new BigDecimal(25)) == 0) {
                change = change.subtract(new BigDecimal(25));
                quarts += 1;
            } else if (change.compareTo(new BigDecimal(10)) == 1 || change.compareTo(new BigDecimal(10)) == 0) {
                change = change.subtract(new BigDecimal(10));
                dimes += 1;
            } else if (change.compareTo(new BigDecimal(5)) == 1 || change.compareTo(new BigDecimal(5)) == 0) {
                change = change.subtract(new BigDecimal(5));
                nickels += 1;
            } else if (change.compareTo(new BigDecimal(1)) == 1 || change.compareTo(new BigDecimal(1)) == 0) {
                change = change.subtract(new BigDecimal(1));
                pennies += 1;
            }
        }
        System.out.println("Your change is: ");
        System.out.println("             " + dollars + " dollars");
        System.out.println("             " + quarts + " quarters");
        System.out.println("             " + dimes + " dimes");
        System.out.println("             " + nickels + " nickels");
        System.out.println("             " + pennies + " pennies");
    }

    public static void finishTransaction() {
        BigDecimal change = moneyInVendingMachine.setScale(2, RoundingMode.HALF_UP);
        getChange(change);
        Logs.auditLog("CHANGE GIVEN:", "", moneyInVendingMachine, BigDecimal.ZERO);
        moneyInVendingMachine = new BigDecimal("0.00");
    }

}
