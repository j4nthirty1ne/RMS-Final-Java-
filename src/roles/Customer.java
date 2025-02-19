package roles;

import models.MenuItem;
import models.Order;
import java.util.Scanner;

public class Customer {
    private String name;
    private Order order;

    public Customer(String name) {
        this.name = name;
        this.order = new Order();
    }

    public void viewMenu(MenuItem[] menu) {
        System.out.println("\n========== RESTAURANT MENU ==========");
        for (MenuItem item : menu) {
            System.out.println("- " + item.getName() + " ($" + item.getPrice() + ")");
        }
    }

    public void placeOrder(MenuItem[] menu) {
        Scanner scanner = new Scanner(System.in);
        boolean ordering = true;

        while (ordering) {
            System.out.print("\nEnter item name to add (or 'done' to finish): ");
            String itemName = scanner.nextLine();

            if (itemName.equalsIgnoreCase("done")) break;

            boolean itemFound = false;
            for (MenuItem item : menu) {
                if (item.getName().equalsIgnoreCase(itemName)) {
                    order.addItem(item);
                    System.out.println("✔ " + item.getName() + " added to cart!");
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                System.out.println("❌ Item not found! Try again.");
            }
        }
    }

    public void viewCart() {
        order.viewCart();
    }

    public void makePayment() {
        Scanner scanner = new Scanner(System.in);
        if (order.getTotalPrice() == 0) {
            System.out.println("❌ Your cart is empty. Please add items first.");
            return;
        }

        System.out.println("\nChoose payment method:\n1. QR Code (Bakong)\n2. Cash Payment");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                order.makePayment("QR Code (Bakong)");
                break;
            case 2:
                order.makePayment("Cash");
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }
}
