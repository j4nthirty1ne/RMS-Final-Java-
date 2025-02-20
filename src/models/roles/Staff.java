package models.roles;

import models.Order;

public class Staff {
    public Staff() {
    }

    public void updateOrderStatus(Order order, String status) {
        order.setStatus(status);
        System.out.println("Order status updated to: " + status);
    }
}