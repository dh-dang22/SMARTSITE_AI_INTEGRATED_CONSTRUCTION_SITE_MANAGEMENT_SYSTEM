/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validatable;

import java.util.Scanner;


public class CheckReportId {
    private static final Scanner sc = new Scanner(System.in);
    public static String checkReportId() {
        while (true) {
            System.out.print("Enter Report ID [RPxxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: ReportID cannot be empty! Please try again.");
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
