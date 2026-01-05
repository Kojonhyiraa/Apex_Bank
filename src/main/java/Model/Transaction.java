package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private double amount;
    private LocalDateTime dateTime;
    private TransactionType transactionType;
    private double balanceAfter;

    // Enum for Transaction Types
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT, INTEREST
    }

    public Transaction(double amount, LocalDateTime dateTime, TransactionType transactionType, double balanceAfter) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.balanceAfter = balanceAfter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-12s | GHS %,10.2f | %s | Balance: GHS %,10.2f", transactionType, amount, dateTime.format(formatter), balanceAfter);
    }

    public String generateTransactionId(){



    }

}