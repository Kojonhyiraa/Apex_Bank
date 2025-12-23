package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private double amount;
    private LocalDateTime dateTime;
    private TransactionType transactionType;
    private double balanceAfter;

    public Transaction(double amount, LocalDateTime dateTime, TransactionType transactionType, double balanceAfter) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.balanceAfter = balanceAfter;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-12s | GHS %,10.2f | %s | Balance: GHS %,10.2f", transactionType, amount, dateTime.format(formatter), balanceAfter);
    }

    // Enum for Transaction Types
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT, INTEREST, FEE
    }
}