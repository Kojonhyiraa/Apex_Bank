package Model;

import java.util.List;

public abstract class Account {
    private final String accountHolderName;
    private final String accountNumber;
    private final String phoneNumber;
    private final String ghanaCardNumber;
    private final String accountType;
    private double balance;
    private final String pin;
    private final List<Transaction> transactionHistory;

    public Account(String accountHolderName, String accountNumber, String phoneNumber, String ghanaCardNumber, String accountType, double balance, List<Transaction> transactionHistory, String pin) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.ghanaCardNumber = ghanaCardNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.transactionHistory = transactionHistory;
        this.pin = pin;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public abstract void addTransaction(Transaction t);

}