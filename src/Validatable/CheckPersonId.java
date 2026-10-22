/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validatable;

import java.util.Scanner;

/**
 *
 * @author GIA HUY
 */
public class CheckPersonId {
     private static final Scanner sc = new Scanner(System.in);

    public static String checkPersonId() {
        while (true) {
            System.out.print("Enter Person ID [PRxxxx]: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("[Error]: Person ID cannot be empty! Please try again.");
                continue;
            }
            if (!id.matches("^P\\d{4}$")) {
                System.out.println("[Error]: Person ID must follow format [PRxxxx] (e.g., PR0001)! Please try again.");
                continue;
            }
            return id;
        }
    }
}
