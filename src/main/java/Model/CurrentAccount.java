package Model;

import java.util.List;

public class CurrentAccount extends Account {

    public CurrentAccount(String accountHolderName, String accountNumber, String phoneNumber, String ghanaCardNumber, String accountType, double balance, List<Transaction> transactionHistory, String pin) {
        super(accountHolderName, accountNumber, phoneNumber, ghanaCardNumber, accountType, balance, transactionHistory, pin);
    }

    @Override
    public void addTransaction(Transaction t) {
        if (getTransactionHistory() != null) {
            getTransactionHistory().add(t);
        }
    }

}