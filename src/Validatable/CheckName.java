package Validatable;

import java.util.Scanner;
// include : checkName, checkZoneName, checkHostName

public class CheckName {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkName() {
        while (true) {
            System.out.print("Enter Full Name: ");
            String input = sc.nextLine();
            String cleanName = input.trim().replaceAll("\\s+", " ");

            if (cleanName.length() < 2 || cleanName.length() > 50) {
                System.out.println("[Error]: Full Name length must be between 2 and 50 characters! Please try again.");
                continue;
            }
            if (!cleanName.matches("^[a-zA-ZÀ-ỹ\\s]+")) {
                System.out.println("[Error]: Full Name must contain letters and one number ! Please try again.");
                continue;
            }
            return cleanName;
        }
    }

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
            if (!zoneName.matches("^(?!(?:.*\\d){4})[a-zA-Z0-9\\s]+$")) {// cho phep nhap toi da 3 so 
                System.out.println("[Error]: Zone Name must contain letters only (no numbers or special characters)! Please try again.");
                continue;
            }

            return zoneName;
        }
    }

    public static String checkHostName() {
        while (true) {
            System.out.print("Enter Host Name: ");
            String input = sc.nextLine();
            String hostName = input.trim().replaceAll("\\s+", " ");

            if (hostName.length() < 2 || hostName.length() > 50) {
                System.out.println("[Error]: Host Name length must be between 2 and 50 characters! Please try again.");
                continue;
            }
            if (!hostName.matches("^[a-zA-Z\\s]+$")) {
                System.out.println("[Error]: Host Name must contain letters only (no numbers or special characters)! Please try again.");
                continue;
            }
            return hostName;
        }
    }

    public static String checkCompanyName() {
        while (true) {
            System.out.print("Enter Company Name: ");
            String input = sc.nextLine();
            String companyName = input.trim().replaceAll("\\s+", " ");

            if (companyName.length() < 1 || companyName.length() > 100) {
                System.out.println("[Error]: Company Name length must be between 1 and 100 characters! Please try again.");
                continue;
            }
            if (!companyName.matches("^[a-zA-Z0-9À-ỹ\\s]+$")) {
                System.out.println("[Error]: Company Name must not contain special characters! Please try again.");
                continue;
            }
            return companyName;
        }
    }
}
