import java.util.*;

import models.MenuItem;
import services.MenuService;
import models.Order;
import views.DisplayUI;
import views.PrintMenu;


// Main.java
class Main {
    public static void main(String[] args) {
        DisplayUI displayUI = new DisplayUI();
        displayUI.showWelcomeMessage();

        MenuService menuService = new MenuService();
        List<MenuItem> menu = menuService.getMenu();
        PrintMenu printMenu = new PrintMenu();
        printMenu.printMenu(menu);

        Order order = new Order();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add to cart");
            System.out.println("2. View cart");
            System.out.println("3. Make payment");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter item ID: ");
                    int itemId = scanner.nextInt();
                    order.addItemById(menuService, itemId);
                    break;
                case 2:
                    order.viewCart();
                    break;
                case 3:
                    System.out.print("Enter payment method (QR Code/Cash): ");
                    String method = scanner.next();
                    order.makePayment(method);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}