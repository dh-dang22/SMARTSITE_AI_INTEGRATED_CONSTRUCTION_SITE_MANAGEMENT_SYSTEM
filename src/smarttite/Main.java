/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Minh_Khang
 */
public class Main {

    public static Person login(String code, ArrayList<Person> people) {
        for (Person p : people) {
            if (p.getCode().equals(code) && p.isActive()) {
                return p;
            }
        }
        return null;
    }

    public static boolean canAccess(Person user, String action) {
        String role = user.getRole();

        if (role.equals("WORKER") || role.equals("VISITOR")) {
            return action.equals("CHECK_IN")
                || action.equals("CHECK_OUT")
                || action.equals("VIEW_HISTORY");
        }

        if (role.equals("SAFETY_OFFICER")) {
            return action.equals("LOG_INCIDENT")
                || action.equals("UPDATE_INCIDENT");
        }

        if (role.equals("SITE_MANAGER")) {
            return action.equals("VIEW_REPORT")
                || action.equals("MANAGE_ZONE")
                || action.equals("MANAGE_PERSON");
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Person> people = new ArrayList<>();

        people.add(new Worker("P001", "Nguyen Van A", "W001", "WORKER", "ACTIVE"));
        people.add(new Person("P003", "Safety Officer", "S001", "SAFETY_OFFICER", "ACTIVE"));
        people.add(new Person("P004", "Site Manager", "M001", "SITE_MANAGER", "ACTIVE"));

        System.out.print("Enter code: ");
        String code = sc.nextLine().trim();

        Person currentUser = login(code, people);

        if (currentUser == null) {
            System.out.println("Invalid code!");
        } else {
            System.out.println("Login successful!");
            System.out.println("Welcome " + currentUser.getFullName());
            System.out.println("Role: " + currentUser.getRole());

            if (canAccess(currentUser, "CHECK_IN")) {
                System.out.println("You can check in.");
            } else {
                System.out.println("You cannot check in.");
            }
        }

        sc.close();
    }
}