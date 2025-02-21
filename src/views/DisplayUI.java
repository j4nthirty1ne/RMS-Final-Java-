package views;

import controller.Admin;
import services.Utils;

import java.util.Scanner;

import static services.Utils.scanner;

public class DisplayUI {
    public void displayUI() {
        System.out.println("Welcome to the Restaurant Management System!");
        System.out.println("1. Staff");
        System.out.println("2. Kitchen");
        System.out.println("3. Customer");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        Scanner scanner = null;
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        int choice = Utils.validateIntegerInput(scanner, "Enter your choice: ", 1, 5);
        if (choice == -1) return;
        switch (choice) {
            case 1:
                System.out.println("Staff");
                break;
            case 2:
                System.out.println("Kitchen");
                break;
            case 3:
                System.out.println("Customer");
                break;
            case 4:
                System.out.println("Admin");
                new Admin().adminPanel();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}
