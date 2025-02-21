package services;


import java.util.*;

public class MenuItemManager {
    private final String name;
    private final double basePrice;
    private final double sellPrice;
    private final String category;
    private final int item_id;

    public MenuItemManager(String name , double basePrice , double sellPrice, String category, int item_id) {
        this.name = name;
        this.basePrice = basePrice;
        this.sellPrice = sellPrice;
        this.category = category;
        this.item_id = item_id;
    }

    private static List<Map<String, Object>> menuItems = new ArrayList<>();

    // ------------ Add multiple menu items in one session ------------
    public static void addMenuItems(Scanner scanner) {
        while (true) {
            //------------ Display category options ------------
            CategoryManager.displayCategories();

            //------------ Validate category number ------------
            int categoryNumber = Utils.validateIntegerInput(scanner, "Enter category number ([b] to go back): ", 1, CategoryManager.getCategories().size());
            if (categoryNumber == -1) return;

            String category = CategoryManager.getCategoryFromNumber(categoryNumber);

            if ("Unknown".equals(category)) {
                System.out.println("Invalid category number.");
                continue;
            }

            // ------------ Add menu item details ------------
            Map<String, Object> menuItem = new HashMap<>();
            String name = "";
            while (true) {
                System.out.print("Enter item name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Item name cannot be empty. Please try again.");
                } else {
                    break;
                }
            }
            menuItem.put("name", name);

            System.out.print("Enter item description: ");
            menuItem.put("description", scanner.nextLine().trim());

            // ------------ Validate base price ------------
            double basePrice = Utils.validatePriceInput(scanner, "Enter base price: ");
            menuItem.put("base_price", basePrice);

            // ------------ Validate sell price ------------
            double sellPrice = Utils.validatePriceInput(scanner, "Enter sell price: ");
            menuItem.put("sell_price", sellPrice);

            menuItem.put("category", category);
            menuItem.put("item_id", menuItems.size() + 1);
            menuItems.add(menuItem);
            System.out.println("Menu item added to category '" + category + "' successfully!");

            System.out.print("Do you want to add another item? (y/n): ");
            String continueAdding = scanner.nextLine().trim().toLowerCase();
            if (!continueAdding.equals("y")) {
                break;
            }
        }
    }

    // ------------ Update multiple menu items in one session ------------
    public static void updateMenuItems(Scanner scanner) {
        while (true) {
            // ------------ Display category options ------------
            CategoryManager.displayCategories();

            // ------------ Validate category number ------------
            int categoryNumber = Utils.validateIntegerInput(scanner, "Enter category number ([b] to go back): ", 1, CategoryManager.getCategories().size());
            if (categoryNumber == -1) return;

            String category = CategoryManager.getCategoryFromNumber(categoryNumber);

            if ("Unknown".equals(category)) {
                System.out.println("Invalid category number.");
                continue; //re-enter the category
            }

            // ------------ Filter items by category ------------
            List<Map<String, Object>> filteredItems = new ArrayList<>();
            for (Map<String, Object> menuItem : menuItems) {
                if (menuItem.get("category").equals(category)) {
                    filteredItems.add(menuItem);
                }
            }

            if (filteredItems.isEmpty()) {
                System.out.println("No items available in category '" + category + "'.");
                continue;
            }

            // Display items in the selected category as a formatted table
            System.out.println("\n--- Items in Category: " + category + " ---");
            Utils.displayItemsAsTable(filteredItems);

            // ------------ Validate item ID ------------
            int itemId = Utils.validateIntegerInput(scanner, "Enter the ID of the item to update ([b] to go back): ", 1, menuItems.size());
            if (itemId == -1) return;

            boolean found = false;

            for (Map<String, Object> menuItem : menuItems) {
                if (menuItem.get("item_id").equals(itemId) && menuItem.get("category").equals(category)) {
                    found = true;

                    System.out.println("\nUpdating item ID: " + itemId);
                    System.out.print("Enter new name (press Enter to skip): ");
                    String name = scanner.nextLine().trim();
                    if (!name.isEmpty()) menuItem.put("name", name);

                    System.out.print("Enter new description (press Enter to skip): ");
                    String description = scanner.nextLine().trim();
                    if (!description.isEmpty()) menuItem.put("description", description);

                    //------------ Validate base price ------------
                    System.out.print("Enter new base price (press Enter to skip): ");
                    String basePriceInput = scanner.nextLine().trim();
                    if (!basePriceInput.isEmpty()) {
                        try {
                            double basePrice = Double.parseDouble(basePriceInput);
                            if (basePrice < 0) {
                                System.out.println("Base price cannot be negative. Skipping update.");
                            } else {
                                menuItem.put("base_price", basePrice);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid base price. Skipping update.");
                        }
                    }

                    // ------------ Validate sell price ------------
                    System.out.print("Enter new sell price (press Enter to skip): ");
                    String sellPriceInput = scanner.nextLine().trim();
                    if (!sellPriceInput.isEmpty()) {
                        try {
                            double sellPrice = Double.parseDouble(sellPriceInput);
                            if (sellPrice < 0) {
                                System.out.println("Sell price cannot be negative. Skipping update.");
                            } else {
                                menuItem.put("sell_price", sellPrice);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid sell price. Skipping update.");
                        }
                    }

                    System.out.println("Item updated successfully!");
                    break;
                }
            }

            if (!found) {
                System.out.println("Invalid item ID or item does not belong to the selected category.");
            }


            System.out.print("Do you want to update another item? (y/n): ");
            String continueUpdating = scanner.nextLine().trim().toLowerCase();
            if (!continueUpdating.equals("y")) {
                break; // Exit the loop if the admin does not want to update more items
            }
        }
    }

    //------------ Remove multiple menu items in one session ------------
    public static void removeMenuItems(Scanner scanner) {
        while (true) {
            // ------------ Display category options ------------
            CategoryManager.displayCategories();

            // ------------ Validate category number ------------
            int categoryNumber = Utils.validateIntegerInput(scanner, "Enter category number ([b] to go back): ", 1, CategoryManager.getCategories().size());
            if (categoryNumber == -1) return;

            String category = CategoryManager.getCategoryFromNumber(categoryNumber);

            if ("Unknown".equals(category)) {
                System.out.println("Invalid category number.");
                continue;
            }

            // ------------ Filter items by category ------------
            List<Map<String, Object>> filteredItems = new ArrayList<>();
            for (Map<String, Object> menuItem : menuItems) {
                if (menuItem.get("category").equals(category)) {
                    filteredItems.add(menuItem);
                }
            }

            if (filteredItems.isEmpty()) {
                System.out.println("No items available in category '" + category + "'.");
                continue;
            }

            // ------------ Display items in the selected category as a formatted table ------------
            System.out.println("\n--- Items in Category: " + category + " ---");
            Utils.displayItemsAsTable(filteredItems);

            //------------ Validate item ID ------------
            int itemId = Utils.validateIntegerInput(scanner, "Enter the ID of the item to remove ([b] to go back): ", 1, menuItems.size());
            if (itemId == -1) return;

            boolean removed = false;

            Iterator<Map<String, Object>> iterator = menuItems.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> menuItem = iterator.next();
                if (menuItem.get("item_id").equals(itemId) && menuItem.get("category").equals(category)) {
                    iterator.remove();
                    removed = true;
                    System.out.println("Item removed successfully!");
                    break;
                }
            }

            if (!removed) {
                System.out.println("Invalid item ID or item does not belong to the selected category.");
            }


            System.out.print("Do you want to remove another item? (y/n): ");
            String continueRemoving = scanner.nextLine().trim().toLowerCase();
            if (!continueRemoving.equals("y")) {
                break;
            }
        }
    }

    //------------ View all menu items separated by category ------------
    public static void viewMenuItemsByCategorySeparately() {
        if (menuItems.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }

        //------------ Group items by category ------------
        Map<String, List<Map<String, Object>>> groupedItems = new LinkedHashMap<>();
        for (Map<String, Object> menuItem : menuItems) {
            String category = (String) menuItem.get("category");
            groupedItems.computeIfAbsent(category, k -> new ArrayList<>()).add(menuItem);
        }

        //------------ Display items in each category ------------
        for (Map.Entry<String, List<Map<String, Object>>> entry : groupedItems.entrySet()) {
            String category = entry.getKey();
            List<Map<String, Object>> items = entry.getValue();

            System.out.println("\n--- Category: " + category + " ---");
            Utils.displayItemsAsTable(items);
        }
    }

    // ------------ Display menu items filtered by category ------------
    public static void displayItemsByCategory(Scanner scanner) {
        // Display category options
        CategoryManager.displayCategories();

        //------------ Validate category number ------------
        int categoryNumber = Utils.validateIntegerInput(scanner, "Enter category number ([b] to go back): ", 1, CategoryManager.getCategories().size());
        if (categoryNumber == -1) return;

        String category = CategoryManager.getCategoryFromNumber(categoryNumber);

        if ("Unknown".equals(category)) {
            System.out.println("Invalid category number.");
            return;
        }

        // ------------ Filter items by category ------------
        List<Map<String, Object>> filteredItems = new ArrayList<>();
        for (Map<String, Object> menuItem : menuItems) {
            if (menuItem.get("category").equals(category)) {
                filteredItems.add(menuItem);
            }
        }

        if (filteredItems.isEmpty()) {
            System.out.println("No items available in category '" + category + "'.");
            return;
        }

        // ------------ Display items in the selected category  ------------
        System.out.println("\n--- Items in Category: " + category + " ---");
        Utils.displayItemsAsTable(filteredItems);
    }

    public static void removeMenuItemsByCategory(String category) {
        Iterator<Map<String, Object>> iterator = menuItems.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> menuItem = iterator.next();
            if (menuItem.get("category").equals(category)) {
                iterator.remove();
            }
        }
    }

}
