package services;

// MenuItem.java

import java.util.UUID;

public class MenuItem {
    private UUID id;
    private String name;
    private double price;
    private String category;

    public MenuItem(String name, double price, String category) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}