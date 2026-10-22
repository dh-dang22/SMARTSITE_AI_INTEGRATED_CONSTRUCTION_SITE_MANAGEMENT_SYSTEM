package ui_Dang_dang_sua;

import Incident.Incident;
import Incident.IncidentManager;
import Validatable.InputScanner;
import java.util.List;
import java.util.Scanner;

/** Input and navigation for the existing incident operations. */
public class IncidentMenu {
    private final Scanner sc = InputScanner.getScanner();
    private final IncidentManager manager = MenuSession.INCIDENTS;

    public void display() {
        while (true) {
            System.out.println("=== INCIDENT MANAGEMENT ===");
            System.out.println("1. Add incidents");
            System.out.println("2. Find by ID");
            System.out.println("3. Update status");
            System.out.println("4. Delete incident");
            System.out.println("5. Display all incidents");
            System.out.println("6. Find by status");
            System.out.println("7. Find by zone ID");
            System.out.println("8. Display incidents in restricted zones");
            System.out.println("0. Back");
            int choice = InputScanner.readRange(sc, "Choose [0-8]: ", 0, 8, "Enter 0-8.");
            if (choice <= 0) return;
            try {
                switch (choice) {
                    case 1:
                        MenuSession.ZONES.displayList();
                        manager.addIncident(sc, MenuSession.ZONES);
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
                    case 5: manager.displayIncidents(); break;
                    case 6: showAll(manager.searchIncidentByStatus(read("Status: "))); break;
                    case 7: showAll(manager.getIncidentsByZoneId(read("Zone ID: "))); break;
                    case 8: showAll(manager.getIncidentsInRestrictedZones()); break;
                    default: break;
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
        for (Incident incident : incidents) show(incident);
    }

    private void show(Incident incident) {
        System.out.println(incident.getIncidentId() + " | " + incident.getZone().getZoneId()
            + " | Report: " + incident.getReportId() + " | Assignee: " + incident.getAssigneeId()
            + " | " + incident.getDescription() + " | " + incident.getIncidentStatus()
            + " | " + incident.getLocateDateTime());
    }
}
