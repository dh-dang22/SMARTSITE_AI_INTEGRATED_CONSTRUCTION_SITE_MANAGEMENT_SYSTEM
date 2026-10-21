package Validatable;

import java.util.Scanner;

public class CheckRole {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkRole() {
        while (true) {
            System.out.print("Enter Role (WORKER, VISITOR, SITEMANAGER, SAFETYOFFICER): ");
            String role = sc.nextLine().trim().toUpperCase();

            if (role.equals("WORKER") || role.equals("VISITOR") 
                    || role.equals("SITEMANAGER") || role.equals("SAFETYOFFICER")) {
                return role;
            }
            System.out.println("[Error]: Role must be one of: WORKER, VISITOR, SITEMANAGER, SAFETYOFFICER! Please try again.");
        }
    }
}