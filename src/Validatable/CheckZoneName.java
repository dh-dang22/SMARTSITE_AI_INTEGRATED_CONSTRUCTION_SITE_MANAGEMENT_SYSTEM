package Validatable;

import java.util.Scanner;

public class CheckZoneName {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkZoneName() {
        while (true) {
            System.out.print("Enter Zone Name: ");
            String input = sc.nextLine();

            // 1. Tự động chuyển nhiều dấu cách liên tiếp thành 1 dấu cách và xóa khoảng trắng 2 đầu
            String zoneName = input.trim().replaceAll("\\s+", " ");

            // 2. Kiểm tra độ dài (từ 2 đến 50)
            if (zoneName.length() < 2 || zoneName.length() > 50) {
                System.out.println("[Error]: Zone Name length must be between 2 and 50 characters! Please try again.");
                continue;
            }

            // 3. Regex chỉ cho phép chữ cái (tiếng Việt & Anh) và khoảng trắng (không chứa số, không chứa ký tự đặc biệt) nhờ AI về regex tiếng việt
            if (!zoneName.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
                System.out.println("[Error]: Zone Name must contain letters only (no numbers or special characters)! Please try again.");
                continue;
            }

            return zoneName;
        }
    }
}