package ru.roon.banking;

import java.util.Scanner;

public class AddIncomeAction implements ActionAccount {

    Scanner scanner;
    AccountDao dataBase;

    public AddIncomeAction(Scanner scanner, AccountDao dataBase) {
        this.scanner = scanner;
        this.dataBase = dataBase;
    }

    @Override
    public void action(Account account) {
        System.out.println("Enter income:");
        long sum = scanner.nextLong();
        dataBase.addBalance(sum, account.getCard().getNumber());
        account.setBalance(account.getBalance() + sum);
        System.out.println("Income was added!");
    }
}
