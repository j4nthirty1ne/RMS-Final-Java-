package controllers;

import models.MenuItem;
import models.Order;
import services.OrderService;
import java.util.List;

public class OrderController {
    private Order order;

    public OrderController() {
        this.order = new Order();
    }

    public void addItemById(List<MenuItem> menu, int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                order.addItem(item);
                System.out.println("Added: " + item.getName());
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void makePayment(String method) {
        order.setStatus("In Progress");
        System.out.println("Payment successful! Order is now In Progress.");
        int orderId = OrderService.saveOrder(order);
        System.out.println("Order ID: " + orderId);
    }
}
