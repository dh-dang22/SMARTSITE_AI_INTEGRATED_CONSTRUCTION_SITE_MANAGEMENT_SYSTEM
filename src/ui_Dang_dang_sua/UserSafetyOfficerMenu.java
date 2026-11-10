package ui_Dang_dang_sua;

import Attendance.AttendanceManager;
import Incident.IncidentManager;
import Person.PersonManager;
import Validatable.CheckId;
import Validatable.InputScanner;
import java.util.Scanner;

public class UserSafetyOfficerMenu extends MenuUserLv00 {
    private final AttendanceManager attendanceManager = AttendanceManager.getInstance();
    private final Scanner sc = InputScanner.getScanner();
    private final IncidentManager manager = IncidentManager.getInstance();

    @Override
    public void displayOption() {
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
        System.out.println("    [5] Display Incident Management functions");
    }

    @Override
    public void chooseOption(int choice) {
        switch (choice) {
            case (0):
                Draft.clearConsoleIDE();
                break;
            case (1):
                new PersonManager().displayPersonInformation(currentUser);
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (2):
                attendanceManager.checkInFromInput(activeZone);
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (3):
                attendanceManager.checkOutFromInput();
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (4):
                attendanceManager.History(currentUser.getId());
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (5):
                displayIncidentManagementMenu();
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            default:
                System.out.println(Draft.center(Draft.RED + "Invalid choice! Please select again" + Draft.BAS));
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }

    /** Input and navigation for the incident management subsystem. */
    public void displayIncidentManagementMenu() {
        while (true) {
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            Draft.displayMenuLine();
            System.out.println("");
            System.out.println(Draft.center("=== INCIDENT MANAGEMENT ==="));
            System.out.println("");
            System.out.println("    [1] Add Incident(s)");
            System.out.println("    [2] Find Incident by ID");
            System.out.println("    [3] Update Incident");
            System.out.println("    [4] Delete Incident");
            System.out.println("    [5] Display all Incidents");
            System.out.println("    [6] Display Incidents Sorted by ID");
            System.out.println("    [7] Save Incidents to File");
            System.out.println("");
            System.out.println("    [0] Back to general menu");
            System.out.println("");

            int choice = InputScanner.readRange(sc, "Choose [0-7]: ", 0, 7, Draft.BLU + "    ➔ Please enter your action [0-7]: " + Draft.BAS);
            if (choice <= 0) return;

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