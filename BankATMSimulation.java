import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankATMSimulation {
    

    private static Map<Integer, Integer> billInventory = new HashMap<>();


    private static void initilizeBillInventory() {
        billInventory.put(500, 50);
        billInventory.put(200, 20);
        billInventory.put(100, 30);
    }
    

    public static void main(String[] args) {

        initilizeBillInventory();

        Scanner scanner = new Scanner(System.in);
        
        while(true) {

            System.out.println("Welcome to the bank ATM");
            System.out.println("1. Wtihdraw (Retiro)");
            System.out.println("2. Exit (Salida)");
            System.out.println("Ingrese su opción:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    WtihdrawMoney(scanner);
                    break;
                case 2:
                    System.out.println("Thank you for using the ATM  (Hasta luego)");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }


        }

    }


    private static void WtihdrawMoney(Scanner scanner) {
        System.out.println("Enter the amount to withdraw: $");

        int amountToWithdraw = scanner.nextInt();

        Map<Integer, Integer> withdraw = new HashMap<>();

        Integer[] billDenominations = billInventory.keySet().toArray(new Integer[0]);
        Arrays.sort(billDenominations, Collections.reverseOrder());

        for (int billDenomination : billDenominations) {
            
            int billCount = billInventory.get(billDenomination);

            int billsNeeded = amountToWithdraw / billDenomination;

            int billsToDispense = Math.min(billsNeeded, billCount);

            if (billsToDispense > 0) {
                withdraw.put(billDenomination, billsToDispense);
                amountToWithdraw -= (billsToDispense * billDenomination);
                billInventory.put(billDenomination, billCount - billsToDispense);
            }

            if(amountToWithdraw == 0) {
                System.out.println("Dispensing the following bills:");

                for (Map.Entry<Integer, Integer> withDrawalEntry : withdraw.entrySet()) {
                    System.out.println("$" + withDrawalEntry.getKey() +  " bills: " + withDrawalEntry.getValue());
                }


            }
        }

    }


}
