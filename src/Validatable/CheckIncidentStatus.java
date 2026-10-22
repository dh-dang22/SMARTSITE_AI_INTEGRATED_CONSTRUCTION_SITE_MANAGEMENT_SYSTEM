package Validatable;

import java.util.Scanner;

public class CheckIncidentStatus {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkIncidentStatus() {
        while (true) {
            System.out.print("Enter Incident Status: ");
            String input = sc.nextLine();

            String status = input.trim().replaceAll("\\s+", " ");

            if (status.length() < 1 || status.length() > 100) {
                System.out.println("[Error]: Incident Status length must be between 1 and 100 characters! Please try again.");
                continue;
            }

            if (!status.matches("^[a-zA-Z0-9\\s_\\-]+$")) {
                System.out.println("[Error]: Incident Status must not contain special characters! Please try again.");
                continue;
            }

            return status;
        }
    }
}