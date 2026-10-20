package Validatable;

import java.util.Scanner;

/**
 *
 * @author GIA HUY
 */
public class CheckInput {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkId() {
        while (true) {
            System.out.print("Enter ID: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("-> [Error]: ID cannot be empty! Please try again.\n");
                continue;
            }
            // Định dạng cho phép: Z123, RZ123, P123, ...
            if (!id.matches("^(Z|RZ|P)\\d{3}$")) {
                System.out.println("-> [Error]: ID must follow format [Zxxx], [RZxxx], or [Pxxx]! Please try again.\n");
                continue;
            }
            return id;
        }
    }

    // kiem tra theo pattern
    public static String readPattern(Scanner sc, String noticeMes, String pattern, String errorMes) {
        System.out.println(noticeMes);
        while (true) {
            if (!sc.hasNextLine()) {
                return "";
            }
            String input = sc.nextLine().trim();
            if (!input.isEmpty() && input.matches(pattern)) {
                return input;
            }
            System.out.println(errorMes);
        }
    }

    //kiem tra theo range
    public static int readRange(Scanner sc, String noticeMes, int min, int max, String errorMes) {
        System.out.println(noticeMes);
        while (true) {
            if (!sc.hasNextLine()) {
                return -1;
            }
            try {
                int input = Integer.parseInt(sc.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + " is not a integer! ");
                System.out.println(errorMes);
            }
        }
    }

    public static boolean checkObject(Validatable obj) {
        if (obj == null) {
            System.out.println("Error: Object is null!");
            return false;
        }
        if (!obj.isValid()) {
            System.out.println("Error: Object contains invalid data!");
            return false;
        }
        return true;
    }
    private static final Scanner INPUT = new Scanner(System.in);

    public static Scanner getScanner() {
        return INPUT;
    }

}
