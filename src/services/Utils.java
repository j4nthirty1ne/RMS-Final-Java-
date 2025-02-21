package services;


import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class Utils {

    // Helper method to validate integer input within a range
    public static int validateIntegerInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("b")) {
                return -1; // Return -1 to indicate the admin wants to go back
            }

            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Input out of range. Please enter a number between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric value.");
            }
        }
    }

    // Helper method to validate price input
    public static double validatePriceInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                double price = Double.parseDouble(input);
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please enter a valid positive number.");
                } else {
                    return price;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric value.");
            }
        }
    }

    // Helper method to display items as a formatted table
    public static void displayItemsAsTable(List<Map<String, Object>> items) {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }

        // Define column widths for formatting
        int idWidth = 5;
        int nameWidth = 20;
        int descriptionWidth = 30;
        int basePriceWidth = 12;
        int sellPriceWidth = 12;

        // Print table header
        System.out.printf(
                "%-" + idWidth + "s | %-" + nameWidth + "s | %-" + descriptionWidth + "s | %-" + basePriceWidth + "s | %-" + sellPriceWidth + "s%n",
                "ID", "Name", "Description", "Base Price", "Sell Price"
        );
        printSeparator(idWidth, nameWidth, descriptionWidth, basePriceWidth, sellPriceWidth);

        // Print each menu item
        for (Map<String, Object> menuItem : items) {
            System.out.printf(
                    "%-" + idWidth + "d | %-" + nameWidth + "s | %-" + descriptionWidth + "s | %" + basePriceWidth + ".2f | %" + sellPriceWidth + ".2f%n",
                    menuItem.get("item_id"),
                    truncate((String) menuItem.get("name"), nameWidth),
                    truncate((String) menuItem.get("description"), descriptionWidth),
                    menuItem.get("base_price"),
                    menuItem.get("sell_price")
            );
        }

    }

    // Helper method to print a separator line
    public static void printSeparator(int... columnWidths) {
        StringBuilder separator = new StringBuilder();
        for (int width : columnWidths) {
            separator.append("+").append("-".repeat(width + 1));
        }
        separator.append("+");
        System.out.println(separator);
    }

    // Helper method to truncate strings to fit column width
    public static String truncate(String text, int maxLength) {
        if (text == null) return "";
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }
}


