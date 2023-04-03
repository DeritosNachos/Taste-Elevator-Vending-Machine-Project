package com.techelevator.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineItemList {
    private List<VendingMachineItem> listVendingMachineItem = new ArrayList<>();

    public VendingMachineItemList(File inputFile) {
        try(Scanner input = new Scanner(inputFile)) {
            while(input.hasNextLine()) {

                String line = input.nextLine().trim();

                String[] info = line.split(",");
                String slot = info[0];
                String name = info[1];
                BigDecimal price = new BigDecimal(info[2]);
                String type = info[3];

                VendingMachineItem snack = new VendingMachineItem(slot, name, price, type);
                //need to initialize quantity for each item as well

                listVendingMachineItem.add(snack);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public List<VendingMachineItem> getListVendingMachineItem() {
        return listVendingMachineItem;
    }
}
