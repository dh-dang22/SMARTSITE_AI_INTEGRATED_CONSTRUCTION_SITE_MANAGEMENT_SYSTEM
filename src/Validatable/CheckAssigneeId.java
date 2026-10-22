package Validatable;

import java.util.Scanner;

public class CheckAssigneeId {

    private static final Scanner sc = new Scanner(System.in);

    public static String checkAssigneeId() {
        while (true) {
            System.out.print("Enter Assignee ID [Pxxx, Wxxx, Oxxx, Mxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Assignee ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^(P|W|O|M)\\d{3}$")) {
                System.out.println("[Error]: Assignee ID must follow format [Pxxx], [Wxxx], [Oxxx], [Mxxx] (e.g., P001, W001,O001, M001)! Please try again.");
                continue;
            }
            return id;  
        }
    }
}