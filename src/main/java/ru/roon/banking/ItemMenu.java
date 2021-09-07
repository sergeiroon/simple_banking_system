package ru.roon.banking;

public class ItemMenu {
    private int number;
    private String description;
    private ActionItem action;

    public ItemMenu(int number, String description, ActionItem action) {
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

    public ActionItem getAction() {
        return action;
    }

    public void setAction(ActionItem action) {
        this.action = action;
    }

    public void action() {
        action.action();
    }
}
