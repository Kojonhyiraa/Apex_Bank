package Model;

import java.util.List;

public abstract class Account {
    private String accountHolderName;
    private String accountNumber;
    private String phoneNumber;
    private String ghanaCardNumber;
    private String accountType;
    private double balance;
    private String pin;
    private List<Transaction> transactionHistory;

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

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String name) {
        this.accountHolderName = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGhanaCardNumber() {
        return ghanaCardNumber;
    }

    public void setGhanaCardNumber(String ghanaCardNumber) {
        this.ghanaCardNumber = ghanaCardNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public void setPin(String pin) {
        this.pin = pin;
    }

    public abstract void calculateInterest();

    public abstract void addTransaction(Transaction t);
}