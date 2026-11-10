/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validatable;

import java.util.Scanner;
import ui_Dang_dang_sua.Draft;

// include : checkCompanyName, checkContractType, checkDepartment, checkLicense, checkRole, checkStatus, checkVisitPurpose
public class CheckPersonAttributes {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkCompanyName() {
        while (true) {
            System.out.print("    ➔ Enter Company Name: ");
            String input = sc.nextLine();
            String companyName = input.trim().replaceAll("\\s+", " ");

            if (companyName.length() < 1 || companyName.length() > 100) {
                System.out.println("[Error]: Company Name length must be between 1 and 100 characters! Please try again.");
                continue;
            }
            if (!companyName.matches("^[a-zA-Z0-9\\s]+$")) {
                System.out.println("[Error]: Company Name must not contain special characters! Please try again.");
                continue;
            }
            return companyName;
        }
    }
    
    public static String checkContractType() {
    while (true) {
        System.out.println("    ➔ Select Contract Type:");
        System.out.println("       [1] FULLTIME");
        System.out.println("       [2] PARTTIME");
        System.out.println("       [3] SEASONAL");
        System.out.print("    Choose [1-3]: ");

        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                return "FULLTIME";
            case "2":
                return "PARTTIME";
            case "3":
                return "SEASONAL";
            default:
                System.out.println(Draft.center(Draft.RED + "[Error]: Invalid choice! Please select between 1 and 3." + Draft.BAS));
                break;
        }
    }
}
    public static String checkDepartment() {
        while (true) {
            System.out.print("    ➔ Enter Department: ");
            String input = sc.nextLine();
            String dept = input.trim().replaceAll("\\s+", " ");

            if (dept.length() < 1 || dept.length() > 100) {
                System.out.println("[Error]: Department length must be between 1 and 100 characters! Please try again.");
                continue;
            }
            if (!dept.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
                System.out.println("[Error]: Department must not contain special characters! Please try again.");
                continue;
            }
            return dept;
        }
    }
     public static String checkLicense() {
        while (true) {
            System.out.print("    ➔ Enter Inspection License: ");
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
     public static String checkRole() {
    while (true) {
        System.out.println("    ➔ Select Role:");
        System.out.println("       [1] WORKER");
        System.out.println("       [2] VISITOR");
        System.out.println("       [3] SITE_MANAGER");
        System.out.println("       [4] SAFETY_OFFICER");
        System.out.print("    Choose [1-4]: ");

        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                return "WORKER";
            case "2":
                return "VISITOR";
            case "3":
                return "SITE_MANAGER";
            case "4":
                return "SAFETY_OFFICER";
            default:
                System.out.println(Draft.center(Draft.RED + "[Error]: Invalid choice! Please select between 1 and 4." + Draft.BAS));
                break;
        }
    }
}

      public static String checkStatus() {
    while (true) {
        System.out.println("    ➔ Select Status:");
        System.out.println("       [1] ACTIVE");
        System.out.println("       [2] INACTIVE");
        System.out.print("    Choose [1-2]: ");

        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                return "ACTIVE";
            case "2":
                return "INACTIVE";
            default:
                System.out.println(Draft.center(Draft.RED + "[Error]: Invalid choice! Please select 1 or 2." + Draft.BAS));
                break;
        }
    }
}
      public static String checkVisitPurpose() {
        while (true) {
            System.out.print("    ➔ Enter Visit Purpose: ");
            String input = sc.nextLine();
            String visitPurpose = input.trim().replaceAll("\\s+", " ");

            if (visitPurpose.length() < 1 || visitPurpose.length() > 100) {
                System.out.println("[Error]: Visit Purpose length must be between 1 and 100 characters! Please try again.");
                continue;
            }
            if (!visitPurpose.matches("^[a-zA-Z0-9\\s]+$")) {
                System.out.println("[Error]: Visit Purpose must not contain special characters! Please try again.");
                continue;
            }
            return visitPurpose;
        }
    }
}
