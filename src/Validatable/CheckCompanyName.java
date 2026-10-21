package Validatable;

import java.util.Scanner;

public class CheckCompanyName {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkCompanyName() {
        while (true) {
            System.out.print("Enter Company Name: ");
            String input = sc.nextLine();
            String companyName = input.trim().replaceAll("\\s+", " ");

            if (companyName.length() < 1 || companyName.length() > 100) {
                System.out.println("[Error]: Company Name length must be between 1 and 100 characters! Please try again.");
                continue;
            }
            if (!companyName.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
                System.out.println("[Error]: Company Name must not contain special characters! Please try again.");
                continue;
            }
            return companyName;
        }
    }
}