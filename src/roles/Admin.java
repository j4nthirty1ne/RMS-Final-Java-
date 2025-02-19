// Source code is decompiled from a .class file using FernFlower decompiler.
package roles;

import java.util.List;
import models.MenuItem;

public class Admin {
    public Admin() {
    }

    public void addMenuItem(List<MenuItem> menu, MenuItem item) {
        menu.add(item);
        System.out.println("Added: " + item.getName());
    }

    public void removeMenuItem(List<MenuItem> menu, String itemName) {
        menu.removeIf((item) -> {
            return item.getName().equalsIgnoreCase(itemName);
        });
        System.out.println("Removed: " + itemName);
    }
}
