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
        this.menu.add(new MenuItem("Burger", 5.99, "Main Course"));
        this.menu.add(new MenuItem("Pizza", 8.99, "Main Course"));
        this.menu.add(new MenuItem("Cola", 1.99, "Beverage"));
        this.menu.add(new MenuItem("Pasta", 7.99, "Main Course"));
        this.menu.add(new MenuItem("Salad", 4.99, "Appetizer"));
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

    public MenuItem getItemById(UUID id) {
        return this.menu.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}