package ru.roon.banking;

public class LogOutAction implements ActionAccount {

    private AccountMenu accountMenu;

    public LogOutAction(AccountMenu accountMenu) {
        this.accountMenu = accountMenu;
    }

    public LogOutAction() {

    }


    @Override
    public void action(Account account) {
        accountMenu.setExit(true);
    }

    public AccountMenu getAccountMenu() {
        return accountMenu;
    }

    public void setAccountMenu(AccountMenu accountMenu) {
        this.accountMenu = accountMenu;
    }
}
