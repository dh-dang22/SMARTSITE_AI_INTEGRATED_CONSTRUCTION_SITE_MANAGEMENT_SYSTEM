package Validatable;

import java.util.Scanner;

public class CheckPinCode {

    private static final Scanner sc = InputScanner.getScanner();

    public static String checkPinCode() {
        while (true) {
            System.out.print("Enter Restricted Zone Pin Code [ABCxxx]: ");
            String pinCode = sc.nextLine().trim();

            if (pinCode.isEmpty()) {
                System.out.println("-> [Error]: Pin Code cannot be empty! Please try again.\n");
                continue;
            }
            if (!pinCode.matches("^[A-Z]{3}\\d{3}$")) {
                System.out.println("-> [Error]: Pin Code must follow format [ABCxxx] (e.g., PWR123)! Please try again.\n");
                continue;
            }
            return pinCode;
        }
    }
}