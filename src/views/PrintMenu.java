package views;

import models.MenuItem;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import services.MenuService;

import java.util.List;

// PrintMenu.java
public class PrintMenu {
    MenuService menuService = new MenuService();

    private static void printMenuTable(List<MenuItem> menu) {
        Table table = new Table(4, BorderStyle.UNICODE_BOX_WIDE, ShownBorders.ALL);
        String[] columnNames = {"ID", "Name", "Price", "Category"};

        for (String columnName : columnNames) {
            table.addCell(columnName, new CellStyle(CellStyle.HorizontalAlign.center));
        }

        for (MenuItem item : menu) {
            table.addCell(String.valueOf(item.getId()), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(item.getName(), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(String.format("$%.2f", item.getPrice()), new CellStyle(CellStyle.HorizontalAlign.center));
            table.addCell(item.getCategory(), new CellStyle(CellStyle.HorizontalAlign.center));
        }

        System.out.println(table.render());
    }

    public void printMenu(List<MenuItem> menu) {
        printMenuTable(menu);
    }
}
