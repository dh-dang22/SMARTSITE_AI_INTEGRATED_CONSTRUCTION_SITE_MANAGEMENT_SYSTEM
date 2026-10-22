package Validatable;

import java.util.Scanner;

public class CheckHostName {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkHostName() {
        while (true) {
            System.out.print("Enter Host Name: ");
            String input = sc.nextLine();
            String hostName = input.trim().replaceAll("\\s+", " ");

            if (hostName.length() < 2 || hostName.length() > 50) {
                System.out.println("[Error]: Host Name length must be between 2 and 50 characters! Please try again.");
                continue;
            }
            if (!hostName.matches("^[a-zA-Z\\s]+$")) {
                System.out.println("[Error]: Host Name must contain letters only (no numbers or special characters)! Please try again.");
                continue;
            }
            return hostName;
        }
    }
}