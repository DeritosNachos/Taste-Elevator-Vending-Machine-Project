package com.techelevator.models;

import java.math.BigDecimal;

public class VendingMachineItem {
    private String name;
    private String slot;
    private String message;
    private BigDecimal price;
    private String type;
    private int inventory = 6;
    private int inventoryBoughtFullPrice = 0;
    private int inventoryBoughtWithDiscount = 0;

    public VendingMachineItem(String slot, String name, BigDecimal price, String type) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSlot() {
        return slot;
    }

    public String getMessage() {
        String variety = this.type.toLowerCase();
        if (variety.equals("munchy")) {
            message = "Munchy, Munchy, so Good!";
        } else if (variety.equals("candy")) {
            message = "Sugar, Sugar, so Sweet!";
        } else if (variety.equals("drink")) {
            message = "Drinky, Drinky, Slurp Slurp!";
        } else if (variety.equals("gum")) {
            message = "Chewy, Chewy, Lots O Bubbles!";
        } else {
            message = "Invalid type";
        }
        return message;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }


    public int getInventoryBoughtWithDiscount() {
        return inventoryBoughtWithDiscount;
    }

    public void setInventoryBoughtWithDiscount(int inventoryBoughtWithDiscount) {
        this.inventoryBoughtWithDiscount = inventoryBoughtWithDiscount;
    }

    public int getInventoryBoughtFullPrice() {
        return inventoryBoughtFullPrice;
    }

    public void setInventoryBoughtFullPrice(int inventoryBoughtFullPrice) {
        this.inventoryBoughtFullPrice = inventoryBoughtFullPrice;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        if (inventory == 0){
            return this.slot + " " +  this.name + " " + this.price + " " + this.type + " NO LONGER AVAILABLE";
        }
        return this.slot + " " +  this.name + " " + this.price + " " + this.type + " " + this.inventory ;
    }
}
