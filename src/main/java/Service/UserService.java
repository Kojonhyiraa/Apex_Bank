package Service;

import Model.User;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static Utils.validateInput.*;

public class UserService {

    private final Map<String, User> users = new HashMap<>();

    //Initial User Registration
    public void register()  {
        Scanner input = new Scanner(System.in);

        String logo = """
                 █████╗ ██████╗ ███████╗██╗  ██╗    ██████╗  █████╗ ███╗   ██╗██╗  ██╗
                ██╔══██╗██╔══██╗██╔════╝╚██╗██╔╝    ██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝
                ███████║██████╔╝█████╗   ╚███╔╝     ██████╔╝███████║██╔██╗ ██║█████╔╝
                ██╔══██║██╔═══╝ ██╔══╝   ██╔██╗     ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ 
                ██║  ██║██║     ███████╗██╔╝ ██╗    ██████╔╝██║  ██║██║ ╚████║██║  ██╗
                ╚═╝  ╚═╝╚═╝     ╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝
                """ ;

        System.out.println(logo);
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                       NEW USER REGISTRATION PORTAL                      │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");

        String username;
        while (true) {
            System.out.print(" [STEP 1/3] Enter Username: ");
            username = input.nextLine();
            if (isValidName(username)) break;

            System.out.println("  ✘ ERROR: Username must be at least 4 words and must be letters only.");
        }

        String password;
        while (true){
            System.out.print(" [STEP 2/3] Set Password: ");
            password = input.nextLine();
            if(isValidPassword(password))break;

            System.out.println("Password must be at least 8 characters long and include at least one uppercase letter, " +
                    "one lowercase letter, one digit, and one special character.");
        }

        //Validate Pin
        String pin;
        while(true){
            System.out.println("    [STEP 3/3] Security PIN for Login");
            System.out.print("        > Enter a 4-digit PIN: ");
            pin = input.nextLine();

            if(isValidPin(pin)){
            System.out.println("\n  Processing...");
            // Simple visual loading bar
            System.out.println("  [██████████████████████████████] 100%");

            System.out.println("\n  ✔ SUCCESS: Account created successfully for " + username + "!");
            System.out.println("  Press ENTER to go to the Main Menu...");
            input.nextLine();

            // Logic for the Model to keep values
            User newUser = new User(username, password, pin);
            users.put(username, newUser);
            users.put(newUser.getPin(), newUser);

            startmenu(username);
            break;

            }
            else{
                System.out.println("Invalid pin, try again");

            };

        }


    }

    //Start menu Page with the initial switch statement
    public void startmenu(String username){
        moneyTransfer moneyTransfer = new moneyTransfer();
        Scanner input = new Scanner(System.in);
        BankService bankService = new BankService();

        String logo = """
                 █████╗ ██████╗ ███████╗██╗  ██╗    ██████╗  █████╗ ███╗   ██╗██╗  ██╗
                ██╔══██╗██╔══██╗██╔════╝╚██╗██╔╝    ██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝
                ███████║██████╔╝█████╗   ╚███╔╝     ██████╔╝███████║██╔██╗ ██║█████╔╝
                ██╔══██║██╔═══╝ ██╔══╝   ██╔██╗     ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗
                ██║  ██║██║     ███████╗██╔╝ ██╗    ██████╔╝██║  ██║██║ ╚████║██║  ██╗
                ╚═╝  ╚═╝╚═╝     ╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝
                """;

        while (true) {
            System.out.println(logo);
            System.out.println("===========================================================================");
            System.out.println(" WELCOME, " + username.toUpperCase() + " | Please select an option:");
            System.out.println("===========================================================================");
            System.out.println(" [1] Open Account");
            System.out.println(" [2] Check Balance");
            System.out.println(" [3] Deposit");
            System.out.println(" [4] Withdraw");
            System.out.println(" [5] Transfer Money");
            System.out.println(" [6] Print Mini Statement");
            System.out.println(" [7] Exit");
            System.out.println("---------------------------------------------------------------------------");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    bankService.createAccount();
                    break;
                case 2:
                    bankService.checkBalance();
                    break;
                case 3:
                    bankService.deposit();
                    break;
                case 4:
                    bankService.withdrawal();
                    break;
                case 5:
                    moneyTransfer.transferMoneyMenu();
                case 6:
                    bankService.printStatement();
                    break;
                case 7:
                    System.out.println("Thank you for choosing Apex Bank. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            // Clear space for the next loop
            System.out.println("\n\n");
        }
    }

}