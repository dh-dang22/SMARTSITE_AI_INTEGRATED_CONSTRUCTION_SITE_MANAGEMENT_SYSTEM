package Zone;

import java.io.IOException;
import java.util.Scanner;

public class MainFake {

    public static void main(String[] args) {
        // Lấy Instance quản lý ZoneManager2 (Tự động gọi loadFromFile trong Constructor)
        ZoneManager2 zm = new ZoneManager2();

        System.out.println("==================================================");
        System.out.println("   AUTOMATED TEST & FILE I/O TESTING PROGRAM");
        System.out.println("==================================================");

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== ZONE MANAGER TEST MENU =====");
            System.out.println("[1] Add Regular Zone (addZone)");
            System.out.println("[2] Add Restricted Zone (addRestrictedZone)");
            System.out.println("[3] Find Zone by ID (findZoneById)");
            System.out.println("[4] Update Zone by ID (updateZoneById)");
            System.out.println("[5] Delete Zone by ID (deleteZoneById)");
            System.out.println("[6] Display ALL Zones (displayList)");
            System.out.println("[7] Display Restricted Zones Only (displayRestrictedZonesOnly)");
            System.out.println("[8] SAVE Data to File (saveToFile)");
            System.out.println("[9] LOAD Data from File (loadFromFile)");
            System.out.println("[10] Sort Zone by Id ");
            System.out.println("[0] Exit Program");
            System.out.print("➔ Choose option [0-9]: ");

            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.println("\n--- [TEST]: Add Regular Zone ---");
                    zm.addZone();
                    break;

                case "2":
               
                       System.out.println("\n--- [TEST]: Add Restricted Zone ---");
                    zm.addRestrictedZone();
                    break;

                case "3":
                    System.out.println("\n--- [TEST]: Find Zone by ID ---");
                    System.out.print("Enter ZoneID to search (e.g., ZN0001): ");
                    String searchId = sc.nextLine().trim();
                    Zone foundZone = zm.findZoneById(searchId);
                    if (foundZone != null) {
                        System.out.println("-> Found: " + foundZone);
                        System.out.println("-> [isRestricted]: " + foundZone.isRestricted());
                        System.out.println("-> [toFileString Format]: " + foundZone.toFileString());
                    } else {
                        System.out.println("[!] ZoneID [" + searchId + "] not found.");
                    }
                    break;

                case "4":
                    System.out.println("\n--- [TEST]: Update Zone ---");
                    zm.updateZoneById();
                    break;

                case "5":
                    System.out.println("\n--- [TEST]: Delete Zone ---");
                    zm.deleteZoneById();
                    break;

                case "6":
                    System.out.println("\n--- [TEST]: Display All Zones ---");
                    zm.displayList();
                    break;

                case "7":
                    System.out.println("\n--- [TEST]: Display Restricted Zones Only ---");
                    zm.displayRestrictedZonesOnly();
                    break;

                case "8":
                    System.out.println("\n--- [TEST]: Save Data to File ---");
                    zm.saveToFile();
                    break;

                case "9":
                    System.out.println("\n--- [TEST]: Load Data from File ---");
                    try {
                        zm.loadFromFile("Zone6"
                                + ".txt");
                    } catch (IOException e) {
                        System.out.println("[Error]: Failed to load file: " + e.getMessage());
                    }
                    break;
                case "10":
                    System.out.println("Sort Zone");
                    zm.sortZonesById();
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting test program. Goodbye!");
                    break;

                default:
                    System.out.println("[Error]: Invalid option!");
                    break;
            }
        }
        sc.close();
    }
}