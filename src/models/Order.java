package models;

import services.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private List<MenuItem> items;
    private double totalPrice;
    private boolean isPaid;
    private String paymentMethod; // "QR Code" or "Cash"

    public Order() {
        this.items = new ArrayList<>();
        this.totalPrice = 0;
        this.isPaid = false;
    }

    // Order.java
    public void addItemById(MenuService menuService, UUID id) {
        MenuItem item = menuService.getItemById(id);
        if (item != null) {
            addItem(item);
            System.out.println(item.getName() + " added to cart!");
        } else {
            System.out.println("Item not found!");
        }
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("\n🛒 Your cart is empty!");
            return;
        }

        System.out.println("\n========== YOUR CART ==========");
        for (MenuItem item : items) {
            System.out.println("- " + item.getName() + " ($" + item.getPrice() + ")");
        }
        System.out.println("Total: $" + totalPrice);
    }

    public void makePayment(String method) {
        if (items.isEmpty()) {
            System.out.println("❌ You cannot pay for an empty cart!");
            return;
        }

        this.isPaid = true;
        this.paymentMethod = method;
        System.out.println("\n✅ Payment successful via " + method + "! Your order is being processed.");
    }

    public boolean isPaid() { return isPaid; }
    public double getTotalPrice() { return totalPrice; }

    public void setStatus(String status) {
        System.out.println("Order status updated to: " + status);

        if (status.equalsIgnoreCase("delivered")) {
            System.out.println("🎉 Thank you for ordering with us! Enjoy your meal!");
        }
    }

}
