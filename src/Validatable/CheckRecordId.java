package Validatable;

import java.util.Scanner;

public class CheckRecordId {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkRecordId() {
        while (true) {
            System.out.print("Enter Record ID [ARxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Record ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^AR\\d{3}$")) {
                System.out.println("[Error]: Record ID must follow format [ARxxx] (e.g., AR001)! Please try again.");
                continue;
            }
            return id;
        }
    }
}