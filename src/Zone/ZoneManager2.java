package Zone;

import Validatable.CheckId;
import Validatable.CheckName;
import Validatable.CheckDetails;
import Validatable.CheckCode;
import java.util.ArrayList;
import java.util.Scanner;
//import ham load from file;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
public class ZoneManager2 {

    private ArrayList<Zone> list;
    private Zone activeDemoZone;
    
    public ZoneManager2() {
        this.list = new ArrayList<>();
    }

    public void addZone() {
        Scanner sc = Validatable.InputScanner.getScanner();
        System.out.print("Input the number of zones needed to add: ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
            if (n <= 0) {
                System.out.println("    [Error]: The number must be greater than 0!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("    [Error]: Invalid number input!");
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("\n                                                  --- Zone [" + i + "] ---");
            String id;

            while (true) {
                id = CheckId.checkZoneId();
                if (findZoneById(id) != null) {
                    System.out.println("                      [Error]: Zone ID [" + id + "] already exists! Try again.");
                    continue;
                }
                break;
            }

            String name = CheckName.checkZoneName();
            String description = CheckDetails.checkZoneDetails();

            String allowedRole = chooseAllowedRole();
      if (allowedRole == null) {
          System.out.println("Đã hủy thao tác thêm Zone.");
          return;
      }

      try {
          Zone z = new Zone(id, name, description, allowedRole);

          if (z.isValid()) {
              this.list.add(z);
              System.out.println("Add Zone [" + id + "] successfully!");
          } else {
              System.out.println("Zone data is invalid.");
              i--;
          }
      } catch (IllegalArgumentException e) {
          System.out.println("[Error]: " + e.getMessage());
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
        String zoneId = CheckId.checkZoneId();
        Zone z = this.findZoneById(zoneId);
        if (z != null) {
            list.remove(z);
            System.out.println("     Delete Zone [" + zoneId + "] successfully!");
        } else {
            System.out.println("    [Error]: Zone ID [" + zoneId + "] not found!");
        }
    }

    public void updateZoneById() {
        String zoneId = "   ➔" + CheckId.checkZoneId();
        Zone z = this.findZoneById(zoneId);

        if (z != null) {
            System.out.println("    Current Zone Info: " + z);

            String newName = CheckName.checkZoneName();
            String newDescription = CheckDetails.checkZoneDetails();

            try {
                z.setZoneName(newName);
                z.setZoneDetails(newDescription);
                System.out.println("     Update Zone [" + zoneId + "] successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println("    [Error]: Update failed! " + e.getMessage());
            }
        } else {
            System.out.println("    [Error]: Zone ID [" + zoneId + "] not found!");
        }
    }

    public void displayList() {
        if (list.isEmpty()) {
            System.out.println("     [ERROR] Zone list is empty now! Add more Zone(s).");
            return;
        }
        System.out.println("                                        \n--- List of Zones ---");
        for (Zone z : this.list) {
            System.out.println(z);
        }
    }

        public void addRestrictedZone() {
       Scanner sc = new Scanner(System.in);
       System.out.print("Allowed role (WORKER/SAFETY_OFFICER/CONTRACTOR/VISITOR): ");
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
                    id = CheckId.checkZoneId();
                    if (findZoneById(id) != null) {
                        System.out.println("[Error]: Zone ID [" + id + "] already exists! Try again.");
                        continue;
                    }
                    break;
                }

                        String name = CheckName.checkZoneName();
            String description = CheckDetails.checkZoneDetails();
            String pinCode = CheckCode.checkPinCode();

            String allowedRole = chooseAllowedRole();
            if (allowedRole == null) {
                System.out.println("Đã hủy thao tác thêm Restricted Zone.");
                return;
    }

    try {
        RestrictedZone rz =
                new RestrictedZone(id, name, description, pinCode, allowedRole);

        if (rz.isValid()) {
            this.list.add(rz);
            System.out.println("Restricted Zone [" + id + "] added successfully!");
        } else {
            System.out.println("Restricted Zone data is invalid.");
            i--;
        }
    } catch (IllegalArgumentException e) {
        System.out.println("[Error]: " + e.getMessage());
        i--;
    }
            }
        }

        public void displayRestrictedZonesOnly() {
            boolean found = false;
            System.out.println("\n                                          --- List of Restricted Zones ---");
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
        public void loadFromFile(String fileName) throws IOException {
        ArrayList<Zone> loaded = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(fileName), StandardCharsets.UTF_8)) {

            String line;
            int lineNumber = 0;

           while ((line = reader.readLine()) != null) {
        lineNumber++;

        if (line.trim().isEmpty()) {
            continue;
        }

        String[] parts = line.split("\\|", -1);
                try {
                    Zone zone;

                    if ("ZONE".equalsIgnoreCase(parts[0]) && parts.length == 5) {
                        zone = new Zone(
                                parts[1].trim(),
                                parts[2].trim(),
                                parts[3].trim(),
                                parts[4].trim()
                        );
                    } else if ("REST".equalsIgnoreCase(parts[0])
                            && parts.length == 6) {
                        zone = new RestrictedZone(
                                parts[1].trim(),
                                parts[2].trim(),
                                parts[3].trim(),
                                parts[4].trim(),
                                parts[5].trim()
                        );
                    } else {
                        System.out.println(
                                "Bỏ qua dòng Zone không đúng định dạng: " + lineNumber
                        );
                        continue;
                    }

                    if (zone.isValid()) {
                        loaded.add(zone);
                    } else {
                        System.out.println(
                                "Bỏ qua Zone không hợp lệ ở dòng: " + lineNumber
                        );
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(
                            "Bỏ qua dòng Zone lỗi " + lineNumber + ": " + e.getMessage()
                    );
                }
            }
        }

        list.clear();
        list.addAll(loaded);
    }
        public boolean selectActiveDemoZone(String zoneId) {
        Zone zone = findZoneById(zoneId);
        if (zone == null) {
            return false;
        }

        activeDemoZone = zone;
        return true;
    }
        private String chooseAllowedRole() {
    Scanner sc = Validatable.InputScanner.getScanner();

    while (true) {
        System.out.println("Allowed role:");
        System.out.println("1. WORKER");
        System.out.println("2. SAFETY_OFFICER");
        System.out.println("3. CONTRACTOR");
        System.out.println("4. VISITOR");
        System.out.print("Choose role [1-4]: ");

        if (!sc.hasNextLine()) {
            return null;
        }

        switch (sc.nextLine().trim()) {
            case "1":
                return "WORKER";
            case "2":
                return "SAFETY_OFFICER";
            case "3":
                return "CONTRACTOR";
            case "4":
                return "VISITOR";
            default:
                System.out.println("Please choose from 1 to 4.");
        }
    }
}
}