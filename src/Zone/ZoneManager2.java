package Zone;

import Validatable.CheckId;
import Validatable.CheckName;
import Validatable.CheckDetails;
import Validatable.CheckCode;
import Validatable.InputScanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import ui_Dang_dang_sua.Draft;

public class ZoneManager2 {

    private ArrayList<Zone> list;
    private Zone activeZone;
    private static ZoneManager2 instance;

    public ZoneManager2() {
        this.list = new ArrayList<>();
        try {
            int cnt = 1;
            this.loadFromFile("data/Zone.txt");
            if (cnt == 1) {
                System.out.println("Load data successfully!");
                cnt -= 1;
            }
        } catch (IOException e) {
            System.out.println("[Notice]: No existing data file found or failed to load. Starting with an empty list.");
        }
    }

    public static ZoneManager2 getInstance() {
        if (instance == null) {
            instance = new ZoneManager2();
        }
        return instance;
    }

    public Zone getRandomActiveZone() {
        if (list == null || list.isEmpty()) {
            System.out.println("[Warning]: Zone list is empty! Cannot select a random zone.");
            return null;
        }

        if (this.activeZone == null) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(list.size());
            this.activeZone = list.get(randomIndex);
        }

        return this.activeZone;
    }

    public Zone getActiveZone() {
        return activeZone;
    }

    public void addZone() {
        Scanner sc = Validatable.InputScanner.getScanner();
        int n;
        while (true) {
            System.out.print("    ➔ Input the number of zones needed to add: ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    System.out.println("    [Error]: The number must be greater than 0!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("    [Error]: Invalid number input!");
                continue;
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

                try {
                    // isSaved = false vì mới thêm vào RAM, chưa lưu file
                    Zone z = new Zone(false, id, name, description, false);

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
            break;
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
        Scanner scanner = InputScanner.getScanner();
        String zoneId = CheckId.checkZoneId();
        Zone z = this.findZoneById(zoneId);

        if (z == null) {
            System.out.println(Draft.center(Draft.RED + "Please enter the Zone ID!" + Draft.BAS));
            return;
        }

        System.out.println("    ➔ Are you sure you want to delete this Zone: ");
        System.out.print(z + " ? [Y/N]: ");
        String choice = scanner.nextLine().trim();

        switch (choice.toUpperCase()) {
            case "N":
                System.out.println(Draft.center("Cancel deleting mod!"));
                System.out.println(Draft.center("No zone has been removed!"));
                break;
            case "Y":
                list.remove(z);
                System.out.println(Draft.center("Deleting sucessfully!"));
                break;
            default:
                System.out.println(Draft.center(Draft.RED + "Please enter a valid choice!" + Draft.BAS));
                break;
        }
    }

    public void updateZoneById() {
        String zoneId = CheckId.checkZoneId();
        Zone z = this.findZoneById(zoneId);

        if (z != null) {
            System.out.println("Current Zone Info: " + z);

            String newName = CheckName.checkZoneName();
            String newDescription = CheckDetails.checkZoneDetails();

            try {
                z.setZoneName(newName);
                z.setZoneDetails(newDescription);
                System.out.println(Draft.center(Draft.GRN + "Update Zone [" + zoneId + "] successfully!" + Draft.BAS));
            } catch (IllegalArgumentException e) {
                System.out.println(Draft.center(Draft.RED + "[Error]: Update failed! " + e.getMessage() + Draft.BAS));
            }
        } else {
            System.out.println(Draft.center(Draft.RED + "[Error]: Zone ID [" + zoneId + "] not found!" + Draft.BAS));
        }
    }

    private String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }

public void displayList() {
        if (list == null || list.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "The Zone list is empty!" + Draft.BAS));
            return;
        }

        Draft.displayMenuLine();
        System.out.println();
        System.out.println(Draft.center("=== The list of Zone(s) in S-SMARTSITE ==="));
        System.out.println();
        System.out.println();

        // In dòng tiêu đề bảng
        System.out.printf("        %-6s | %-5s | %-20s | %-53s | %-8s%n",
                "ID", "Type", "Zone Name", "Zone Details", "PIN Code");

        // Chuỗi gạch ngang định hình phân cách bảng
        System.out.println("      -------------------------------------------------------------------------------------------------------------");

        // Duyệt qua danh sách và in từng dòng dữ liệu
        for (Zone z : list) {
            if (z != null) {
                String id = z.getZoneId();
                String isRes = z.isRestricted() ? "RES" : "NOR";
                String name = z.getZoneName();
                String details = truncate(z.getZoneDetails(), 40);

                // Xử lý riêng trường PIN Code nếu là RestrictedZone
                String pinCode = "N/A";
                if (z instanceof RestrictedZone) {
                    RestrictedZone rz = (RestrictedZone) z;
                    pinCode = rz.getPinCode();
                }

                // Cấu hình màu sắc theo isSaved:
                // - isSaved = true (dữ liệu từ file): In màu xanh (Draft.GRN)
                // - isSaved = false (dữ liệu mới thêm RAM): Mặc định (không màu)
                String colorPrefix = "";
                String colorReset = "";
                if (z.isSaved()) {
                    colorPrefix = Draft.GRN;
                    colorReset = Draft.BAS;
                }

                System.out.printf("%s        %-6s | %-5s | %-20.20s | %-53.53s | %-8s%s%n",
                        colorPrefix, id, isRes, name, details, pinCode, colorReset);
            }
        }

        System.out.println("      -------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public void sortZonesById() {
        if (list == null || list.isEmpty()) {
            System.out.println("[Warning]: Zone list is empty. Nothing to sort.");
            return;
        }
        list.sort((z1, z2) -> z1.getZoneId().compareToIgnoreCase(z2.getZoneId()));

        System.out.println("-> Successfully sorted zone list by Zone ID!");
    }

    public void addRestrictedZone() {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            System.out.println("Input the number of zones needed to add: ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    System.out.println(Draft.center(Draft.RED + "[Error]: The number must be greater than 0!" + Draft.BAS));
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED + "[Error]: Invalid number input!" + Draft.BAS));
                continue;
            }

            for (int i = 1; i <= n; i++) {
                System.out.println(Draft.center("--- Restricted Zone [" + i + "] ---"));

                String id;
                while (true) {
                    id = CheckId.checkZoneId();
                    if (findZoneById(id) != null) {
                        System.out.println(Draft.center(Draft.RED + "[Error]: Zone ID [" + id + "] already exists! Try again!" + Draft.BAS));
                        continue;
                    }
                    break;
                }

                String name = CheckName.checkZoneName();
                String description = CheckDetails.checkZoneDetails();
                String pinCode = CheckCode.checkPinCode();

                try {
                    // isSaved = false vì mới thêm vào RAM, chưa lưu file
                    RestrictedZone rz = new RestrictedZone(id, name, description, false, pinCode);

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
            break;
        }
    }

public void displayRestrictedZonesOnly() {
        if (list == null || list.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "The Zone list is empty!" + Draft.BAS));
            return;
        }

        int countRes = 0;
        for (Zone z : list) {
            if (z instanceof RestrictedZone) {
                countRes++;
            }
        }

        if (countRes == 0) {
            System.out.println(Draft.center(Draft.RED + "No restricted zones found in the system!" + Draft.BAS));
            return;
        }

        Draft.displayMenuLine();
        System.out.println();
        System.out.println(Draft.center("=== The list of RESTRICTED ZONE(S) in S-SMARTSITE ==="));
        System.out.println();
        System.out.println();

        System.out.printf("        %-8s | %-20s | %-40s | %-10s%n",
                "Zone ID", "Zone Name", "Zone Details", "PIN Code");

        System.out.println("      -----------------------------------------------------------------------------------------------------------");

        for (Zone z : list) {
            if (z instanceof RestrictedZone) {
                RestrictedZone rz = (RestrictedZone) z;
                String id = rz.getZoneId();
                String name = rz.getZoneName();
                String details = truncate(rz.getZoneDetails(), 40);
                String pinCode = rz.getPinCode();

                // Cấu hình màu sắc tương tự cho danh sách Restricted Zone
                String colorPrefix = "";
                String colorReset = "";
                if (rz.isSaved()) {
                    colorPrefix = Draft.GRN;
                    colorReset = Draft.BAS;
                }

                System.out.printf("%s        %-8s | %-20s | %-40.40s | %-10s%s%n",
                        colorPrefix, id, name, details, pinCode, colorReset);
            }
        }

        System.out.println("      -----------------------------------------------------------------------------------------------------------");
        System.out.println();
    }
    public void loadFromFile(String fileName) throws IOException {
        ArrayList<Zone> loaded = new ArrayList<>();

        try ( BufferedReader reader = Files.newBufferedReader(
                Paths.get(fileName), StandardCharsets.UTF_8)) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s*\\|\\s*", -1);
                try {
                    Zone zone = null;
                    String type = parts[1].trim();

                    // Dữ liệu đọc từ file được gán isSaved = true
                    if ("NOR".equalsIgnoreCase(type) && parts.length == 4) {
                        String id = parts[0].trim();
                        String name = parts[2].trim();
                        String details = parts[3].trim();

                        zone = new Zone(false, id, name, details, true);
                    } else if ("RES".equalsIgnoreCase(type) && parts.length == 5) {
                        String id = parts[0].trim();
                        String name = parts[2].trim();
                        String details = parts[3].trim();
                        String pinCode = parts[4].trim();

                        zone = new RestrictedZone(id, name, details, true, pinCode);
                    } else {
                        System.out.println("[Warning]: Skipped line " + lineNumber + " due to invalid column count.");
                        continue;
                    }

                    if (zone.isValid()) {
                        loaded.add(zone);
                    } else {
                        System.out.println("[Warning]: Skipped invalid Zone object at line " + lineNumber + ".");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("[Error]: Skipped faulty line " + lineNumber + ": " + e.getMessage());
                }
            }
        }

        this.list.clear();
        this.list.addAll(loaded);
        System.out.println("-> Successfully loaded " + this.list.size() + " zone(s) from file [" + fileName + "]!");
    }

    public void saveToFile() {
        if (list == null || list.isEmpty()) {
            System.out.println("[Warning]: List is empty. Skipping save to avoid clearing existing file data.");
            return;
        }
        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("data/Zone.txt"), StandardCharsets.UTF_8))) {
            for (Zone z : list) {
                pw.println(z.toFileString());
            }
            
            // Sau khi ghi file thành công, cập nhật lại trạng thái isSaved = true cho toàn bộ danh sách trong RAM
            for (Zone z : list) {
                if (z != null) {
                    z.setSaved(true);
                }
            }
            
            System.out.println("-> Saved data successfully!");
        } catch (IOException e) {
            System.out.println("[Error]: " + e.getMessage());
        }
    }

}