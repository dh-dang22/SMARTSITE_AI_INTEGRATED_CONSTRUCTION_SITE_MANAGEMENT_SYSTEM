/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zone;

import java.util.ArrayList;
import java.util.Scanner;
import Validatable.CheckInput;
       
/**
 *
 * @author GIA HUY
 */
public class ZoneManager2 {

    private ArrayList<Zone> list;

    public ZoneManager2() {
        this.list = new ArrayList<>();
    }

    // add, delete,findByName, findById, update, display 
    // showRestrictedZones

    public void addZone() {
        Scanner sc = new Scanner(System.in);
        int n = CheckInput.readRange(sc, "Input the number of zones needed to add: ", 1, 20, "The number must be greater than 0 ! Try again!");

        // Trường hợp ngắt luồng nhập (EOF) từ CheckInput
        if (n == -1) {
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("Zone [" + i + "]");
            String id;

            while (true) {
                id = CheckInput.readPattern(sc, "Enter Zone Id [Zxxx] : ", "^Z\\d{3}$", "Zone Id must follow format [Zxxx]");
                if (id.isEmpty()) {
                    return;
                }
                if (findZoneById(id) != null) {
                    System.out.println("Zone Id already exists! Try again!");
                    continue;
                }
                break;
            }

            String name = CheckInput.checkEmpty(sc,"Enter Zone Name : ", "Name can not be empty!");
            if (name.isEmpty()) {
                return;
            }

            String description = CheckInput.checkEmpty(sc,"Enter Zone Description : ", "Description can not be empty!");
            if (description.isEmpty()) {
                return;
            }

            try {
                Zone z = new Zone(id, name, description);
                if (CheckInput.checkObject(z)) {
                    this.list.add(z);
                    System.out.println("Add Zone [" + id + "] successfully!");
                } else {
                    System.out.println("Failed to add Zone [" + id + "]: Invalid object state!");
                    i--;    
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " is not valid! Try again.");
                i--; 
            }
        }
    }

    public Zone findZoneById(String zoneId) {
        for (Zone z : list) {
            if (z.getZoneId().equalsIgnoreCase(zoneId)) {
                return z;
            }
        }
        return null;
    }



    public void deleteZoneById(Scanner sc) {
    String zoneId = CheckInput.readPattern(sc, "Enter Zone ID to delete [Zxxx/RZxxx]: ", "^(Z|RZ)\\d{3}$", "Zone ID must follow format [Zxxx] or [RZxxx]!");
    if (zoneId.isEmpty()) return;
    // 2 dong tren kiem tra id nhap vao co hop le hay khong
    Zone z = this.findZoneById(zoneId);
    if (z != null) {
        list.remove(z);
        System.out.println("Delete Zone [" + zoneId + "] successfully!");
    } else {
        System.out.println("Id not found!");
    }
}
    

    
    public void updateZoneById(Scanner sc) {
    String zoneId = CheckInput.readPattern(sc,  "Enter Zone ID/Restricted Zone ID to update [Zxxx/RZxxx]: ", "^(Z|RZ)\\d{3}$", "Zone ID must follow format [Zxxx] or [RZxxx]!");
    if (zoneId.isEmpty()) return;

    Zone z = this.findZoneById(zoneId);
    if (z != null) {
        System.out.println("Current Zone Info: " + z);

        String newName = CheckInput.checkEmpty(sc, "Enter New Zone Name: ", "Name can not be empty!");
        if (newName.isEmpty()) return;

        String newDescription = CheckInput.checkEmpty(sc, "Enter New Zone Description: ", "Description can not be empty!");
        if (newDescription.isEmpty()) return;

        try {
            z.setZoneName(newName);
            z.setZoneDetails(newDescription);
            System.out.println("Update Zone [" + zoneId + "] successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Update failed! " + e.getMessage() + " is not valid");
        }
    } else {
        System.out.println("Zone Id not found!");
    }
}

    public void displayList() {
        if (list.isEmpty()) {
            System.out.println("Zone list is empty now ! Add more zones ! ");
            return;
        }
        System.out.println("List of Zones: ");
        for (Zone z : this.list) {
            System.out.println(z);
        }
    }


    
    public void addRestrictedZone(Scanner sc) {
    // 1. Nhập số lượng Restricted Zone cần thêm
    int n = CheckInput.readRange(sc, "Input the number of restricted zones needed to add: ", 1, 100, "The number must be greater than 0 ! Try again!");

    // Kiểm tra ngắt luồng (EOF)
    if (n == -1) return;

    for (int i = 1; i <= n; i++) {
        System.out.println("Restricted Zone [" + i + "]");

       
        String id;
        while (true) {
            id = CheckInput.readPattern(sc, "Enter Restricted Zone Id [RZxxx] : ", "^RZ\\d{3}$", "Id must follow [RZxxx]");

            if (id.isEmpty()) return;

            if (findZoneById(id) != null) {
                System.out.println("Zone Id already exists!");
                continue;
            }
            break;
        }

        
        String name = CheckInput.checkEmpty(sc,"Enter Restricted Zone Name : ", "Name can not be empty!");
        if (name.isEmpty()) return;

      
        String description = CheckInput.checkEmpty(sc, "Enter Restricted Zone Description : ", "Description can not be empty!");
        if (description.isEmpty()) return;

       
        String pinCode = CheckInput.readPattern(sc, "Enter Restricted Zone Pin Code [ABCxxx] : ", "^[A-Z]{3}\\d{3}$", "Pin Code must follow format [ABCxxx] !" );
        if (pinCode.isEmpty()) return;

//      int accessLevel = CheckInput.readRange(sc, "Enter Restricted Zone Access Level (0-2) : ",  0, 2, "Access Level must from 0 to 2 !" );
// không nên bỏ phần này vì Person sẽ gán level sẵn nhưng zone thì phải nhập và không cố định giữa các zone 

        try {
            RestrictedZone rz = new RestrictedZone(id, name, description, /* accessLevel, */ pinCode);
            this.list.add(rz);
            System.out.println("Add Restricted Zone [" + id + "] successfully!");
            
                
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " is not valid ! Try again");
            i--;
        }
    }
}

    public void displayRestrictedZonesOnly() {
        System.out.println("List of Restricted Zones: ");
        for (Zone z : this.list) {
            if (z.isAccessRestricted()) {
                System.out.println(z);
            }
        }
    }

}
