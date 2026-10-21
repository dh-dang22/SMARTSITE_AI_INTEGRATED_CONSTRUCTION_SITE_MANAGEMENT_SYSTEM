package Validatable;

import java.util.Scanner;

public class CheckDepartment {
    private static final Scanner sc = new Scanner(System.in);

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
}