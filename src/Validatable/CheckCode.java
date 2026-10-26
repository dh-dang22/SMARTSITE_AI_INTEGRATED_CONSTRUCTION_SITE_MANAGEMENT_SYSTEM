package Validatable;

import Person.PersonManager;
import java.util.Scanner;

public class CheckCode {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkCode(PersonManager pm, String role) {
        while (true) {
            String code = "";
            String number = "";

            switch (role.toUpperCase().trim()) {
                case "WORKER": {
                    String prefix = "WK";
                    number = checkNumber(prefix);
                    code = prefix.toUpperCase().trim() + number;
                    break;
                }

                case "VISITOR": {
                    String prefix = "VT";
                    number = checkNumber(prefix);
                    code = prefix.toUpperCase().trim() + number;
                    break;
                }

                case "CONTRACTOR": {
                    String prefix = "CT";
                    number = checkNumber(prefix);
                    code = prefix.toUpperCase().trim() + number;
                    break;
                }

                case "SAFETYOFFICER": {
                    String prefix = "SO";
                    number = checkNumber(prefix);
                    code = prefix.toUpperCase().trim() + number;
                    break;

                }

                case "SITEMANAGER": {
                    String prefix = "SM";
                    number = checkNumber(prefix);
                    code = prefix.toUpperCase().trim() + number;
                    break;

                }

                default: {
                    System.out.println("[Error]: Invalid Code Prefix! Must be one of: WK, VS, CT, SO, SM. Try again.\n");
                }
            }

            if (pm != null && pm.findPersonByCode(code) != null) { // Chua xac nhan co kiem tra duoc trung code hay khong
                System.out.println("[Error]:  [" + code + "] already exists! Please try again.\n");
                continue;
            }
            return code;
        }
    }

    public static String checkNumber(String prefix) {
        while (true) {
            System.out.print("Enter Code : [" + prefix + "xxxx]: " + prefix);
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
            System.out.print("Enter Restricted Zone Pin Code [ABCxxx]: ");
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
