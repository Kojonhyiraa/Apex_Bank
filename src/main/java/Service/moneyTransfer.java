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

    }


    public void momoToBank(){

    }

    public void internalAccountTransfer(){

    }

    public void transferToAnotherBank(){

    }
}
