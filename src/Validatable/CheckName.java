package Validatable;

import java.util.Scanner;
import ui_Dang_dang_sua.Draft;
// include : checkPersonName, checkZoneName, checkHostName

public class CheckName {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkPersonName() {
        while (true) {
            System.out.print("    ➔ Enter Person Name: ");
            String input = sc.nextLine();
            String cleanName = input.trim().replaceAll("\\s+", " ");

            if (cleanName.length() < 2 || cleanName.length() > 50) {
                System.out.println("[Error]: Full Name length must be between 2 and 50 characters! Please try again.");
                continue;
            }
            if (!cleanName.matches("(?U)^\\p{L}+(?:[ '\u2019-]\\p{L}+)*$")) {
                System.out.println("[Error]: Name may contain letters, spaces, apostrophes, or hyphens only.");
                continue;
            }

            boolean hasOverlongPart = false;
            for (String part : cleanName.split("[ '\u2019-]+")) {
                if (part.length() > 20) {
                    hasOverlongPart = true;
                    break;
                }
            }
            if (hasOverlongPart) {
                System.out.println("[Error]: Each part of the name must be 20 characters or fewer.");
                continue;
            }

            return toTitleCaseName(cleanName);
        }
    }
    private static String toTitleCaseName(String name) {
        StringBuilder result = new StringBuilder(name.length());
        boolean capitalizeNext = true;

        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            if (Character.isLetter(character)) {
                result.append(capitalizeNext
                        ? Character.toUpperCase(character)
                        : Character.toLowerCase(character));
                capitalizeNext = false;
            } else {
                result.append(character);
                capitalizeNext = character == ' ' || character == '-'
                        || character == '\'' || character == '\u2019';
            }
        }
        return result.toString();
    }

    public static String checkZoneName() {
        while (true) {
            System.out.print("    ➔ Enter Zone Name: ");
            String input = sc.nextLine();

            // 1. Tự động chuyển nhiều dấu cách liên tiếp thành 1 dấu cách và xóa khoảng trắng 2 đầu
            String zoneName = input.trim().replaceAll("\\s+", " ");

            // 2. Kiểm tra độ dài (từ 2 đến 50)
            if (zoneName.length() < 2 || zoneName.length() > 50) {
                System.out.println(Draft.center(Draft.RED+"[Error]: Zone Name length must be between 2 and 50 characters! Please try again."+Draft.BAS));
                continue;
            }

            // 3. Regex chỉ cho phép chữ cái (Anh) và khoảng trắng (không chứa số, không chứa ký tự đặc biệt) 
            if (!zoneName.matches("^(?!(?:.*\\d){4})[a-zA-Z0-9\\s]+$")) {// cho phep nhap toi da 3 so 
                System.out.println(Draft.center(Draft.RED+"[Error]: Zone Name must contain letters only (no numbers or special characters)! Please try again."+Draft.BAS));
                continue;
            }

            return zoneName;
        }
    }

    public static String checkHostName() {
        while (true) {
            System.out.print("    ➔ Enter Host Name: ");
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
            System.out.print("    ➔ Enter Company Name: ");
            String input = sc.nextLine();
            String companyName = input.trim().replaceAll("\\s+", " ");

            if (companyName.length() < 1 || companyName.length() > 100) {
                System.out.println("[Error]: Company Name length must be between 1 and 100 characters! Please try again.");
                continue;
            }
            if (!companyName.matches("^[a-zA-Z0-9\\s]+$")) {
                System.out.println("[Error]: Company Name must not contain special characters! Please try again.");
                continue;
            }
            return companyName;
        }
    }
}
