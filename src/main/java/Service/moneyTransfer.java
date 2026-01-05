package Service;

import java.util.Scanner;

public class moneyTransfer {


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
        int accountType = input.nextInt();

        switch (accountType) {
            case 1:
                break;

            case 2:
                break;

            default:
                System.out.println("Invalid option.");
        }
    }


    public void bankToMomo(){

    }

    public void transferMoney(){

    }
}
