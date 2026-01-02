package Service;

import Model.*;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class BankService implements authenticatable {
    // Users should be able to create Savings or Current accounts
    private Map<String, Account> accounts = new HashMap<>();

    Scanner input = new Scanner(System.in);
//    InsufficientFundsException: Thrown during a debit if account rules
//            (Min-Balance/Overdraft) are violated.


    //Check User Balance
    public void checkBalance() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter account number: ");
        String accountNumber = input.nextLine();

        System.out.println("Enter your pin");
        String pin = input.nextLine();

       if(!verifyPin(accountNumber, pin)){
           return;
        }

        System.out.println("Your balance is:" + this.accounts.get(accountNumber).getBalance());

    }

    // Deposit funds
    public void deposit() {
        try{
            Scanner input = new Scanner(System.in);

            System.out.println("Enter account number: ");
            String accountNumber = input.nextLine();

            //Check if the account number exists even before the user can enter any amount
           if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException(accountNumber+" Not found in our records");
           }

            System.out.println("Enter amount to deposit:");
            double amount = input.nextDouble();
            input.nextLine();

            if (amount > 0) {
                Account account = accounts.get(accountNumber);
                account.setBalance(account.getBalance() + amount);

                double newBalance = account.getBalance();
                System.out.println("Deposit successful");
                System.out.println("======================================================================");
                System.out.println("Your new balance is:" + newBalance);
                System.out.println("======================================================================");

                //Log the transaction
                Transaction transaction = new Transaction(
                    amount,
                    java.time.LocalDateTime.now(),
                    Transaction.TransactionType.DEPOSIT,
                    newBalance
            );

            account.addTransaction(transaction);
    }
            else{
                System.out.println("Invalid amount.Try again");
            }

            }

        catch (AccountNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    //Withdraw funds per-account specifications
    public void withdrawal() {

        /*
        * SavingsAccount: Must enforce a Minimum Balance of $50. Any debit that violates this must be blocked.
           CurrentAccount: Must implement an Overdraft Limit (e.g., balance can go down to -$500).*/

        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter account number: ");
            String accountNumb = input.nextLine();

            System.out.println("Enter your pin");
            String pin = input.nextLine();

            if (accounts.containsKey(accountNumb) && accounts.get(accountNumb).getPin().equals(pin)) {
                Account account = accounts.get(accountNumb);

                System.out.println("Enter amount to withdraw:");
                double amount = input.nextDouble();

                if (amount <= 0) {
                    System.out.println("✘ ERROR: Invalid amount. Must be greater than 0.");
                    return;
                }

                // Check account type and apply appropriate rules
                if (account instanceof SavingsAccount) {
                    // SavingsAccount: Enforce minimum balance of $50
                    double currentBalance = account.getBalance();
                    double balanceAfterWithdrawal = currentBalance - amount;

                    if (amount > currentBalance) {
                        System.out.println("✘ ERROR: Insufficient funds.");
                        System.out.println("Current balance: GHS " + currentBalance);
                        System.out.println("Attempted withdrawal: GHS " + amount);
                        return;
                    }

                    if (balanceAfterWithdrawal < 50) {
                        System.out.println("✘ ERROR: Withdrawal denied. Savings account must maintain a minimum balance of GHS 50.00");
                        System.out.println("Current balance: GHS " + currentBalance);
                        System.out.println("Attempted withdrawal: GHS " + amount);
                        System.out.println("Balance after withdrawal would be: GHS " + balanceAfterWithdrawal);
                        System.out.println("Maximum you can withdraw: GHS " + (currentBalance - 50));
                        return;
                    }

                    account.setBalance(balanceAfterWithdrawal);

                    // Log Transaction for Savings Account
                    Transaction transaction = new Transaction(
                            amount,
                            java.time.LocalDateTime.now(),
                            Transaction.TransactionType.WITHDRAWAL,
                            balanceAfterWithdrawal
                    );
                    account.addTransaction(transaction);


                } else if (account instanceof CurrentAccount) {
                    // CurrentAccount: Allow overdraft up to -$500
                    double currentBalance = account.getBalance();
                    double balanceAfterWithdrawal = currentBalance - amount;

                    if (balanceAfterWithdrawal < -500) {
                        System.out.println("✘ ERROR: Withdrawal denied. Overdraft limit exceeded.");
                        System.out.println("Current balance: GHS " + currentBalance);
                        System.out.println("Attempted withdrawal: GHS " + amount);
                        System.out.println("Balance after withdrawal would be: GHS " + balanceAfterWithdrawal);
                        System.out.println("Maximum overdraft allowed: GHS -500.00");
                        System.out.println("Maximum you can withdraw: GHS " + (currentBalance + 500));
                        return;
                    }
                    // Proceed with withdrawal
                    account.setBalance(balanceAfterWithdrawal);

                    //Logging the transaction
                    Transaction transaction = new Transaction(
                            amount,
                            java.time.LocalDateTime.now(),
                            Transaction.TransactionType.WITHDRAWAL,
                            balanceAfterWithdrawal
                    );
                    account.addTransaction(transaction);


                    // Proceed with withdrawal
                    account.setBalance(balanceAfterWithdrawal);

                } else {
                    System.out.println("✘ ERROR: Unknown account type.");
                    return;
                }

                System.out.println("\n  Processing...");
                // Simple visual loading bar
                System.out.println("  [██████████████████████████████] 100%");
                System.out.println("Withdrawal successful");
                System.out.println("Your new balance is: GHS " + account.getBalance());

            } else {
                System.out.println("✘ ERROR: Invalid account number or PIN.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Generate Current Account Number
    public static String generateCAccountNumber() {
        String prefix = "CUR-";
        int min = 100000;
        int max = 999999;
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));
        return prefix + randomNum;
    }

    //Generate Savings Account Number
    public static String generateSAccountNumber() {
        String prefix = "SAV-";
        int min = 100000;
        int max = 999999;
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));
        return prefix + randomNum;
    }

    //Create Savings Account
    public void createSavingsAccount() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\n");
        System.out.println("  ╔═══════════════════════════════════════════════════════════╗");
        System.out.println("  ║            NEW SAVINGS ACCOUNT APPLICATION                ║");
        System.out.println("  ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("  Please provide your official documentation details below:");
        System.out.println();

        System.out.print("  [1] Full Name: ");
        String name = input.nextLine();

        System.out.print("  [2] Phone Number: ");
        String phoneNumber = input.nextLine();

        System.out.print("  [3] Ghana Card ID (Input without dashes..eg:GHAXXXXXXXXXX): ");
        String ghanaCardNumber = input.nextLine();

        System.out.print("  [4] Enter 4-digit pincode to be used for all transactions: ");
        String pin = input.nextLine();
        if (pin.length() == 4 && pin.matches("\\d+")) {
            // Logic remains the same
            String accountNumber = generateSAccountNumber();
            double balance = 0.0;

            SavingsAccount savingsAccount = new SavingsAccount(name, accountNumber, phoneNumber, ghanaCardNumber, "Savings", balance, new ArrayList<>(), pin);

            // --- BEAUTIFIED VERIFICATION CARD ---
            System.out.println("\n  ✔ APPLICATION PROCESSED SUCCESSFULLY");
            System.out.println("  ┌──────────────────────────────────────────────────────────┐");
            System.out.println("  │                OFFICIAL ACCOUNT SUMMARY                  │");
            System.out.println("  ├──────────────────────────────────────────────────────────┤");
            System.out.printf("  │  HOLDER NAME    : %-38s │\n", name.toUpperCase());
            System.out.printf("  │  ACCOUNT NUMBER : %-38s │\n", accountNumber);
            System.out.printf("  │  PHONE NUMBER   : %-38s │\n", phoneNumber);
            System.out.printf("  │  GHANA CARD ID  : %-38s │\n", formatCard(ghanaCardNumber));
            System.out.printf("  │  ACCOUNT TYPE   : %-38s │\n", "SAVINGS");
            System.out.printf("  │  INITIAL BAL    : GHS %-34.2f │\n", balance);
            System.out.println("  └──────────────────────────────────────────────────────────┘");

            System.out.println("\n  Processing...");
            // Simple visual loading bar
            System.out.println("  [██████████████████████████████] 100%");
            System.out.println("    [!] Savings account must have a minimum balance of GHS 50.00 to be activated...Press ENTER to continue...");
            input.nextLine();

            // Attempt initial deposit with the savingsAccount object
            boolean depositSuccessful = initialDeposit(savingsAccount);

            if (depositSuccessful) {
                // Only store account after successful initial deposit
                accounts.put(name, savingsAccount);
                accounts.put(accountNumber, savingsAccount);
                accounts.put(phoneNumber, savingsAccount);
                accounts.put(ghanaCardNumber, savingsAccount);
                accounts.put(pin, savingsAccount);

                System.out.println("\n✔ Savings account successfully created and activated!");
                System.out.println("  Account Number: " + accountNumber);
                System.out.println("  Initial Balance: GHS " + savingsAccount.getBalance());
            } else {
                System.out.println("\n✘ Account creation cancelled. Initial deposit requirement not met.");
            }
        } else {
            System.out.println("Invalid pincode. Please try again.");
        }

    }

    //CREATE A CURRENT ACCOUNT
    public void createCurrentAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\n");
        System.out.println("  ╔═══════════════════════════════════════════════════════════╗");
        System.out.println("  ║            NEW CURRENT ACCOUNT APPLICATION                ║");
        System.out.println("  ╚═══════════════════════════════════════════════════════════╝");
        System.out.println("  Please provide your official documentation details below:");
        System.out.println();

       // Used While loop so that it goes back to the input not return to main menu
        // Validate Name
        String name;
        while (true) {
            System.out.print("  [1] Full Name: ");
            name = input.nextLine();
            if (isValidName(name)) break;

            System.out.println("  ✘ ERROR: Name must be at least 4 words and must be letters only.");
        }

        System.out.print("  [2] Phone Number: ");
        String phoneNumber = input.nextLine();

        System.out.print("  [3] Ghana Card ID (formatCard(ghanaCardNumber): ");
        String ghanaCardNumber = input.nextLine();

        System.out.print("  [4] Enter 4-digit pincode to be used for all transactions: ");
        String pin = input.nextLine();


        if (pin.length() == 4 && pin.matches("\\d+")) {
            // Logic remains the same
            String accountNumber = generateCAccountNumber();
            double balance = 0.0;

            CurrentAccount currentAccount = new CurrentAccount(name, accountNumber, phoneNumber, ghanaCardNumber, "Current", balance, new ArrayList<>(), pin);

            // Storing values
            accounts.put(name, currentAccount);
            accounts.put(accountNumber, currentAccount);
            accounts.put(phoneNumber, currentAccount);
            accounts.put(ghanaCardNumber, currentAccount);
            accounts.put(pin, currentAccount);


            // --- BEAUTIFIED VERIFICATION CARD ---
            System.out.println("\n  ✔ APPLICATION PROCESSED SUCCESSFULLY");
            System.out.println("  ┌──────────────────────────────────────────────────────────┐");
            System.out.println("  │                OFFICIAL ACCOUNT SUMMARY                  │");
            System.out.println("  ├──────────────────────────────────────────────────────────┤");
            System.out.printf("  │  HOLDER NAME    : %-38s │\n", name.toUpperCase());
            System.out.printf("  │  ACCOUNT NUMBER : %-38s │\n", accountNumber);
            System.out.printf("  │  PHONE NUMBER   : %-38s │\n", phoneNumber);
            System.out.printf("  │  GHANA CARD ID  : %-38s │\n", formatCard(ghanaCardNumber));
            System.out.printf("  │  ACCOUNT TYPE   : %-38s │\n", "CURRENT");
            System.out.printf("  │  INITIAL BAL    : GHS %-34.2f │\n", balance);
            System.out.println("  └──────────────────────────────────────────────────────────┘");

            System.out.println("\n  Processing...");
            // Simple visual loading bar
            System.out.println("  [██████████████████████████████] 100%");
            System.out.println("  Press ENTER to return to the menu...");
            input.nextLine();
        } else {
            System.out.println("Invalid pincode. Please try again.");
        }
    }

    //Method that lets users choose which type of account to create
    public void createAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("===========================================================================");
        System.out.println("||              WELCOME to APEX BANK Account Creation Portal:             ||");
        System.out.println("===========================================================================");
        System.out.println(" [1] Create Savings Account");
        System.out.println(" [2] Create Current Account");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Enter your choice:");
        int accountType = input.nextInt();

        switch (accountType) {
            case 1:
                createSavingsAccount();
                break;

            case 2:
                createCurrentAccount();
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    //Ghana Card input Format
    public String formatCard(String ghanaCard) {
        StringBuilder var4 = new StringBuilder(ghanaCard);
        var4.insert(3, "-");
        var4.insert(ghanaCard.length(), "-");
        String formatted = var4.toString();
        return formatted;
    }

    //Initial deposit for Savings Account
    public boolean initialDeposit(SavingsAccount savingsAccount) {
        Scanner input = new Scanner(System.in);
        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
            try {
                System.out.println("\n═══════════════════════════════════════════════════════════");
                System.out.println("              INITIAL DEPOSIT REQUIRED");
                System.out.println("═══════════════════════════════════════════════════════════");
                System.out.println("  Minimum deposit amount: GHS 50.00");
                System.out.println("  Attempts remaining: " + (maxAttempts - attempts));
                System.out.println("───────────────────────────────────────────────────────────");

                System.out.print("  Enter your 4-digit pincode: ");
                String pin = input.nextLine();

                if (!pin.equals(savingsAccount.getPin())) {
                    attempts++;
                    System.out.println("✘ ERROR: Invalid PIN.");
                    if (attempts >= maxAttempts) {
                        System.out.println("✘ Maximum attempts reached. Account creation cancelled.");
                        return false;
                    }
                    continue;
                }

                System.out.print("  Enter amount to deposit (minimum GHS 50.00): ");
                double amount = input.nextDouble();
                input.nextLine(); // consume newline

                if (amount < 50.0) {
                    attempts++;
                    System.out.println("✘ ERROR: Amount must be at least GHS 50.00");
                    System.out.println("  You entered: GHS " + amount);
                    if (attempts >= maxAttempts) {
                        System.out.println("✘ Maximum attempts reached. Account creation cancelled.");
                        return false;
                    }
                    continue;
                }

                // Set the balance on the savings account
                savingsAccount.setBalance(amount);

                // Create and add transaction for initial deposit
                Transaction transaction = new Transaction(
                        amount,
                        java.time.LocalDateTime.now(),
                        Transaction.TransactionType.DEPOSIT,
                        amount
                );
                savingsAccount.addTransaction(transaction);

                System.out.println(" ");
                System.out.println("\n  Processing...");
                System.out.println("  [██████████████████████████████] 100%");
                System.out.println("✔ Initial deposit successful!");
                System.out.println("  Amount deposited: GHS " + amount);
                return true;

            } catch (Exception e) {
                attempts++;
                System.out.println("✘ ERROR: Invalid input. " + e.getMessage());
                input.nextLine();
                if (attempts >= maxAttempts) {
                    System.out.println("✘ Maximum attempts reached. Account creation cancelled.");
                    return false;
                }
            }
        }

        return false;
    }


    //Print Statement
    public void printStatement() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║                   MINI-STATEMENT REQUEST                  ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");

            System.out.print("  Enter account number: ");
            String accountNumber = input.nextLine();

            System.out.print("  Enter your PIN: ");
            String pin = input.nextLine();

            if (!accounts.containsKey(accountNumber)) {
                System.out.println("✘ ERROR: Account not found.");
                return;
            }

            Account account = accounts.get(accountNumber);

            if (!account.getPin().equals(pin)) {
                System.out.println("✘ ERROR: Invalid PIN.");
                return;
            }

            List<Transaction> transactions = account.getTransactionHistory();

            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║                    MINI-STATEMENT                         ║");
            System.out.println("╠═══════════════════════════════════════════════════════════╣");
            System.out.printf("║  Account: %-47s ║\n", accountNumber);
            System.out.printf("║  Holder:  %-47s ║\n", account.getAccountHolderName());
            System.out.printf("║  Type:    %-47s ║\n", account.getAccountType());
            System.out.printf("║  Current Balance: GHS %-35.2f ║\n", account.getBalance());
            System.out.println("╠═══════════════════════════════════════════════════════════╣");

            if (transactions == null || transactions.isEmpty()) {
                System.out.println("║  No transactions found.                                   ║");
            } else {

                // Use Streams & Lambdas to get the 5 most recent transactions
                List<Transaction> lastFiveTransactions = transactions.stream()
                        .sorted((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime())) // Sort by date descending (most recent first)
                        .limit(5) // Get only the first 5
                        .collect(java.util.stream.Collectors.toList()); // Collect to list

                System.out.println("║  LAST 5 TRANSACTIONS:                                     ║");
                System.out.println("╠═══════════════════════════════════════════════════════════╣");

                // Display transactions using forEach with lambda
                lastFiveTransactions.forEach(transaction ->
                        System.out.printf("║  %-57s ║\n", transaction.toString())
                );
            }

            System.out.println("╚═══════════════════════════════════════════════════════════╝");

        } catch (Exception e) {
            System.out.println("✘ ERROR: " + e.getMessage());
        }
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
