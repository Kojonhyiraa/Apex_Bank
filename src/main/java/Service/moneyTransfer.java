package Service;

import Model.Account;
import Model.Transaction;

import java.util.Map;
import java.util.Scanner;

import static Utils.validateInput.isValidPhoneNumber;

public class moneyTransfer implements authenticatable {
    private final Map<String, Account> accounts;
    Scanner input;

    //Adding a constructor so it sync with the BankService Impl
    public moneyTransfer(Map<String, Account> accounts) {
        this.accounts = accounts;
        this.input = new Scanner(System.in);
    }

    //Transfer money Section
    public void transferMoneyMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("===========================================================================");
        System.out.println("||                 APEX BANK MONEY TRANSFER PORTAL                       ||");
        System.out.println("===========================================================================");
        System.out.println(" [1] Bank to Momo");
        System.out.println(" [2] Momo to Bank");
        System.out.println(" [3] Internal Account Transfer");
        System.out.println(" [4] Transfer to another bank account");
        System.out.println(" [5] Cancel");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(" Enter your choice:");
        int transferType = input.nextInt();
        input.nextLine();

        switch (transferType) {
            case 1:
                bankToMomo();
                break;

            case 2:
                momoToBank();
                break;

            case 3:
                internalAccountTransfer();
                break;

            case 4:
                transferToAnotherBank();
                break;

            case 5:
                return;

            default:
                System.out.println(" Invalid option.");
        }
    }

    public void bankToMomo() {
        System.out.println(" [Step 1/3] Enter account to transfer from:");
        String accountNumber = input.nextLine();

        System.out.println(" [Step 2/3] Enter pin");
        String pin = input.nextLine();

        if (!verifyPin(accountNumber, pin)) {
            return;
        }

        //Check if the account number exists even before the user can enter any amount
        Account account = accounts.get(accountNumber);

        //Validate Phone number
        String phoneNumber;
        while (true) {
            System.out.print(" Phone Number: ");
            phoneNumber = input.nextLine();

            if (isValidPhoneNumber(phoneNumber)) break;

            System.out.println(" ✘ ERROR: Phone number must be exactly 10 digits.");
        }

        // Store the current balance into a variable
        double currentBalance = account.getBalance();

        //Take amount from User
        System.out.println(" Your current balance is: GHS " + currentBalance);
        System.out.println(" ------------------------------------------- ");
        System.out.println(" Enter amount to transfer: ");

        double amount = input.nextDouble();
        input.nextLine(); // Clear buffer

        if (amount <= 0) {
            System.out.println("✘ ERROR: Invalid amount. Must be greater than 0.");
            return;
        }

        double balanceAfterTransfer = currentBalance - amount;

        if (balanceAfterTransfer < 0) {
            System.out.println("✘ ERROR: Insufficient funds.");
            System.out.println("Current balance: GHS " + currentBalance);
            return;
        }

        account.setBalance(balanceAfterTransfer);

        Transaction transaction = new Transaction(amount, java.time.LocalDateTime.now(), Transaction.TransactionType.TRANSFER_OUT, balanceAfterTransfer);
        account.addTransaction(transaction);

        System.out.println("\n  Processing...");
        System.out.println("  [██████████████████████████████] 100%");
        System.out.println("Payment successfully made to " + phoneNumber + " from " + account.getAccountHolderName() + " Your current " + "balance is: " + balanceAfterTransfer);

    }

    public void momoToBank() {
        System.out.println(" Feature will be implemented soon");
    }

    public void internalAccountTransfer() {

        System.out.println(" Enter your account to transfer from");
        String accountNumber = input.nextLine();

        System.out.println(" Enter the recipient's bank account");
        String recipientAccountNumber = input.nextLine();

        //Check validity of recipient Account number
        if (!accounts.containsKey(recipientAccountNumber)) {
            System.out.println("Account not found");
            return;
        }

        if (accountNumber.equals(recipientAccountNumber)) {
            System.out.println("You cannot transfer money to and from the same account");
            return;
        }
        Account recipientAccount = accounts.get(recipientAccountNumber);
        Account account = accounts.get(accountNumber);

        // Store the current balance into a variable
        double currentBalance = account.getBalance();

        //Take amount from User
        System.out.println(" Your current balance is: GHS " + currentBalance);
        System.out.println(" ------------------------------------------- ");
        System.out.println(" Enter amount to transfer: ");

        double amount = input.nextDouble();
        input.nextLine(); // Clear buffer

        if (amount <= 0) {
            System.out.println("✘ ERROR: Invalid amount. Must be greater than 0.");
            return;
        }
        double balanceAfterTransfer = currentBalance - amount;

        if (balanceAfterTransfer < 0) {
            System.out.println("✘ ERROR: Insufficient funds.");
            System.out.println("Current balance: GHS " + currentBalance);
            return;
        }

        //Check the recipient's name to see if that's what who the user wants to send to
        System.out.println("Are you sure you want to send money to " + recipientAccount.getAccountHolderName() + "? (y/n)");
        String confirmation = input.nextLine();

        if (!confirmation.equalsIgnoreCase("y")) {
            System.out.println("Check account number and try again");
            return;
        }

        //Last input is to enter pin
        System.out.println(" Enter pin");
        String pin = input.nextLine();

        if (!verifyPin(accountNumber, pin)) {
            return;
        }

        //Set Balances
        account.setBalance(balanceAfterTransfer);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);

        //Log the transaction for the debit
        Transaction transaction = new Transaction(amount, java.time.LocalDateTime.now(), Transaction.TransactionType.TRANSFER_OUT, balanceAfterTransfer);
        account.addTransaction(transaction);

        //Log the transaction for the credit
        Transaction cr_transaction = new Transaction(amount, java.time.LocalDateTime.now(), Transaction.TransactionType.TRANSFER_IN, recipientAccount.getBalance());
        recipientAccount.addTransaction(cr_transaction);


        //Display to the user
        System.out.println("\n  Processing...");
        System.out.println("  [██████████████████████████████] 100%");
        System.out.println("Payment successful. Your current balance is: " + balanceAfterTransfer);

    }

    public void transferToAnotherBank() {
        System.out.println("Feature will be implemented soon");
    }


    @Override
    public boolean verifyPin(String accountNumber, String pin) {
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found");
            return false;
        }

        Account account = accounts.get(accountNumber);

        if (account.getPin().equals(pin)) {
            System.out.println("✔ Verification successful for account " + accountNumber);
            return true;
        }

        System.out.println("Invalid PIN");
        return false;
    }
}
