package Validatable;

import java.util.Scanner;

public class CheckLicense {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkLicense() {
        while (true) {
            System.out.print("Enter Inspection License: ");
            String input = sc.nextLine();
            
            String inspectionLicense = input.trim().replaceAll("\\s+", " ");

            if (inspectionLicense.length() < 1 || inspectionLicense.length() > 100) {
                System.out.println("[Error]: Inspection License length must be between 1 and 100 characters! Please try again.");
                continue;
            }

            if (!inspectionLicense.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
                System.out.println("[Error]: Inspection License must not contain special characters! Please try again.");
                continue;
            }

            return inspectionLicense;
        }
    }
}