/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.util.NoSuchElementException;
import java.util.Scanner;
import Validatable.InputScanner;


public class ConditionPerson {
    private final PersonManager pm = PersonManager.getInstance();

    public ConditionPerson() {
    }
   
    public static String checkValid(Person p, PersonManager pm) {
    if (p == null) {
        return "Person khong duoc null!";
    }

    if (!p.isValid()) {
        return "Thong tin Person khong hop le!";
    }

    if (pm.findPersonByID(p.getId()) != null) {
        return "ID da ton tai!";
    }

    if (pm.findPersonByCode(p.getCode()) != null) {
        return "Code da ton tai!";
    }

    return null;
}
    private String readRequired(String message) {
    while (true) {
        System.out.print(message);
        Scanner sc = InputScanner.getScanner();
        if (!sc.hasNextLine()) {
            throw new NoSuchElementException();
        }

        String value = sc.nextLine().trim();

        if (value.isEmpty()) {
            System.out.println("Thong tin khong duoc de trong!");
        } else if (value.contains("|")) {
            System.out.println("Thong    tin khong duoc chua dau |");
        } else {
            return value;
        }
    }
    }
    // Trong ConditionPerson.java
public Person inputPerson() {
    String role = chooseRole();

    if (role == null) {
        return null; // Chọn hủy
    }

    return inputPerson(role); // Gọi hàm private bạn đã viết
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
    
private Person inputPerson(String role) {
    if (role == null) {
        throw new IllegalArgumentException("Role khong duoc null!");
    }

    String id = readRequired("Nhap ID: ");
    String name = readRequired("Nhap ho ten: ");
    String code = readRequired("Nhap code dang nhap: ");
    String status = "ACTIVE";

    switch (role) {
        case "WORKER": {
    String company = readRequired("Nhap cong ty: ");
    String contract = readRequired("Nhap loai hop dong: ");

        return new Worker(
            id, name, code, role, status, company, contract
        );
    }

    case "CONTRACTOR": {
        String contactPerson = readRequired("Nhap nguoi lien he: ");
        String contractStatus = readRequired("Nhap trang thai hop dong: ");

        return new Contractor(
            id, name, code, status, contactPerson);
    }

        case "VISITOR": {
            String purpose = readRequired("Nhap muc dich den: ");
            String host = readRequired("Nhap nguoi tiep don: ");

            return new Visitor(
                id, name, code, role, status, purpose, host
            );
        }

        case "SAFETY_OFFICER": {
            String license = readRequired("Nhap giay phep: ");

            return new SafetyOfficer(
                id, name, code, role, status, 0, license
            );
        }

        case "SITE_MANAGER": {
            String department = readRequired("Nhap bo phan: ");

            return new SiteManager(
                id, name, code, role, status, department
            );
        }

        default:
            throw new IllegalArgumentException(
                "Role khong hop le: " + role
            );
    }
}
}
    
    



