package views;

import Controller.AdminController;

public class DisplayUI {
    private final AdminController adminController = new AdminController();
    public void showWelcomeMessage() {
        System.out.println("=============================================");
        System.out.println("Welcome to the Restaurant Ordering System");
        System.out.println("=============================================");
    }
}
