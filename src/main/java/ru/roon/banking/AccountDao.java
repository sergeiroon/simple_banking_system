package ru.roon.banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao {

    private static final String URL_TEMPLATE = "jdbc:sqlite:%s";
    private static SQLiteDataSource dataSource = null;

    public AccountDao(String name) {
        if (dataSource == null) {
            SQLiteDataSource newDataSource = new SQLiteDataSource();
            newDataSource.setUrl(String.format(URL_TEMPLATE, name));
            dataSource = newDataSource;
        }
    }

    public void createDateBase() {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                    "id INTEGER PRIMARY KEY," +
                    "number TEXT NOT NULL," +
                    "pin TEXT NOT NULL," +
                    "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAccount(long numberCard, int pin) {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(String.format("Insert into card (number, pin) " +
                    "VALUES (%d, %d)", numberCard, pin));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findAccount(long numberCard, int pin) {
        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet rs = statement.executeQuery(String.format("SELECT * FROM card Where number = '%d' and pin = '%d'", numberCard, pin))) {
                    String number = null;
                    String pinCode = null;
                    long balance = 0;
                    while (rs.next()) {
                        // Retrieve column values
                        number = rs.getString("number");
                        pinCode = rs.getString("pin");
                        balance = rs.getLong("balance");
                    }
                    if (number != null && pinCode != null) {
                        Card card = new Card(Long.valueOf(number), Integer.valueOf(pinCode));
                        Account account = new Account(card);
                        account.setBalance(balance);
                        return account;
                    } else {
                        return null;
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account findAccount(long numberCard) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement("SELECT * FROM card Where number = ?")) {
                statement.setString(1, String.valueOf(numberCard));
                try (ResultSet rs = statement.executeQuery()) {
                    String number = null;
                    String pinCode = null;
                    while (rs.next()) {
                        number = rs.getString("number");
                        pinCode = rs.getString("pin");
                    }
                    if (number != null && pinCode != null) {
                        Card card = new Card(Long.valueOf(number), Integer.valueOf(pinCode));
                        Account account = new Account(card);
                        return account;
                    } else {
                        return null;
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBalance(long sum, long number) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement("UPDATE card SET balance = balance + ? WHERE number = ?")) {

                statement.setLong(1, sum);
                statement.setLong(2, number);

                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transfer(long number, long numberCardTo, long sum) {
        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement statement = con.prepareStatement("UPDATE card SET balance = balance - ? WHERE number = ?");
                 PreparedStatement statement1 = con.prepareStatement("UPDATE card SET balance = balance + ? WHERE number = ?")) {

                statement.setLong(1, sum);
                statement.setLong(2, number);
                statement.executeUpdate();
                statement1.setLong(1, sum);
                statement1.setLong(2, numberCardTo);
                statement1.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long number) {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement("DELETE FROM card WHERE number = ?")) {
                statement.setLong(1, number);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
