package ru.roon.banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dbName = retrieveDbName(args);
        AccountDao dataBase = new AccountDao(dbName);
        dataBase.createDateBase();
        Scanner scanner = new Scanner(System.in);
        List<ItemMenu> items = new ArrayList<>();
        List<ItemSubMenu> itemsSubMenu = new ArrayList<>();

        ActionAccount balance = new BalanceAction();
        ActionAccount logOut = new LogOutAction();
        ActionAccount exitSub = new ExitAction();
        ActionAccount addIncome = new AddIncomeAction(scanner, dataBase);
        DoTransfer transfer = new DoTransfer(scanner, dataBase);
        CloseAccount closeAccount = new CloseAccount(dataBase);

        itemsSubMenu.add(new ItemSubMenu(1, "Balance", balance));
        itemsSubMenu.add(new ItemSubMenu(2, "Add income", addIncome));
        itemsSubMenu.add(new ItemSubMenu(3, "Do transfer", transfer));
        itemsSubMenu.add(new ItemSubMenu(4, "Close account", closeAccount));
        itemsSubMenu.add(new ItemSubMenu(5, "Log out", logOut));
        itemsSubMenu.add(new ItemSubMenu(0, "Exit", exitSub));

        ActionItem createAccountAction = new CreateAccountAction(dataBase);
        ItemMenu createAccount = new ItemMenu(1, "Create an account", createAccountAction);
        items.add(createAccount);

        ActionItem logIntoAccountAction = new LogIntoAccountAction(scanner, dataBase);
        ItemMenu logIntoAccount = new ItemMenu(2, "Log into account", logIntoAccountAction);
        items.add(logIntoAccount);

        ActionItem exit = new ExitAction();
        ItemMenu exitItem = new ItemMenu(0, "Exit", exit);
        items.add(exitItem);

        AccountMenu subMenu = new AccountMenu(scanner, itemsSubMenu);
        Menu menu = new Menu(scanner, items);

        ((LogIntoAccountAction) logIntoAccountAction).setAccountMenu(subMenu);
        ((ExitAction) exitSub).setMenu(menu);
        ((ExitAction) exitSub).setSubMenu(subMenu);
        ((ExitAction) exit).setMenu(menu);
        ((ExitAction) exit).setSubMenu(subMenu);
        ((LogOutAction)logOut).setAccountMenu(subMenu);
        menu.selectItem();
    }

    private static String retrieveDbName(String[] args) {
        if (args.length > 0) {
            String nameArgument = args[0];
            if (nameArgument.equals("-fileName")) {
                return args[1];
            }
        }
        return null;
    }

}