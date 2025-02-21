package services;


import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryManager {

    @Getter
    private static final List<String> categories = new ArrayList<>(List.of("Appetizers", "Main Course", "Beverages", "Desserts"));

    // display available categories
    public static void displayCategories() {
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        System.out.println("Available Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
    }

    //  get category name from number
    public static String getCategoryFromNumber(int categoryNumber) {
        if (categoryNumber < 1 || categoryNumber > categories.size()) {
            return "Unknown";
        }
        return categories.get(categoryNumber - 1);
    }

    // Manage categories (add, remove, or view)
    public static void manageCategories(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manage Categories ---");
            System.out.println("1. Add Category");
            System.out.println("2. Remove Category");
            System.out.println("3. View Categories");

            int choice = Utils.validateIntegerInput(scanner, "Enter your choice ([b] to go back): ", 1, 3);
            if (choice == -1) return;

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the new category ([b] to go back): ");
                    String newCategory = scanner.nextLine().trim();

                    if (newCategory.equalsIgnoreCase("b")) {
                        break;
                    }

                    if (!newCategory.isEmpty()) {
                        categories.add(newCategory);
                        System.out.println("Category '" + newCategory + "' added successfully!");
                    } else {
                        System.out.println("Category name cannot be empty.");
                    }
                    break;

                case 2:
                    displayCategories();
                    int categoryNumber = Utils.validateIntegerInput(scanner, "Enter the number of the category to remove ([b] to go back): ", 1, categories.size());
                    if (categoryNumber == -1) break;

                    String categoryToRemove = getCategoryFromNumber(categoryNumber);
                    if ("Unknown".equals(categoryToRemove)) {
                        System.out.println("Invalid category number.");
                    } else {
                        System.out.print("Are you sure you want to remove the category '" + categoryToRemove + "' and all its items? (y/n): ");
                        String confirm = scanner.nextLine().trim().toLowerCase();
                        if (confirm.equals("y")) {
                            // Remove the category
                            categories.remove(categoryToRemove);

                            // Remove all menu items in this category
                            MenuItemManager.removeMenuItemsByCategory(categoryToRemove);

                            System.out.println("Category '" + categoryToRemove + "' and all its items removed successfully!");
                        } else {
                            System.out.println("Removal canceled.");
                        }
                    }
                    break;

                case 3:
                    displayCategories();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}