package ui_Dang_dang_sua;

import Incident.Incident;
import Incident.IncidentManager;
import Validatable.InputScanner;
import java.util.List;
import java.util.Scanner;

public class UserSafetyOfficerMenu extends MenuUserLv00{

    @Override
    public void displayOption(){
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
        System.out.println("    [5] Display Incident Management functions");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
                break;
            case (1):
                UniqueSiteManagerMenu ht1 = new UniqueSiteManagerMenu();
                ht1.displayPersonInformation(currentUser);
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (2):
                Draft.functionIsNotCompletedYet();
                break;
            case (3):
                Draft.functionIsNotCompletedYet();
                break;
            case (4):
                Draft.functionIsNotCompletedYet();
                break;            case (5):
                UserSafetyOfficerMenu ht5 = new UserSafetyOfficerMenu();
                ht5.displayIncidentManagementMenu();
                break;
            default:
                System.out.println(Draft.center(Draft.RED+"Invalid choice! Please select again"+Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();        }
    }


/** Input and navigation for the existing incident operations. */
   private final Scanner sc = InputScanner.getScanner();
   private final IncidentManager manager = IncidentManager.getInstance();
   
   //ham hien thi 
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
            System.out.println("    [1]  Add Incident(s)");
            System.out.println("    [2]  Find Incident by ID");
            System.out.println("    [3]  Update Incident status");
            System.out.println("    [4]  Delete Incident");
            System.out.println("    [5]  Display all Incidents");
            System.out.println("    [6]  Find Incident(s) by status");
            System.out.println("    [7]  Find Incident by Zone ID");
            System.out.println("    [8]  Display Incident(s) in Restricted Zones");
            System.out.println("");
            System.out.println("    [0]  Back to general menu");
            System.out.println("");
            int choice = InputScanner.readRange(sc, "Choose [0-8]: ", 0, 8, Draft.BLU+"    ➔ Please enter your action[0-8]: "+Draft.BAS);
            if (choice <= 0) return;
            try {
                switch (choice) {
                    case 1:
                        Draft.functionIsNotCompletedYet();
                        break;
                    case 2:
                        Incident incident = manager.searchIncidentById(read("Incident ID: "));
                        if (incident == null) System.out.println("Incident not found.");
                        else show(incident);
                        break;
                    case 3:
                        String id = read("Incident ID: ");
                        String status = read("Status [OPEN/IN_PROGRESS/RESOLVED]: ").toUpperCase(java.util.Locale.ROOT);
                        if (!status.matches("OPEN|IN_PROGRESS|RESOLVED")) {
                            System.out.println("Invalid status.");
                            break;
                        }
                        System.out.println(manager.updateIncidentStatus(id, status) ? "Updated." : "Update failed.");
                        break;
                    case 4:
                        System.out.println(manager.deleteIncident(read("Incident ID: ")) ? "Deleted." : "Incident not found.");
                        break;
                    case 5: 
                        manager.displayIncidents(); 
                        break;
                    case 6: 
                        showAll(manager.searchIncidentByStatus(read("Status: "))); 
                    break;
                    case 7: 
                        showAll(manager.getIncidentsByZoneId(read("Zone ID: "))); 
                        break;
                    case 8: 
                        showAll(manager.getIncidentsInRestrictedZones()); 
                        break;
                    default: 
                        break;
                }
            } catch (java.util.NoSuchElementException e) {
                return;
            }
            Draft.pause();
            Draft.clearConsoleIDE();
        }
    }

    private String read(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private void showAll(List<Incident> incidents) {
        if (incidents.isEmpty()) System.out.println("No matching incidents.");
        for (Incident incident : incidents) {
            show(incident);
        }
    }

    private void show(Incident incident) {
        System.out.println(incident.getIncidentId() + " | " + incident.getZone().getZoneId()
            + " | Report: " + incident.getReportId() + " | Assignee: " + incident.getAssigneeId()
            + " | " + incident.getDescription() + " | " + incident.getIncidentStatus()
            + " | " + incident.getLocateDateTime());
    }
}


