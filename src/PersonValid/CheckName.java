package PersonValid;

import java.util.Scanner;

public class CheckName {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkName() {
        while (true) {
            System.out.print("Enter Full Name: ");
            String input = sc.nextLine();
            String cleanName = input.trim().replaceAll("\\s+", " ");

            if (cleanName.length() < 2 || cleanName.length() > 50) {
                System.out.println("[Error]: Full Name length must be between 2 and 50 characters! Please try again.");
                continue;
            }
            if (!cleanName.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
                System.out.println("[Error]: Full Name must contain letters only (no numbers or special characters)! Please try again.");
                continue;
            }
            return cleanName;
        }
    }
}