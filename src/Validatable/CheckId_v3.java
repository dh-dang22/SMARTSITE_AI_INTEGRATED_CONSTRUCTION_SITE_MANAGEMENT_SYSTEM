package Validatable;

import java.util.Scanner;
// include : checkPersonId, checkZoneId, checkIncidentId, checkAssgineeId, checkRecordId, checkReportId
public class CheckId_v3 {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkPersonId() {
        while (true) {
            System.out.print("Enter Person ID [PRxxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Person ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^P\\d{4}$")) {
                System.out.println("[Error]: Person ID must follow format [PRxxxx] (e.g., PR0001)! Please try again.");
                continue;
            }
            return id;
        }
    }
    public static String checkZoneId() {
        while (true) {
            System.out.print("Enter Zone ID [ZNxxxx ]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Zone ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^(ZN)\\d{4}$")) {
                System.out.println("[Error]: Zone ID must follow format [ZNxxxx]  (e.g., ZN0001)! Please try again.");
                continue;
            }
            return id;
        }
    }
    public static String checkIncidentId() {
        while (true) {
            System.out.print("Enter Incident ID [INxxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Incident ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^IN\\d{4}$")) {
                System.out.println("[Error]: Incident ID must follow format [INxxxx] (e.g., IN0001)! Please try again.");
                continue;
            }
            return id;
        }
    }
    public static String checkRecordId() {
        while (true) {
            System.out.print("Enter Record ID [RCxxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Record ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^RC\\d{4}$")) {
                System.out.println("[Error]: Record ID must follow format [RCxxx] (e.g., RC0001)! Please try again.");
                continue;
            }
            return id;
        }
    }
     public static String checkAssigneeId() {
        while (true) {
            System.out.print("Enter Assignee ID [PRxxxx, WKxxxx, SOxxxx, SMxxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Assignee ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^(PR|WK|SO|SM)\\d{4}$")) {
                System.out.println("[Error]: Assignee ID must follow format [PRxxxx], [WKxxxx], [SOxxxx], [SMxxxx] (e.g., PR001, WK001,SO001, SM001)! Please try again.");
                continue;
            }
            return id;
        }
    }
    public static String checkReportId() {
        while (true) {
            System.out.print("Enter Report ID [RPxxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Report ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^RP\\d{4}$")) {
                System.out.println("[Error]: Record ID must follow format [RPxxxx] (e.g., RP0001)! Please try again.");
                continue;
            }
            return id;
        }
}
}