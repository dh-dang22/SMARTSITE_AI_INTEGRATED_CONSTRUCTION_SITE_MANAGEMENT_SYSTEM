package Incident;
import Zone.RestrictedZone;
import Zone.Zone;
import Zone.ZoneManager2;
import Validatable.CheckZoneId;
import Validatable.CheckId;
import Validatable.CheckDetails;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncidentManager {

    private final List<Incident> incidents;

    public IncidentManager() {
        this.incidents = new ArrayList<>();
    }

    public void addIncident(ZoneManager2 zoneManager) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number of incidents to add: ");
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
            System.out.println("\n--- Incident [" + i + "] ---");

            String incidentId;
            while (true) {
                incidentId = CheckId.checkIncidentId();
                if (searchIncidentById(incidentId) != null) {
                    System.out.println("[Error]: Incident ID [" + incidentId + "] already exists! Please try again.");
                    continue;
                }
                break;
            }

            Zone selectedZone = null;
            while (true) {
                String zoneId = CheckZoneId.checkZoneId();
                selectedZone = zoneManager.findZoneById(zoneId);

                if (selectedZone == null) {
                    System.out.println("[Error]: Zone ID [" + zoneId + "] not found in system! Please try again.");
                    continue;
                }
                break;
            }

            String reportId = CheckId.checkReportId();
            String assigneeId = CheckId.checkAssigneeId();
            String description = CheckDetails.checkDetails();
            String status = CheckDetails.checkIncidentStatus();

            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            try {
                Incident inc = new Incident(incidentId, selectedZone, reportId, assigneeId, description, status, currentDateTime);

                if (inc.isValid()) {
                    incidents.add(inc);
                    System.out.println("-> Add Incident [" + incidentId + "] successfully!");
                } else {
                    System.out.println("[Error]: Failed to add Incident [" + incidentId + "]: Invalid object state!");
                    i--;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Error]: " + e.getMessage() + "! Try again.");
                i--;
            }
        }
    }

    public boolean addIncident(Incident incident) {
        if (incident == null || !incident.isValid()) {
            return false;
        }
        if (searchIncidentById(incident.getIncidentId()) != null) {
            return false; // Đã tồn tại ID
        }
        incidents.add(incident);
        return true;
    }

    public boolean updateIncidentStatus(String incidentId, String status) {
        Incident inc = searchIncidentById(incidentId);
        if (inc != null) {
            try {
                inc.setIncidentStatus(status);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println("[Error]: Update failed! " + e.getMessage());
            }
        }
        return false;
    }

    public boolean deleteIncident(String incidentId) {
        Incident inc = searchIncidentById(incidentId);
        if (inc != null) {
            incidents.remove(inc);
            return true;
        }
        return false;
    }

    public Incident searchIncidentById(String incidentId) {
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

    public List<Incident> searchIncidentByStatus(String status) {
        List<Incident> result = new ArrayList<>();
        if (status == null) {
            return result;
        }
        for (Incident inc : incidents) {
            if (inc.getIncidentStatus() != null && inc.getIncidentStatus().equalsIgnoreCase(status.trim())) {
                result.add(inc);
            }
        }
        return result;
    }

    public void displayIncidents() {
        if (incidents.isEmpty()) {
            System.out.println("No incidents recorded.");
            return;
        }
        System.out.println("\n=== INCIDENT LIST ===");
        for (Incident inc : incidents) {
            System.out.println("ID: " + inc.getIncidentId() + " | Zone: " + (inc.getZone() != null ? inc.getZone().getZoneId() : "N/A") + " | Status: " + inc.getIncidentStatus()
                    + " | Desc: " + inc.getDescription()
                    + " | Time: " + inc.getLocateDateTime());
        }
    }

    public List<Incident> getIncidentsByZoneId(String zoneId) {
        List<Incident> result = new ArrayList<>();
        if (zoneId == null) {
            return result;
        }
        for (Incident inc : incidents) {
            if (inc.getZone() != null && inc.getZone().getZoneId().equalsIgnoreCase(zoneId.trim())) {
                result.add(inc);
            }
        }
        return result;
    }

    public List<Incident> getIncidentsInRestrictedZones() {
        List<Incident> result = new ArrayList<>();
        for (Incident inc : incidents) {
            if (inc.getZone() instanceof RestrictedZone) {
                result.add(inc);
            }
        }
        return result;
    }

    public void addIncident(Scanner sc, ZoneManager2 ZONES) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
