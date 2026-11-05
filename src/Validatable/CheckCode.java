package Validatable;

import Person.Person;
import Person.PersonManager;
import java.util.Scanner;

public class CheckCode {

    private static final Scanner sc = new Scanner(System.in);

   public static String checkCode(
        PersonManager pm,
        String role,
        String currentPersonId) {

    Scanner sc = InputScanner.getScanner();

    while (true) {
        System.out.print("Enter Person's code: ");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("Code cannot be empty!");
            continue;
        }

        Person existing = pm.findPersonByCode(code);

        // Code đã thuộc về người khác thì yêu cầu nhập lại.
        // Code đang được chính người này sử dụng thì chấp nhận.
        if (existing != null
                && !existing.getId().equalsIgnoreCase(currentPersonId)) {
            System.out.println("Code already exists! Please enter another code.");
            continue;
        }

        // Đặt kiểm tra định dạng code theo role ở đây nếu cần.
        return code;
    }
}

    public static String checkNumber(String prefix) {
        while (true) {
            System.out.print("    ➔ Enter Code : [" + prefix + "xxxx]: " + prefix);
            String number = InputScanner.nextLine();
            if (number == null) {
                return null;
            }
            if (!number.matches("^\\d{4}$")) {
                System.out.println("[Error]: Number must be exactly 4 digits (0-9) with no letters/special characters! Try again.\n");
                continue;
            }

            return number;
        }
    }
     public static String checkPinCode() {
        while (true) {
            System.out.print("    ➔ Enter Restricted Zone Pin Code [ABCxxx]: ");
            String pinCode = sc.nextLine().trim();

            if (pinCode.isEmpty()) {
                System.out.println("-> [Error]: Pin Code cannot be empty! Please try again.\n");
                continue;
            }
            if (!pinCode.matches("^[A-Z]{3}\\d{3}$")) {
                System.out.println("-> [Error]: Pin Code must follow format [ABCxxx] (e.g., PWR123)! Please try again.\n");
                continue;
            }
            return pinCode;
        }
    }
}
