package ru.roon.banking;

public class BalanceAction implements ActionAccount {

    public BalanceAction() {
    }

    @Override
    public void action(Account account) {
            System.out.println("Balance: " + account.getBalance());

    }
}
