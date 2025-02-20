// MenuService.java
package services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import models.MenuItem;

public class MenuService {
    private final List<MenuItem> menu = new ArrayList<>();

    public MenuService() {
        this.menu.add(new MenuItem(1,"Burger", 5.99, "Main Course"));
        this.menu.add(new MenuItem(2, "Pizza", 8.99, "Main Course"));
        this.menu.add(new MenuItem(3, "Cola", 1.99, "Beverage"));
        this.menu.add(new MenuItem(4, "Pasta", 7.99, "Main Course"));
        this.menu.add(new MenuItem(5, "Salad", 4.99, "Appetizer"));
    }

    public List<MenuItem> getItemsByCategory(String category) {
        return this.menu.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<MenuItem> getMenu() {
        return this.menu;
    }

    public MenuItem getItem(String itemName) {
        return this.menu.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
    }

    public MenuItem getItemById(int id) {
        return this.menu.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}