package PersonValid;

import java.util.Scanner;

public class CheckVisitPurpose {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkVisitPurpose() {
        while (true) {
            System.out.print("Enter Visit Purpose: ");
            String input = sc.nextLine();
            String visitPurpose = input.trim().replaceAll("\\s+", " ");

            if (visitPurpose.length() < 1 || visitPurpose.length() > 100) {
                System.out.println("[Error]: Visit Purpose length must be between 1 and 100 characters! Please try again.");
                continue;
            }
            if (!visitPurpose.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
                System.out.println("[Error]: Visit Purpose must not contain special characters! Please try again.");
                continue;
            }
            return visitPurpose;
        }
    }
}