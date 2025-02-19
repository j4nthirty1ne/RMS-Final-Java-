import java.net.Authenticator;
import java.sql.*;
import java.util.*;

import models.MenuItem;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;
import roles.Admin;
import roles.Customer;
import services.MenuService;
import models.User;
import services.UserService;
import services.Authorization;
import models.Order;


class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://202.178.125.77:3333/ros_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234567890";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

// PrintMenu.java
class PrintMenu {
    MenuService menuService = new MenuService();

    private static void printMenuTable(List<MenuItem> menu) {
        Table table = new Table(4, BorderStyle.UNICODE_BOX_WIDE, ShownBorders.ALL);
        String[] columnNames = {"UUID", "Name", "Price", "Category"};

        for (String columnName : columnNames) {
            table.addCell(columnName, new CellStyle(CellStyle.HorizontalAlign.center));
        }

        for (MenuItem item : menu) {
            table.addCell(item.getId().toString(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(item.getName(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(String.format("$%.2f", item.getPrice()), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(item.getCategory(), new CellStyle(CellStyle.HorizontalAlign.center));
        }

        System.out.println(table.render());
    }

    public void printMenu(List<MenuItem> menu) {
        printMenuTable(menu);
    }
}

class DisplayUI {
    public void showWelcomeMessage() {
        System.out.println("=============================================");
        System.out.println("Welcome to the Restaurant Ordering System");
        System.out.println("=============================================");
    }
}

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
            System.out.println("\n1. Add item to cart by ID");
            System.out.println("2. View cart");
            System.out.println("3. Make payment");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter item ID: ");
                    String itemId = scanner.next();
                    order.addItemById(menuService, UUID.fromString(itemId));
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