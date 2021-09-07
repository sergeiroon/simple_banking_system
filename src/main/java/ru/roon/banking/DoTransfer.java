package ru.roon.banking;

import java.util.Scanner;

public class DoTransfer implements ActionAccount {

    Scanner scanner;
    AccountDao accountDao;

    public DoTransfer(Scanner scanner, AccountDao accountDao) {
        this.scanner = scanner;
        this.accountDao = accountDao;
    }

    @Override
    public void action(Account account) {
        System.out.println("Transfer\n" +
            "Enter card number:");
        long numberCardTo = scanner.nextLong();

        if (!Card.checkLuhnAlg(numberCardTo)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }
        Account receiver = accountDao.findAccount(numberCardTo);
        if (receiver == null) {
            System.out.println("Such a card does not exist.");
            return;
        }

        if (account.getCard().getNumber() == numberCardTo) {
            System.out.println("You can't transfer money to the same account!");
            return;
        }
        System.out.println("Enter how much money you want to transfer:");
        long sumTransfer = scanner.nextLong();

        if (account.getBalance() < sumTransfer) {
            System.out.println("Not enough money!");
            return;
        }
        accountDao.transfer(account.getCard().getNumber(), numberCardTo, sumTransfer);
        account.setBalance(account.getBalance() - sumTransfer);
        System.out.println("Success!");
    }

}
