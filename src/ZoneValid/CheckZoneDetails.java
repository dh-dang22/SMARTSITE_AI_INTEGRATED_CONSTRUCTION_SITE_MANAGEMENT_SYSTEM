package ZoneValid;

import java.util.Scanner;

public class CheckZoneDetails {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkZoneDetails() {
        while (true) {
            System.out.print("Enter Zone Details: ");
            String input = sc.nextLine();

            // 1. Tự động chuyển nhiều dấu cách liên tiếp thành 1 dấu cách
            String zoneDetails = input.trim().replaceAll("\\s+", " ");

            // 2. Kiểm tra độ dài (từ 1 đến 100)
            if (zoneDetails.length() < 1 || zoneDetails.length() > 100) {
                System.out.println("[Error]: Zone Details length must be between 1 and 100 characters! Please try again.\n");
                continue;
            }

            // 3. Regex chỉ cho phép chữ cái, số và khoảng trắng (không chứa ký tự đặc biệt)
            if (!zoneDetails.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
                System.out.println("[Error]: Zone Details must not contain special characters! Please try again.\n");
                continue;
            }

            return zoneDetails;
        }
    }
}