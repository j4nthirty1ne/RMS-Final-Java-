package services;

import java.util.Scanner;
import models.User;

public class Authorization {
    public void userAuth() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Enter role (Customer/Staff/Admin): ");
                    String role = scanner.nextLine();
                    userService.register(newUsername, newPassword, role);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    loggedInUser = userService.login(username, password);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        if (loggedInUser != null) {
            switch (loggedInUser.getRole()) {
                case "Admin":
                    System.out.println("Welcome, Admin! You can manage menu and reports.");
                    break;
                case "Staff":
                    System.out.println("Welcome, Staff! You can process orders.");
                    break;
                case "Customer":
                    System.out.println("Welcome, Customer! You can place an order.");
                    break;
                default:
                    System.out.println("Unknown role.");
            }
        }
    }
}