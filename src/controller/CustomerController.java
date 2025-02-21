package controller;

import java.util.*;
import models.MenuItem;
import services.MenuService;
import models.Order;
import views.DisplayUI;
import views.PrintMenu;

public class CustomerController {
    public CustomerController() {
        DisplayUI displayUI = new DisplayUI();
        displayUI.showWelcomeMessage();

        MenuService menuService = new MenuService();
        List<MenuItem> menu = menuService.getMenu();
        PrintMenu printMenu = new PrintMenu();
        printMenu.printMenu(menu);

        Order order = new Order();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Show all Food & Beverage");
            System.out.println("2. View by Category");
            System.out.println("3. Order Now");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printMenu.printMenu(menu);
                    break;
                case 2:
                    System.out.println("Enter category: ");

                    String category = scanner.next();
                    List<MenuItem> items = menuService.getItemsByCategory(category);
                    printMenu.printMenu(items);
                    break;
                case 3:
                    System.out.println("Which item would you like to add to cart?");
                    System.out.print("Enter item ID: ");
                    int itemId = scanner.nextInt();
                    order.addItemById(menuService, itemId);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

//        while (true) {
//            System.out.println("\n1. Add to cart");
//            System.out.println("2. View cart");
//            System.out.println("0. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Which item would you like to add to cart?");
//                    System.out.print("Enter item ID: ");
//                    int itemId = scanner.nextInt();
//                    order.addItemById(menuService, itemId);
//                    break;
//                case 2:
//                    order.viewCart();
//                    break;
//                case 0:
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("Invalid choice!");
//            }
//        }
    }
}