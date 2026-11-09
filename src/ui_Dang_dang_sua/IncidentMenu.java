package ui_Dang_dang_sua;

import Incident.IncidentManager;
import Validatable.CheckId;
import Validatable.InputScanner;
import java.util.Scanner;

/**
 * Input and navigation for incident operations.
 */
public class IncidentMenu {

    private final Scanner sc = InputScanner.getScanner();
    private final IncidentManager manager = IncidentManager.getInstance();
    private static IncidentMenu instance;

    public static IncidentMenu getInstance() {
        if (instance == null) {
            instance = new IncidentMenu();
        }
        return instance;
    }

    public void display() {
        while (true) {
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            Draft.displayMenuLine();
            System.out.println("");
            System.out.println(Draft.center("=== INCIDENT MANAGEMENT ==="));
            System.out.println("");
            System.out.println("    [1] Add Incidents");
            System.out.println("    [2] Find Incident by ID");
            System.out.println("    [3] Update Incident");
            System.out.println("    [4] Delete Incident");
            System.out.println("    [5] Display all Incidents");
            System.out.println("    [6] Display Incidents Sorted by ID");
            System.out.println("    [7] Save Incidents to File");
            System.out.println("");
            System.out.println("    [0] Back");
            System.out.println("");

            int choice = InputScanner.readRange(sc, "    Choose [0-7]: ", 0, 7, Draft.BLU + "    ➔ Please enter your action [0-7]: " + Draft.BAS);
            if (choice == 0) {
                Draft.clearConsoleIDE();
                return;
            }

            try {
                switch (choice) {
                    case 1:
                        manager.addIncident();
                        break;
                    case 2:
                        String searchId = CheckId.checkIncidentId();
                        manager.findIncidentById(searchId);
                        break;
                    case 3:
                        manager.updateIncidentById();
                        break;
                    case 4:
                        manager.deleteIncidentById();
                        break;
                    case 5:
                        manager.displayIncidents();
                        break;
                    case 6:
                        manager.sortIncidentById();
                        break;
                    case 7:
                        manager.saveToFile();
                        break;
                    default:
                        break;
                }
            } catch (java.util.NoSuchElementException e) {
                return;
            }

            Draft.displayMenuLine();
            Draft.pause();
            Draft.clearConsoleIDE();
        }
    }
}
