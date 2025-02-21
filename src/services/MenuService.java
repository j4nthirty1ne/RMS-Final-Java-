// MenuService.java
package services;

import java.util.ArrayList;
import java.util.List;
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
        this.menu.add(new MenuItem(6, "Ice Cream", 2.99, "Dessert"));
        this.menu.add(new MenuItem(7, "Coffee", 2.49, "Beverage"));
        this.menu.add(new MenuItem(8, "Tea", 1.99, "Beverage"));
        this.menu.add(new MenuItem(9, "Bread", 1.49, "Side Dish"));
        this.menu.add(new MenuItem(10, "Fries", 2.49, "Side Dish"));
        this.menu.add(new MenuItem(11, "Soup", 3.99, "Appetizer"));
        this.menu.add(new MenuItem(12, "Sandwich", 4.99, "Main Course"));
        this.menu.add(new MenuItem(13, "Apple", 0.99, "Fruit"));
        this.menu.add(new MenuItem(14, "Orange", 0.99, "Fruit"));
        this.menu.add(new MenuItem(15, "Banana", 0.99, "Fruit"));
        this.menu.add(new MenuItem(16, "Milk", 1.49, "Beverage"));
        this.menu.add(new MenuItem(17, "Water", 0.99, "Beverage"));

    }

    // Add this method to get items by price range
    public List<MenuItem> getItemsByPriceRange(double minPrice, double maxPrice) {
        return this.menu.stream()
                .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Add this method to get items by category and price range
    public List<MenuItem> getItemsByCategoryAndPriceRange(String category, double minPrice, double maxPrice) {
        return this.menu.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Add this method to get items by category
    public List<MenuItem> getItemsByCategory(String category) {
        return this.menu.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Add this method to get all items from the menu
    public List<MenuItem> getMenu() {
        return this.menu;
    }

    // Add this method to get item by name from the menu
    public MenuItem getItem(String itemName) {
        return this.menu.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
    }

    // Add this method to get item by ID from the menu
    public MenuItem getItemById(int id) {
        return this.menu.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}