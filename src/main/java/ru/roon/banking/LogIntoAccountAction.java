package ru.roon.banking;

import java.util.Scanner;

public class LogIntoAccountAction implements ActionItem {
    private AccountMenu accountMenu;
    Scanner scanner;
    AccountDao accounts;
    public LogIntoAccountAction(Scanner scanner,
                                AccountDao accounts) {
        this.accounts = accounts;
        this.scanner = scanner;
    }


    @Override
    public void action() {
        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();
        System.out.println("Enter your PIN:");
        int pin = scanner.nextInt();
        Account account = accounts.findAccount(cardNumber, pin);
        if (account == null) {
            System.out.println("Wrong card number or PIN!");
        } else {
            accountMenu.setExit(false);
            System.out.println("You have successfully logged in!");
                accountMenu.selectItem(account);
        }
    }

    public AccountMenu getAccountMenu() {
        return accountMenu;
    }

    public void setAccountMenu(AccountMenu accountMenu) {
        this.accountMenu = accountMenu;
    }
}
