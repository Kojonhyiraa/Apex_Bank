
package Model;

import java.util.List;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountHolderName, String accountNumber, String phoneNumber, String ghanaCardNumber, String accountType, double balance, List<Transaction> transactionHistory, String pin) {
        super(accountHolderName, accountNumber, phoneNumber, ghanaCardNumber, accountType, balance, transactionHistory, pin);
    }

    @Override
    public void calculateInterest() {
    }

    @Override
    public void addTransaction(Transaction t) {
        if (getTransactionHistory() != null) {
            getTransactionHistory().add(t);
        }
    }
}