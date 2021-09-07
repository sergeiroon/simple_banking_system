package ru.roon.banking;

public class ExitAction implements ActionItem, ActionAccount {

    private Menu menu;
    private AccountMenu subMenu;
    public ExitAction(Menu menu, AccountMenu accountMenu) {
        this.menu = menu;
        this.subMenu = accountMenu;
    }

    public ExitAction() {

    }
    @Override
    public void action() {
        menu.setExit(true);
        subMenu.setExit(true);
        System.out.println("Bye!");
    }
    @Override
    public void action(Account account) {
        menu.setExit(true);
        subMenu.setExit(true);
        System.out.println("Bye!");
    }
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public AccountMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(AccountMenu subMenu) {
        this.subMenu = subMenu;
    }
}
