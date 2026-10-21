package Validatable;

import java.util.Scanner;

public class CheckLicense {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkLicense() {
        while (true) {
            System.out.print("Enter Inspection License: ");
            String input = sc.nextLine();
            
            // 1. Tự động chuyển nhiều dấu cách liên tiếp thành 1 dấu cách và xóa khoảng trắng 2 đầu
            String inspectionLicense = input.trim().replaceAll("\\s+", " ");

            // 2. Kiểm tra độ dài (từ 1 đến 100 ký tự)
            if (inspectionLicense.length() < 1 || inspectionLicense.length() > 100) {
                System.out.println("[Error]: Inspection License length must be between 1 and 100 characters! Please try again.");
                continue;
            }

            // 3. Chuỗi ký tự tự do: Chỉ cho phép chữ cái, chữ số và khoảng trắng (không chứa ký tự đặc biệt)
            if (!inspectionLicense.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
                System.out.println("[Error]: Inspection License must not contain special characters! Please try again.");
                continue;
            }

            return inspectionLicense;
        }
    }
}