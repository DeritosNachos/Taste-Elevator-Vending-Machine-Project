package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.VendingMachineItem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class Logs {

    public static void auditLog(String type, String slot, BigDecimal moneyTendered, BigDecimal moneyInVending) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        LocalTime time = LocalTime.now();
        formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String formattedTime = time.format(formatter);
        String log = formattedDate + " " + formattedTime + " " + type + " " + slot + " $" + moneyTendered + " $" + moneyInVending; //fix to be just date and time
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("audit.txt", true))){
            writer.println(log);
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println("The requested file could not be found.");
        }
    }

    public static void salesReport(List<VendingMachineItem> list) {
        String log = "Taste Elevator Sales Report";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        LocalTime time = LocalTime.now();
        formatter = DateTimeFormatter.ofPattern("hh-mm-ss");
        String formattedTime = time.format(formatter);
        String fileTitle = "sales" + formattedDate + formattedTime + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileTitle, true))){
            //System.out.println(log);
            writer.println(log);
            for (VendingMachineItem item: list) {
                log = item.getName() + "|" + item.getInventoryBoughtFullPrice() + "|" + item.getInventoryBoughtWithDiscount();
                //System.out.println(log);
                writer.println(log);
            }
            writer.println("TOTAL SALES: " + VendingMachine.sales);
            //System.out.println("TOTAL SALES: " + VendingMachine.sales);
        } catch (FileNotFoundException e) {
            System.out.println("The requested file could not be found.");
        }

    }

}
