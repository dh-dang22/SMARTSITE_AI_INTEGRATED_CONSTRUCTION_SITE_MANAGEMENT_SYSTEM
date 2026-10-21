package PersonValid;

import java.util.Scanner;

public class CheckPersonId {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkPersonId() {
        while (true) {
            System.out.print("Enter Person ID [Pxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Person ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^P\\d{3}$")) {
                System.out.println("[Error]: Person ID must follow format [Pxxx] (e.g., P001)! Please try again.");
                continue;
            }
            return id;
        }
    }
}