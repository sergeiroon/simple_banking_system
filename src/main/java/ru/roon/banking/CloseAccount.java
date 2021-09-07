package ru.roon.banking;

public class CloseAccount implements ActionAccount {
    AccountDao accountDao;

    public CloseAccount (AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    @Override
    public void action(Account account) {
        accountDao.delete(account.getCard().getNumber());
        System.out.println("The account has been closed!");
    }
}
