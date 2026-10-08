/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

import java.util.ArrayList;
import java.util.Scanner;

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
  
    public void addZone(Scanner sc) {
        String id = "", name = "", description = "";
        int n = 0;
        while (true) {
            System.out.println("Input the number of zones needed to add: ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    System.out.println("The number must be greater than 0 ! Try again! ");
                    continue;
                }
                break; // n > 0 => out while loop 

            } catch (NumberFormatException e1) {
                System.out.println("Error input value must be integer! Try again!");
                return;
            } catch (Exception e2) {
                System.out.println(e2.getMessage() + " is not valid ! Try again!");
                return;
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("Zone [" + i + "]");

            while (true) {
                System.out.println("Enter Zone Id : ");
                String zoneIdPattern = "^Z\\d{3}$";
                id = sc.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("Id can not be empty! ");
                    continue;
                }
                if (!id.matches(zoneIdPattern)) {
                    System.out.println("Zone Id must follow format [Zxxx]");
                    continue;
                }
                if (findZoneById(id) != null) {
                    System.out.println("Zone Id already exists!");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.print("Enter Zone Name : ");
                name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println(" Name can not be empty! ");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.print("Enter Zone Description : ");
                description = sc.nextLine().trim();
                if (description.isEmpty()) {
                    System.out.println(" Description can not be empty! ");
                    continue;
                }
                break;
            }

            try {
                Zone z = new Zone(id, name, description);
                this.list.add(z);
                System.out.println("Add Zone [" + id + "] succeesfully! ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " is not valid ! Try again");
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

    public boolean deleteZoneById(String zoneId) {
        Zone z = this.findZoneById(zoneId);
        if (z != null) {
            list.remove(z);
            return true;
        } else {
            System.out.println("Id not found!");
            return false;
        }
    }

    public boolean updateZoneById(String zoneId, String newName, String newDescription) {
        Zone z = this.findZoneById(zoneId);

        if (z != null) {
            try {
                z.setZoneName(newName);
                z.setZoneDetails(newDescription);
                return true; // 
            } catch (IllegalArgumentException e) {
                System.out.println("Update failed! " + e.getMessage() + "is not valid ");
                return false;
            }
        }
        System.out.println("Zone Id not found!");
        return false;
    }

    public void displayList() {
        if (list.isEmpty()) {
            System.out.println("Zone list is empty now ! Add more zones ! ");
        }
        System.out.println("List of Zones: ");
        for (Zone z : this.list) {
            System.out.println(z);
        }
    }

    public void addRestrictedZone(Scanner sc) {
        String id = "", name = "", description = "", pinCode = "";

        int n = 0, accessLevel = -1;
        while (true) {
            System.out.println("Input the number of restricted zones needed to add: ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    System.out.println("The number must be greater than 0 ! Try again! ");
                    continue;
                }
                break; // n > 0 => out while loop    
            } catch (NumberFormatException e1) {
                System.out.println("Error input value must be integer! Try again!");
                return;
            } catch (Exception e2) {
                System.out.println(e2.getMessage() + " is not valid ! Try again!");
                return;
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("Restricted Zone [" + i + "]");

            while (true) {
                System.out.println("Enter Restricted Zone Id [RZxxx] : ");
                String restrictedIdPattern = "^RZ\\d{3}$";
                id = sc.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("Id can not be empty! ");
                    continue;
                }
                if (!id.matches(restrictedIdPattern)) {
                    System.out.println("Id must follow [RZxxx]");
                    continue;
                }
                if (findZoneById(id) != null) {
                    System.out.println("Zone Id already exists!");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.print("Enter Restricted Zone Name : ");
                name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println(" Name can not be empty! ");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.print("Enter Restricted Zone Description : ");
                description = sc.nextLine().trim();
                if (description.isEmpty()) {
                    System.out.println(" Description can not be empty! ");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.println("Enter Restricted Zone Pin Code [ABCxxx] : ");
                String codePattern = "^[A-Z]{3}\\d{3}$";
                pinCode = sc.nextLine().trim();
                if (pinCode == null || pinCode.trim().isEmpty()) {
                    System.out.println("Pin Code cannot be empty!");
                    continue;
                }
                if (!pinCode.trim().matches(codePattern)) {
                    System.out.println("Pin Code must follow format [ABCxxx] !");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.println("Enter Restricted Zone Access Level (0-2) : ");
                try {
                    accessLevel = Integer.parseInt(sc.nextLine().trim());
                    if (accessLevel < 0 || accessLevel > 2) {
                        System.out.println("Access Level must from 0 to 2 ! ");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Access level must be one of the numbers 0, 1, or 2 !");
                }
            }
            try {
                RestrictedZone rz = new RestrictedZone(id, name, description, accessLevel, pinCode);
                this.list.add(rz);
                System.out.println("Add Restricted Zone [" + id + "] succeesfully! ");
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
