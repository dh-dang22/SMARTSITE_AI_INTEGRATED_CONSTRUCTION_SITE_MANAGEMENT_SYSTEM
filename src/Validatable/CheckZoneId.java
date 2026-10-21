package Validatable;

import java.util.Scanner;

public class CheckZoneId {

    private static final Scanner sc = new Scanner(System.in);

    // Kiểm tra Zone ID (dạng Zxxx hoặc RZxxx)
    public static String checkZoneId() {
        while (true) {
            System.out.print("Enter Zone ID [Zxxx or RZxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Zone ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^(Z|RZ)\\d{3}$")) {
                System.out.println("[Error]: Zone ID must follow format [Zxxx] or [RZxxx] (e.g., Z001, RZ001)! Please try again.");
                continue;
            }
            return id;
        }
    }
}