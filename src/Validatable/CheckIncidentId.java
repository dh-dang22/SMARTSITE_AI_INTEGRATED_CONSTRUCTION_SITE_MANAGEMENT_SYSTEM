package Validatable;

import java.util.Scanner;

public class CheckIncidentId {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkIncidentId() {
        while (true) {
            System.out.print("Enter Incident ID [INxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Incident ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^IN\\d{3}$")) {
                System.out.println("[Error]: Incident ID must follow format [INxxx] (e.g., IN001)! Please try again.");
                continue;
            }
            return id;
        }
    }
}