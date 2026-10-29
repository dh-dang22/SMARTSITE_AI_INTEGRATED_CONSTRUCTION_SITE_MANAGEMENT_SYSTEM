/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validatable;

import java.util.Scanner;

// include : checkCompanyName, checkContractType, checkDepartment, checkLicense, checkRole, checkStatus, checkVisitPurpose
public class CheckPersonAttributes {
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
            if (!companyName.matches("^[a-zA-Z0-9\\s]+$")) {
                System.out.println("[Error]: Company Name must not contain special characters! Please try again.");
                continue;
            }
            return companyName;
        }
    }
    
    public static String checkContractType() {
        while (true) {
            System.out.print("Enter Contract Type (FULLTIME/PARTTIME/SEASONAL): ");
            String contractType = sc.nextLine().trim().toUpperCase();

            if (contractType.equals("FULLTIME") || contractType.equals("PARTTIME") ) {
                return contractType;
            }
            System.out.println("[Error]: Contract Type must be FULLTIME, PARTTIME! Please try again.");
        }
    }
    public static String checkDepartment() {
        while (true) {
            System.out.print("Enter Department: ");
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
      public static String checkStatus() {
        while (true) {
            System.out.print("Enter Status (ACTIVE/INACTIVE): ");
            String input = sc.nextLine().trim().toUpperCase();

            if (input.equals("ACTIVE") || input.equals("INACTIVE")) {
                return input;
            }
            System.out.println("[Error]: Status must be ACTIVE or INACTIVE! Please try again.");
        }
    }
      public static String checkVisitPurpose() {
        while (true) {
            System.out.print("Enter Visit Purpose: ");
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
