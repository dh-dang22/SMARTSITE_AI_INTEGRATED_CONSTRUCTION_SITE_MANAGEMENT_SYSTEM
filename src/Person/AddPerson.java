/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.util.NoSuchElementException;
import java.util.Scanner;
import Validatable.InputScanner;


public class AddPerson {
    private final PersonManager pm = PersonManager.getInstance();

    public AddPerson() {
    }
    
    private String readRequired(String message) {
    while (true) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            throw new NoSuchElementException();
        }

        String value = sc.nextLine().trim();

        if (value.isEmpty()) {
            System.out.println("Thong tin khong duoc de trong!");
        } else if (value.contains("|")) {
            System.out.println("Thong tin khong duoc chua dau |");
        } else {
            return value;
        }
    }
}

public void addPersonFromInput() {
    try {
        String role = chooseRole();

        if (role == null) {
            return;
        }

        String id = readRequired("Nhap ID: ");
        if (pm.findPersonByID(id) != null) {
            System.out.println("ID da ton tai!");
            return;
        }

        String name = readRequired("Nhap ho ten: ");
        String code = readRequired("Nhap code dang nhap: ");

        if (pm.findPersonByCode(code) != null) {
            System.out.println("Code da ton tai!");
            return;
        }

        Person person;
        String status = "ACTIVE";

        switch (role) {
            case "WORKER":
            case "CONTRACTOR":
                String company = readRequired("Nhap cong ty: ");
                String contract = readRequired("Nhap loai hop dong: ");

                person = new Worker(
                    id, name, code, role, status, company, contract
                );
                break;

            case "VISITOR":
                String purpose = readRequired("Nhap muc dich den: ");
                String host = readRequired("Nhap nguoi tiep don: ");

                person = new Visitor(
                    id, name, code, role, status, purpose, host
                );
                break;

            case "SAFETY_OFFICER":
                String license = readRequired("Nhap giay phep: ");

                person = new SafetyOfficer(
                    id, name, code, role, status, 0, license
                );
                break;

            case "SITE_MANAGER":
                String department = readRequired("Nhap bo phan: ");

                person = new SiteManager(
                    id, name, code, role, status, department
                );
                break;

            default:
                return;
        }

        if (pm.addPerson(person)) {
            System.out.println("Da them vao danh sach trong bo nho!");
        } else {
            System.out.println(
                "Them that bai: du lieu khong hop le hoac ID/code trung."
            );
        }
    } catch (NoSuchElementException e) {
        System.out.println("Da dung nhap du lieu.");
    }
}

private String chooseRole() {
    while (true) {
        System.out.println("\n--- CHON ROLE ---");
        System.out.println("1. Worker");
        System.out.println("2. Contractor");
        System.out.println("3. Visitor");
        System.out.println("4. Safety Officer");
        System.out.println("5. Site Manager");
        System.out.println("0. Huy");

        String choice = readRequired("Lua chon: ");

        switch (choice) {
            case "1": return "WORKER";
            case "2": return "CONTRACTOR";
            case "3": return "VISITOR";
            case "4": return "SAFETY_OFFICER";
            case "5": return "SITE_MANAGER";
            case "0": return null;
            default:
                System.out.println("Vui long chon tu 0 den 5!");
        }
    }
}



}
