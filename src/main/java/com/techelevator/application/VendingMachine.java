package com.techelevator.application;

import com.techelevator.models.VendingMachineItemList;
import com.techelevator.ui.Logs;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.math.BigDecimal;

public class VendingMachine 
{
    public static BigDecimal sales = new BigDecimal("0.00");

    public void run()
    {
        VendingMachineItemList list = new VendingMachineItemList(new File("catering.csv"));

        while(true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            if(choice.equals("display"))
            {
                UserOutput.displayVendingMachineItems(list.getListVendingMachineItem());
            }
            else if(choice.equals("purchase")) {
                while (true) {
                    UserOutput.displayPurchaseScreen();
                    String select = UserInput.getPurchaseScreenOption(list.getListVendingMachineItem());
                     if (select.equals("feed")) {
                            UserInput.feedMoneyFunction();
                    }
                    else if (select.equals("selection")) {
                        UserInput.selectItem(list.getListVendingMachineItem());
                    }
                    else if (select.equals("finish")) {
                        System.out.println("reached finish transaction");
                        UserInput.finishTransaction();
                        break;
                    }
                }
            } else if (choice.equals("sales")){
                Logs.salesReport(list.getListVendingMachineItem());
            }
            else if(choice.equals("exit")) {
                System.out.println("Thank you for using Taste Elevator!");
                break;
            }
        }
    }
}
