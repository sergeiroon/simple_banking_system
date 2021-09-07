package ru.roon.banking;

import java.util.Random;

public class Card {
    private static final long BIN = 4000000000000000L;
    private static final Random RANDOM = new Random(0);
    private static final int ACCOUNT_IDENTIFIER_BOUND = 999999999;
    private static final int PIN_UPPER = 9999;
    private static final int PIN_LOWER = 1000;

    long number;
    int pin;

    public Card(long number, int pin) {
        this.number = number;
        this.pin = pin;
    }

    public static Card createCard() {
        long number = generateNumber();
        int pin = generatePin();
        return new Card(number, pin);
    }

    private static int generatePin() {
        return RANDOM.nextInt(PIN_UPPER - PIN_LOWER + 1) + PIN_LOWER;
    }

    private static long generateNumber() {
        int accountIdentifier = RANDOM.nextInt(ACCOUNT_IDENTIFIER_BOUND - 1) + 1;
        return BIN + ((long)accountIdentifier) * 10L + retrieveCheckSum(BIN + ((long)accountIdentifier) * 10L);
    }

    private static int retrieveCheckSum(long accountIdentifier) {

        String [] numbers = String.valueOf(accountIdentifier).split("");
        int number = 0;
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (((i + 1) % 2) != 0) {
                number =  Integer.valueOf(numbers[i]) * 2;
            } else {
                number = Integer.valueOf(numbers[i]);
            }
            if (number > 9) {
                number -= 9;
            }
            sum += number;
        }
        if (sum % 10 == 0) {
            return 0;
        }
        return 10 - (sum % 10);
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    private static long pow(long a, int b) {

        if (b == 0) {
            return 0;
        }

        for (int i = 1; i < b; i++) {
            a *= a;
        }

        return a;
    }

    public static boolean checkLuhnAlg(long number) {

        long checkSum = number % 10;
        number = number / 10;
        long sum = 0;
        int count = 0;
        while (number > 0) {
            ++count;
            long a = number % 10;
            number = number / 10;
            if ((count % 2) != 0) {
                a =  a * 2;
            }
            if (a > 9) {
                a -= 9;
            }
            sum = sum + a;
        }
        if ((sum + checkSum) % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
