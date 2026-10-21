package Zone;

import Validatable.CheckZoneId;
import Validatable.CheckZoneName;
import Validatable.CheckZoneDetails;
import Validatable.CheckPinCode;

import java.util.ArrayList;
import java.util.Scanner;

public class ZoneManager2 {

    private ArrayList<Zone> list;

    public ZoneManager2() {
        this.list = new ArrayList<>();
    }

    public void addZone() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number of zones needed to add: ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) {
                System.out.println("[Error]: The number must be greater than 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error]: Invalid number input!");
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Zone [" + i + "] ---");
            String id;

            while (true) {
                id = CheckZoneId.checkZoneId();
                if (findZoneById(id) != null) {
                    System.out.println("[Error]: Zone ID [" + id + "] already exists! Try again.");
                    continue;
                }
                break;
            }

            String name = CheckZoneName.checkZoneName();
            String description = CheckZoneDetails.checkZoneDetails();

            try {
                Zone z = new Zone(id, name, description);
                if (z.isValid()) {
                    this.list.add(z);
                    System.out.println("-> Add Zone [" + id + "] successfully!");
                } else {
                    System.out.println("[Error]: Failed to add Zone [" + id + "]: Invalid object state!");
                    i--;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Error]: " + e.getMessage() + "! Try again.");
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

    public void deleteZoneById() {
        String zoneId = CheckZoneId.checkZoneId();
        Zone z = this.findZoneById(zoneId);
        if (z != null) {
            list.remove(z);
            System.out.println("-> Delete Zone [" + zoneId + "] successfully!");
        } else {
            System.out.println("[Error]: Zone ID [" + zoneId + "] not found!");
        }
    }

    public void updateZoneById() {
        String zoneId = CheckZoneId.checkZoneId();
        Zone z = this.findZoneById(zoneId);

        if (z != null) {
            System.out.println("Current Zone Info: " + z);

            String newName = CheckZoneName.checkZoneName();
            String newDescription = CheckZoneDetails.checkZoneDetails();

            try {
                z.setZoneName(newName);
                z.setZoneDetails(newDescription);
                System.out.println("-> Update Zone [" + zoneId + "] successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println("[Error]: Update failed! " + e.getMessage());
            }
        } else {
            System.out.println("[Error]: Zone ID [" + zoneId + "] not found!");
        }
    }

    public void displayList() {
        if (list.isEmpty()) {
            System.out.println("Zone list is empty now! Add more zones.");
            return;
        }
        System.out.println("\n--- List of Zones ---");
        for (Zone z : this.list) {
            System.out.println(z);
        }
    }

    public void addRestrictedZone() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number of restricted zones needed to add: ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) {
                System.out.println("[Error]: The number must be greater than 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error]: Invalid number input!");
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Restricted Zone [" + i + "] ---");

            String id;
            while (true) {
                id = CheckZoneId.checkZoneId();
                if (findZoneById(id) != null) {
                    System.out.println("[Error]: Zone ID [" + id + "] already exists! Try again.");
                    continue;
                }
                break;
            }

            String name = CheckZoneName.checkZoneName();
            String description = CheckZoneDetails.checkZoneDetails();
            String pinCode = CheckPinCode.checkPinCode();

            try {
                RestrictedZone rz = new RestrictedZone(id, name, description, pinCode);
                if (rz.isValid()) {
                    this.list.add(rz);
                    System.out.println("-> Add Restricted Zone [" + id + "] successfully!");
                } else {
                    System.out.println("[Error]: Failed to add Restricted Zone [" + id + "]: Invalid object state!");
                    i--;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Error]: " + e.getMessage() + "! Try again.");
                i--;
            }
        }
    }

    public void displayRestrictedZonesOnly() {
        boolean found = false;
        System.out.println("\n--- List of Restricted Zones ---");
        for (Zone z : this.list) {
            if (z.isAccessRestricted()) {
                System.out.println(z);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No restricted zones found.");
        }
    }
}