package ru.roon.banking;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    private final String FORMAT_ITEM_MENU = "%d. %s";
    private Scanner scanner;
    private Map<Integer, ItemMenu> itemByNumber;
    private List<ItemMenu> items;
    private boolean exit;
    public Menu(Scanner scanner,
                List<ItemMenu> items) {
        this.scanner = scanner;
        this.items = items;
        this.itemByNumber = items.stream().collect(Collectors.toMap(ItemMenu::getNumber, Function.identity()));
    }

    public void selectItem() {
        while ((!exit)) {
            items.forEach(itemMenu -> {
                System.out.println(String.format(FORMAT_ITEM_MENU, itemMenu.getNumber(), itemMenu.getDescription()));
            });

            int item = scanner.nextInt();
            itemByNumber.get(item).action();
        }

    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
