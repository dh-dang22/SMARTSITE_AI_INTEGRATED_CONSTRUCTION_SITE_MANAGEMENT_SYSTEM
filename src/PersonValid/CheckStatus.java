package PersonValid;

import java.util.Scanner;

public class CheckStatus {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkStatus() {
        while (true) {
            System.out.print("Enter Status (ACTIVE/INACTIVE): ");
            String input = sc.nextLine().trim().toUpperCase();

            if (input.equals("ACTIVE") || input.equals("INACTIVE")) {
                return input;
            }
            System.out.println("[Error]: Status must be ACTIVE or INACTIVE! Please try again.");
        }
    }
}