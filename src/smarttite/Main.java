package smarttite;

import java.util.Scanner;
/**
 *
 * @author Minh_Khang
 */
public class Main {

    public static boolean canAccess(Person user, String action) {
        if (user == null || action == null) return false;
        String role = user.getRole();

        if ("WORKER".equalsIgnoreCase(role) || "VISITOR".equalsIgnoreCase(role)) {
            return action.equalsIgnoreCase("CHECK_IN")
                || action.equalsIgnoreCase("CHECK_OUT")
                || action.equalsIgnoreCase("VIEW_HISTORY");
        }

        if ("SAFETY_OFFICER".equalsIgnoreCase(role)) {
            return action.equalsIgnoreCase("LOG_INCIDENT")
                || action.equalsIgnoreCase("UPDATE_INCIDENT");
        }

        if ("SITE_MANAGER".equalsIgnoreCase(role)) {
            return action.equalsIgnoreCase("VIEW_REPORT")
                || action.equalsIgnoreCase("MANAGE_ZONE")
                || action.equalsIgnoreCase("MANAGE_PERSON");
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PersonManager pm = new PersonManager();

        // Nạp dữ liệu mẫu vào PersonManager
        pm.addPerson(new Worker("P001", "Tran Minh Khang", "W001", "WORKER", "ACTIVE", "BuildCo", "Full-time"));
        pm.addPerson(new Visitor("P002", "Bach Gia Huy", "V001", "VISITOR", "ACTIVE", "Site Visit", "Manager A"));
        pm.addPerson(new Person("P003", "Safety Officer", "S001", "SAFETY_OFFICER", "ACTIVE"));
        pm.addPerson(new Person("P004", "Site Manager", "M001", "SITE_MANAGER", "ACTIVE"));

        // Hiển thị danh sách tất cả người dùng
        pm.displayAll();

        System.out.print("\nEnter code to login (e.g., W001, V001, S001, M001): ");
        String code = sc.nextLine().trim();

        Person currentUser = pm.login(code);

        if (currentUser == null) {
            System.out.println("Invalid code or user is inactive!");
        } else {
            System.out.println("\nLogin successful!");
            System.out.println("Welcome: " + currentUser.getFullName());
            System.out.println("Role: " + currentUser.getRole());

            if (canAccess(currentUser, "CHECK_IN")) {
                System.out.println("-> You have permission to check in.");
            } else {
                System.out.println("-> You CANNOT check in.");
            }
        }

        sc.close();
    }
}