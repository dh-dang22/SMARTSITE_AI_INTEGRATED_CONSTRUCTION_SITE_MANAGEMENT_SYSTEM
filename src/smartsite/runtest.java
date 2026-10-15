package smartsite;

import java.util.Scanner;


public class runtest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ZoneManager2 zoneManager = new ZoneManager2();
        int choice = -1;

        do {
            System.out.println("\n==========================================");
            System.out.println("      SMARTSITE - ZONE MANAGEMENT          ");
            System.out.println("==========================================");
            System.out.println("1. Add Normal Zone(s)");
            System.out.println("2. Add Restricted Zone(s)");
            System.out.println("3. Display All Zones");
            System.out.println("4. Display Restricted Zones Only");
            System.out.println("5. Find Zone By ID");
            System.out.println("6. Update Zone By ID");
            System.out.println("7. Delete Zone By ID");
            System.out.println("0. Exit Application");
            System.out.println("==========================================");
            System.out.print("Choose an option (0-7): ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number from 0 to 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- ADD NORMAL ZONE ---");
                    zoneManager.addZone(sc);
                    break;

                case 2:
                    System.out.println("\n--- ADD RESTRICTED ZONE ---");
                    zoneManager.addRestrictedZone(sc);
                    break;

                case 3:
                    System.out.println("\n--- ALL ZONES LIST ---");
                    zoneManager.displayList();
                    break;

                case 4:
                    System.out.println("\n--- RESTRICTED ZONES LIST ---");
                    zoneManager.displayRestrictedZonesOnly();
                    break;

                case 5:
                    System.out.println("\n--- FIND ZONE BY ID ---");
                    System.out.print("Enter Zone ID to search (e.g., Z001 or RZ001): ");
                    String searchId = sc.nextLine().trim();
                    Zone foundZone = zoneManager.findZoneById(searchId);
                    if (foundZone != null) {
                        System.out.println("Found: " + foundZone);
                    } else {
                        System.out.println("Zone with ID [" + searchId + "] not found!");
                    }
                    break;

                case 6:
                    System.out.println("\n--- UPDATE ZONE BY ID ---");
                    System.out.print("Enter Zone ID to update: ");
                    String updateId = sc.nextLine().trim();
                    Zone existingZone = zoneManager.findZoneById(updateId);
                    
                    if (existingZone != null) {
                        System.out.println("Current Info: " + existingZone);
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine().trim();
                        System.out.print("Enter New Description: ");
                        String newDesc = sc.nextLine().trim();

                        boolean updated = zoneManager.updateZoneById(updateId, newName, newDesc);
                        if (updated) {
                            System.out.println("Updated Zone [" + updateId + "] successfully!");
                        }
                    } else {
                        System.out.println("Zone ID not found!");
                    }
                    break;

                case 7:
                    System.out.println("\n--- DELETE ZONE BY ID ---");
                    System.out.print("Enter Zone ID to delete: ");
                    String deleteId = sc.nextLine().trim();
                    boolean deleted = zoneManager.deleteZoneById(deleteId);
                    if (deleted) {
                        System.out.println("Deleted Zone [" + deleteId + "] successfully!");
                    }
                    break;

                case 0:
                    System.out.println("\nExiting SmartSite Zone Manager. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option! Please choose between 0 and 7.");
            }

        } while (choice != 0);

        sc.close();
    }
}