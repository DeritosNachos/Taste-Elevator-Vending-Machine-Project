package com.techelevator.ui;

import com.techelevator.models.VendingMachineItem;
import com.techelevator.models.VendingMachineItemList;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{


    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayPurchaseScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Purchases");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayVendingMachineItems(List<VendingMachineItem> list) {

        System.out.println();
        for(VendingMachineItem item: list) {
            System.out.println(item.toString());
        }
        System.out.println();
        System.out.println();
    }

    //maybe create the audit log and purchase report methods here?

}
