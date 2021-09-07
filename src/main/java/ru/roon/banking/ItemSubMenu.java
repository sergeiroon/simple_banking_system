package ru.roon.banking;

public class ItemSubMenu {

    private int number;
    private String description;
    private ActionAccount action;

    public ItemSubMenu(int number, String description, ActionAccount action) {
        this.number = number;
        this.description = description;
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActionAccount getAction() {
        return action;
    }

    public void setAction(ActionAccount action) {
        this.action = action;
    }

}
