package ru.roon.banking;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountMenu {
    private final String FORMAT_ITEM_MENU = "%d. %s";
    private boolean exit;
    private List<ItemSubMenu> items;
    private Scanner scanner;
    private Map<Integer, ItemSubMenu> itemByNumber;


    public AccountMenu(Scanner scanner, List<ItemSubMenu> items) {
        this.scanner = scanner;
        this.items = items;
        this.itemByNumber = items.stream().collect(Collectors.toMap(ItemSubMenu::getNumber, Function.identity()));

    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void selectItem(Account account) {
        while ((!exit)) {
            items.forEach(itemMenu -> {
                System.out.println(String.format(FORMAT_ITEM_MENU, itemMenu.getNumber(), itemMenu.getDescription()));
            });

            int item = scanner.nextInt();
            itemByNumber.get(item).getAction().action(account);
        }

    }
}
