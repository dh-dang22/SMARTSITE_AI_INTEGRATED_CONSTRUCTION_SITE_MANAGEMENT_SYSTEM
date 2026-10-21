package Validatable;

import java.util.Scanner;

public class CheckCode {
    private static final Scanner sc = new Scanner(System.in);
    
    public static String checkWorkerCode() {
        while (true) {
            System.out.print("Enter Code [WKxxx]: ");
            String code = sc.nextLine().trim();

            if (code.isEmpty()) {
                System.out.println("[Error]: Code cannot be empty! Please try again.");
                continue;
            }
            if (!code.matches("^W\\d{3}$")) {
                System.out.println("[Error]: Code must follow format [Wxxx] (e.g., W001)! Please try again.");
                continue;
            }
            return code;
        }
    }
    public static String checkSafetyOfficerCode() {
        while (true) {
            System.out.print("Enter Code [Oxxx]: ");
            String code = sc.nextLine().trim();

            if (code.isEmpty()) {
                System.out.println("[Error]: Code cannot be empty! Please try again.");
                continue;
            }
            if (!code.matches("^O\\d{3}$")) {
                System.out.println("[Error]: Code must follow format [Oxxx] (e.g., O001)! Please try again.");
                continue;
            }
            return code;
        }
    }
    public static String checkSiteManagerCode() {
        while (true) {
            System.out.print("Enter Code [Mxxx]: ");
            String code = sc.nextLine().trim();

            if (code.isEmpty()) {
                System.out.println("[Error]: Code cannot be empty! Please try again.");
                continue;
            }
            if (!code.matches("^M\\d{3}$")) {
                System.out.println("[Error]: Code must follow format [Mxxx] (e.g., M001)! Please try again.");
                continue;
            }
            return code;
        }
    }
    
}