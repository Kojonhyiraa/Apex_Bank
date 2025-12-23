package Service;

import Model.User;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {

    private final Map<String, User> users = new HashMap<>();


    public void register() throws Exception {
        Scanner input = new Scanner(System.in);

        String logo = """
                 █████╗ ██████╗ ███████╗██╗  ██╗    ██████╗  █████╗ ███╗   ██╗██╗  ██╗
                ██╔══██╗██╔══██╗██╔════╝╚██╗██╔╝    ██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝
                ███████║██████╔╝█████╗   ╚███╔╝     ██████╔╝███████║██╔██╗ ██║█████╔╝
                ██╔══██║██╔═══╝ ██╔══╝   ██╔██╗     ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ 
                ██║  ██║██║     ███████╗██╔╝ ██╗    ██████╔╝██║  ██║██║ ╚████║██║  ██╗
                ╚═╝  ╚═╝╚═╝     ╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝
                """;

        System.out.println(logo);
        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                       NEW USER REGISTRATION PORTAL                      │");
        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");

        System.out.print("  [STEP 1/3] Create Username: ");
        String username = input.nextLine();

        System.out.print("  [STEP 2/3] Set Password: ");
        String password = input.nextLine();

        String pin = "";
        boolean pinSet = false;

        while (!pinSet) {
            System.out.println("  [STEP 3/3] Security PIN for Login");
            System.out.print("  > Enter a 4-digit PIN: ");
            pin = input.nextLine();

            if (pin.length() == 4 && pin.matches("\\d+")) { // Added a check to ensure only numbers
                System.out.print("  > Confirm 4-digit PIN: ");
                String confirmed_pin = input.nextLine();

                if (pin.equals(confirmed_pin)) {
                    System.out.println("\n  Processing...");
                    // Simple visual loading bar
                    System.out.println("  [██████████████████████████████] 100%");

                    System.out.println("\n  ✔ SUCCESS: Account created successfully for " + username + "!");
                    System.out.println("  Press ENTER to go to the Main Menu...");
                    input.nextLine();

                    pinSet = true;
                } else {
                    System.out.println("  ✘ ERROR: PINs do not match. Please restart step 3.");
                }
            } else {
                System.out.println("  ✘ ERROR: Invalid PIN. It must be exactly 4 digits (0-9).");
            }
        }

        // Logic for the Model to keep values
        User newUser = new User(username, password, pin);
        users.put(username, newUser);
        users.put(newUser.getPin(), newUser);

        startmenu(username);
    }


    public void startmenu(String username) throws Exception {
        Scanner input = new Scanner(System.in);
        BankService bankService = new BankService();

        // The logo is printed once at the start or inside the loop depending on preference
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
            System.out.println(" [5] Print Mini Statement");
            System.out.println(" [6] Exit");
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
                    bankService.printStatement();
                    break;
                case 6:
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