/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validatable;

import java.util.Scanner;

// include : checkDetails, checkIncidentStatus

public class CheckDetails {
    private static final Scanner sc = new Scanner(System.in);
    public static String checkDetails() {
        while (true) {
            System.out.print("Enter Details: ");
            String input = sc.nextLine();

            String details = input.trim().replaceAll("\\s+", " ");

            if (details.length() < 1 || details.length() > 100) {
                System.out.println("[Error]: Details length must be between 1 and 100 characters! Please try again.\n");
                continue;
            }

            if (!details.matches("^[a-zA-Z0-9\\s]+$")) {
                System.out.println("[Error]: Details must not contain special characters! Please try again.\n");
                continue;
            }

            return details;
        }
    }
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
