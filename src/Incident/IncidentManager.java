package Incident;

import Zone.Zone;
import Zone.ZoneManager2;
import Person.Person;
import Person.Worker;
import Person.PersonManager;
import Validatable.CheckId;
import Validatable.CheckDetails;
import Validatable.InputScanner;
import ui_Dang_dang_sua.Draft;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncidentManager {

    private final List<Incident> incidents;
    private static IncidentManager instance;
    private static final String FILE_PATH = "data/Incident.txt";

    public IncidentManager() {
        this.incidents = new ArrayList<>();
        try {
            this.loadFromFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println(Draft.center(Draft.RED + "[Notice]: Failed to load file or no existing Incident file found." + Draft.BAS));
        }
    }

    public static IncidentManager getInstance() {
        if (instance == null) {
            instance = new IncidentManager();
        }
        return instance;
    }

    // ----------------------------------------------------------------
    // 1. Thêm mới Incident
    // ----------------------------------------------------------------
    public void addIncident() {
        Scanner sc = InputScanner.getScanner();
        ZoneManager2 zoneManager = ZoneManager2.getInstance();
        PersonManager personManager = PersonManager.getInstance();

        System.out.print("    ➔ Input number of incidents to add (1-5): ");
        int n = InputScanner.readRange(sc, "", 1, 5, Draft.center(Draft.RED + "[Error]: Please enter a number between 1 and 5!" + Draft.BAS));
        if (n == -1) {
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(Draft.center("--- Incident [" + i + "] ---"));

            // 1. Check Incident ID
            String incidentId;
            while (true) {
                incidentId = CheckId.checkIncidentId();
                if (findIncidentByIdLogic(incidentId) != null) {
                    System.out.println(Draft.center(Draft.RED + "[Error]: Incident ID [" + incidentId + "] already exists! Try again." + Draft.BAS));
                    continue;
                }
                break;
            }

            // 2. Tra cứu đối tượng Zone
            Zone selectedZone;
            while (true) {
                String zoneId = CheckId.checkZoneId();
                selectedZone = zoneManager.findZoneById(zoneId);
                if (selectedZone == null) {
                    System.out.println(Draft.center(Draft.RED + "[Error]: Zone ID [" + zoneId + "] not found in system! Try again." + Draft.BAS));
                    continue;
                }
                break;
            }

            // 3. Tra cứu hoặc Khởi tạo đối tượng Person
            Person selectedPerson;
            while (true) {
                String personId = CheckId.checkPersonId();
                selectedPerson = personManager.findPersonByID(personId);

                // Nếu chưa có trong PersonManager thì tự khởi tạo đối tượng Person tạm thời
                if (selectedPerson == null) {
                    selectedPerson = new Worker(personId, "Unknown Person", "WK0000", "WORKER", "ACTIVE", "N/A", "FULLTIME");
                }
                break;
            }

            String description = CheckDetails.checkDetails();
            String status = CheckDetails.checkIncidentStatus();
            String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            try {
                // Truyền 2 đối tượng selectedZone và selectedPerson vào Incident
                Incident inc = new Incident(incidentId, selectedZone, selectedPerson, description, status, timeStr, false);

                if (inc.isValid()) {
                    incidents.add(inc);
                    System.out.println(Draft.center(Draft.GRN + "Add Incident [" + incidentId + "] successfully!" + Draft.BAS));
                } else {
                    System.out.println(Draft.center(Draft.RED + "[Error]: Invalid Incident data!" + Draft.BAS));
                    i--;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Draft.center(Draft.RED + "[Error]: " + e.getMessage() + Draft.BAS));
                i--;
            }
        }
    }

    // ----------------------------------------------------------------
    // 2. Tìm kiếm Incident theo ID (Hiển thị dạng BẢNG)
    // ----------------------------------------------------------------
    public Incident findIncidentById(String incidentId) {
        Incident inc = findIncidentByIdLogic(incidentId);
        if (inc != null) {
            System.out.println();
            System.out.println(Draft.center("=== INCIDENT INFORMATION ==="));
            System.out.println();
            printTableHeader();
            printIncidentRow(inc);
            printTableFooter();
        } else {
            System.out.println(Draft.center(Draft.RED + "[Error]: Incident ID [" + incidentId + "] not found!" + Draft.BAS));
        }
        return inc;
    }

    public Incident findIncidentByIdLogic(String incidentId) {
        if (incidentId == null) {
            return null;
        }
        for (Incident inc : incidents) {
            if (inc.getIncidentId().equalsIgnoreCase(incidentId.trim())) {
                return inc;
            }
        }
        return null;
    }

    // ----------------------------------------------------------------
    // 3. Cập nhật Incident
    // ----------------------------------------------------------------
    public void updateIncidentById() {
        if (incidents.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "The Incident list is empty!" + Draft.BAS));
            return;
        }

        displayIncidents();

        String incidentId = CheckId.checkIncidentId();
        Incident inc = findIncidentByIdLogic(incidentId);

        if (inc != null) {
            // Hiển thị thông tin Incident hiện tại dạng BẢNG trước khi hỏi update
            System.out.println("\n                               Current Incident Information");
            printTableHeader();
            printIncidentRow(inc);
            printTableFooter();

            System.out.println("\n   ➔ Enter information you want to update:");
            System.out.println("   [1] Description");
            System.out.println("   [2] Status");
            System.out.println("   [3] All information");

            Scanner sc = InputScanner.getScanner();
            int choice = InputScanner.readRange(sc, "   Choose [1-3]: ", 1, 3, Draft.center(Draft.RED + "Invalid choice! Enter 1-3." + Draft.BAS));

            String newDesc = inc.getDescription();
            String newStatus = inc.getStatus();

            switch (choice) {
                case 1:
                    newDesc = CheckDetails.checkDetails();
                    break;
                case 2:
                    newStatus = CheckDetails.checkIncidentStatus();
                    break;
                case 3:
                    newDesc = CheckDetails.checkDetails();
                    newStatus = CheckDetails.checkIncidentStatus();
                    break;
                default:
                    break;
            }

            try {
                inc.setDescription(newDesc);
                inc.setStatus(newStatus);
                inc.setSaved(false);

                System.out.println(Draft.center(Draft.GRN + "Update Incident [" + incidentId + "] successfully!" + Draft.BAS));
            } catch (IllegalArgumentException e) {
                System.out.println(Draft.center(Draft.RED + "[Error]: Update failed! " + e.getMessage() + Draft.BAS));
            }
        } else {
            System.out.println(Draft.center(Draft.RED + "[Error]: Incident ID [" + incidentId + "] not found!" + Draft.BAS));
        }
    }

    // ----------------------------------------------------------------
    // 4. Xóa Incident (Hỏi Y/N)
    // ----------------------------------------------------------------
    public void deleteIncidentById() {
        displayIncidents();
        String incidentId = CheckId.checkIncidentId();
        Incident inc = findIncidentByIdLogic(incidentId);

        if (inc != null) {
            System.out.println("\n                               Current Incident Information");
            printTableHeader();
            printIncidentRow(inc);
            printTableFooter();
        } else {
            System.out.println(Draft.center(Draft.RED + "[Error]: Incident ID [" + incidentId + "] not found!" + Draft.BAS));
            return;
        }
        Scanner sc = InputScanner.getScanner();

        System.out.print("    ➔ Are you sure you want to delete this Incident? [Y/N]: ");
        String choice = sc.nextLine().trim();

        if (choice.equalsIgnoreCase("Y")) {
            incidents.remove(inc);
            System.out.println(Draft.center(Draft.GRN + "Delete Incident [" + incidentId + "] successfully!" + Draft.BAS));
        } else {
            System.out.println(Draft.center("Cancel deleting mode! No incident was removed."));
        }
    }

    // ----------------------------------------------------------------
    // 5. Hiển thị toàn bộ dạng BẢNG
    // ----------------------------------------------------------------
    public void displayIncidents() {
        if (incidents.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "The Incident list is empty!" + Draft.BAS));
            return;
        }

        Draft.displayMenuLine();
        System.out.println();
        System.out.println(Draft.center("=== The list of Incident(s) in S-SMARTSITE ==="));
        System.out.println();

        printTableHeader();
        for (Incident inc : incidents) {
            if (inc != null) {
                printIncidentRow(inc);
            }
        }
        printTableFooter();
        System.out.println("    [!] Note: The blue data is saved in file, while the black isn't. Remember to press \"Save file\" before leaving.");
        System.out.println();
    }

    // ----------------------------------------------------------------
    // 6. Sắp xếp Incident theo ID (Copy List view)
    // ----------------------------------------------------------------
    public void sortIncidentById() {
        if (incidents.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "Incident list is empty. Nothing to sort." + Draft.BAS));
            return;
        }

        List<Incident> sortedList = new ArrayList<>(incidents);
        sortedList.sort((i1, i2) -> i1.getIncidentId().compareToIgnoreCase(i2.getIncidentId()));

        Draft.displayMenuLine();
        System.out.println();
        System.out.println(Draft.center("=== INCIDENT LIST SORTED BY ID ==="));
        System.out.println();

        printTableHeader();
        for (Incident inc : sortedList) {
            if (inc != null) {
                printIncidentRow(inc);
            }
        }
        printTableFooter();
        System.out.println("    [!] Note: This is a temporary sorted view. Original order is unchanged.");
        System.out.println();
    }

    // ----------------------------------------------------------------
    // 7. Đọc / Ghi File
    // ----------------------------------------------------------------
    public void loadFromFile(String fileName) throws IOException {
        List<Incident> loaded = new ArrayList<>();
        ZoneManager2 zm = ZoneManager2.getInstance();
        PersonManager pm = PersonManager.getInstance();

        try ( BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s*\\|\\s*", -1);
                if (parts.length == 6) {
                    String incidentId = parts[0].trim();
                    String zoneId = parts[1].trim();
                    String personId = parts[2].trim();
                    String desc = parts[3].trim();
                    String status = parts[4].trim();
                    String time = parts[5].trim();

                    // Tìm Zone từ ZoneManager2 (nếu rỗng tự tạo Zone giả lập)
                    Zone zone = zm.findZoneById(zoneId);
                    if (zone == null) {
                        zone = new Zone(false, zoneId, "Unknown Zone", "N/A", true);
                    }

                    // Tìm Person từ PersonManager (nếu rỗng tự tạo Person giả lập)
                    Person person = pm.findPersonByID(personId);
                    if (person == null) {
                        person = new Worker(personId, "Unknown Person", "WK0000", "WORKER", "ACTIVE", "N/A", "FULLTIME");
                    }

                    Incident inc = new Incident(incidentId, zone, person, desc, status, time, true);
                    if (inc.isValid()) {
                        loaded.add(inc);
                    }
                } else {
                    System.out.println(Draft.center(Draft.RED + "[Warning]: Skipped line " + lineNumber + " due to invalid format." + Draft.BAS));
                }
            }
        }

        this.incidents.clear();
        this.incidents.addAll(loaded);
        System.out.println(Draft.center(Draft.GRN + "Successfully loaded " + this.incidents.size() + " incident(s) from file [" + fileName + "]!" + Draft.BAS));
        Draft.clearConsoleIDE();
    }

    public void saveToFile() {
        if (incidents.isEmpty()) {
            System.out.println(Draft.center(Draft.RED + "[Warning]: Incident list is empty. Skipping save." + Draft.BAS));
            return;
        }

        try ( PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(FILE_PATH), StandardCharsets.UTF_8))) {
            for (Incident inc : incidents) {
                pw.println(inc.toFileString());
            }

            for (Incident inc : incidents) {
                if (inc != null) {
                    inc.setSaved(true);
                }
            }
            System.out.println(Draft.center(Draft.GRN + "Saved Incident data successfully!" + Draft.BAS));
        } catch (IOException e) {
            System.out.println(Draft.center(Draft.RED + "[Error]: " + e.getMessage() + Draft.BAS));
        }
    }

    // ----------------------------------------------------------------
    // Các hàm trợ giúp kẻ BẢNG
    // ----------------------------------------------------------------
    private void printTableHeader() {
        System.out.printf("    %-8s | %-8s | %-10s | %-40s | %-12s | %-19s%n",
                "Inc ID", "Zone ID", "Person ID", "Description", "Status", "Time");
        System.out.println("  --------------------------------------------------------------------------------------------------------------------");
    }

    private void printIncidentRow(Incident inc) {
        String id = inc.getIncidentId();
        String zId = inc.getZone() != null ? inc.getZone().getZoneId() : "N/A";
        String pId = inc.getPersonId(); // Gọi helper getPersonId() từ đối tượng Person
        String desc = truncate(inc.getDescription(), 37);
        String status = inc.getStatus();
        String time = inc.getLocateDateTime();

        String colorPrefix = inc.isSaved() ? Draft.BLU : "";
        String colorReset = inc.isSaved() ? Draft.BAS : "";

        System.out.printf("%s    %-8s | %-8s | %-10s | %-40.40s | %-12s | %-19s%s%n",
                colorPrefix, id, zId, pId, desc, status, time, colorReset);
    }

    private void printTableFooter() {
        System.out.println("  --------------------------------------------------------------------------------------------------------------------");
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

    public boolean hasInProgressIncident(String zoneId) {
        if (zoneId == null || zoneId.trim().isEmpty()) {
            return false;
        }
        for (Incident inc : incidents) {
            if (inc != null && inc.getZone() != null
                    && inc.getZone().getZoneId().equalsIgnoreCase(zoneId.trim())
                    && "IN_PROGRESS".equalsIgnoreCase(inc.getStatus())) {
                return true;
            }
        }
        return false;
    }
}
