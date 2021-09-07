package ru.roon.banking;

public class CreateAccountAction implements ActionItem {

    private static final String FORMAT_MESSAGE = "Your card has been created\n" +
        "Your card number:\n" +
        "%d\n" +
        "Your card PIN:\n" +
        "%d";

    private AccountDao accounts;

    public CreateAccountAction(AccountDao accounts) {
        this.accounts = accounts;
    }

    @Override
    public void action() {
        Card card = Card.createCard();
        accounts.createAccount(card.getNumber(), card.pin);
        System.out.println(String.format(FORMAT_MESSAGE, card.getNumber(), card.getPin()));
    }
}
