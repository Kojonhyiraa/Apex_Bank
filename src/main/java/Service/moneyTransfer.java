package Service;

import Model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Utils.validateInput.isValidPhoneNumber;

public class moneyTransfer implements authenticatable{
    private final Map<String, Account> accounts = new HashMap<>();
    Scanner input = new Scanner(System.in);

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
        System.out.println("Enter your choice:");
        int transferType = input.nextInt();

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
                System.out.println("Invalid option.");
        }
    }


    public void bankToMomo(){

        System.out.println(" Enter account number");
        String accountNumber = input.nextLine();

        System.out.println(" Enter pin");
        String pin = input.nextLine();

        if (!verifyPin(accountNumber, pin)) {
            return;
        }

        Account account = accounts.get(accountNumber);

        //Validate Phone number
        String phoneNumber;
        while (true){
            System.out.print(" Phone Number: ");
            phoneNumber = input.nextLine();

            if (isValidPhoneNumber(phoneNumber)) break;

            System.out.println(" ✘ ERROR: Phone number must be exactly 10 digits.");
        }

        double currentBalance = account.getBalance();

        //Take amount from User
        System.out.println("Your current balance is: GHS " + currentBalance );
        System.out.println("Enter amount to transfer:");

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
        }

        account.setBalance(balanceAfterTransfer);


        //Deduct from the balance and credit the number



    }


    public void momoToBank(){

    }

    public void internalAccountTransfer(){

    }

    public void transferToAnotherBank(){

    }

    @Override
    public boolean verifyPin(String pin, String accountNumber) {
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
