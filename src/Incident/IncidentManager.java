package Incident;

import Zone.RestrictedZone;
import Zone.Zone;
import Zone.ZoneManager2; 
import smartsite.CheckInput;

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

   
    public void addIncident(Scanner sc, ZoneManager2 zoneManager) {
        int n = CheckInput.readRange(
            sc, 
            "Input number of incidents to add: ", 
            1, 
            100, 
            "The number must be greater than 0!"
        );

        if (n == -1) return; // EOF check

        for (int i = 1; i <= n; i++) {
            System.out.println("--- Incident [" + i + "] ---");

            // 1. Nhập Incident ID chuẩn dạng ICxxx và kiểm tra trùng
            String incidentId;
            while (true) {
                incidentId = CheckInput.readPattern(
                    sc, 
                    "Enter Incident ID [ICxxx]: ", 
                    "^IC\\d{3}$", 
                    "Incident ID must follow format [ICxxx]!"
                );
                if (incidentId.isEmpty()) return;

                if (searchIncidentById(incidentId) != null) {
                    System.out.println("Incident ID already exists! Try again.");
                    continue;
                }
                break;
            }

           
            Zone selectedZone = null;
            while (true) {
                String zoneId = CheckInput.readPattern(sc, "Enter Zone ID for this incident [Zxxx/RZxxx]: ", "^(Z|RZ)\\d{3}$",  "Zone ID must follow format [Zxxx] or [RZxxx]!");
                if (zoneId.isEmpty()) return;

                selectedZone = zoneManager.findZoneById(zoneId);
                
                if (selectedZone == null) {
                    System.out.println("Zone ID not found in system! Try again.");
                    continue;
                }
                break;
            }

            // 3. Nhập Report ID, Assignee ID, Description, Status
            String reportId = CheckInput.checkEmpty(sc, "Enter Report ID: ", "Report ID can not be empty!");
            if (reportId.isEmpty()) return;

            String assigneeId = CheckInput.checkEmpty(sc, "Enter Assignee ID: ", "Assignee ID can not be empty!");
            if (assigneeId.isEmpty()) return;

            String description = CheckInput.checkEmpty(sc, "Enter Description: ", "Description can not be empty!");
            if (description.isEmpty()) return;

            String status = CheckInput.checkEmpty(sc, "Enter Status (e.g. OPEN, IN_PROGRESS, RESOLVED): ", "Status can not be empty!");
            if (status.isEmpty()) return;

            // Tự động lấy thời gian hiện tại
            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            try {
                Incident inc = new Incident(incidentId, selectedZone, reportId, assigneeId, description, status, currentDateTime);
                
                // Kiểm tra isValid trước khi lưu
                if (inc.isValid()) {
                    incidents.add(inc);
                    System.out.println("Add Incident [" + incidentId + "] successfully!");
                } else {
                    System.out.println("Failed: Incident data is invalid!");
                    i--;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                i--;
            }
        }
    }

    // Hàm addIncident truyền đối tượng trực tiếp
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
                System.out.println("Update failed: " + e.getMessage());
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
        if (incidentId == null) return null;
        for (Incident inc : incidents) {
            if (inc.getIncidentId().equalsIgnoreCase(incidentId.trim())) {
                return inc;
            }
        }
        return null;
    }

    public List<Incident> searchIncidentByStatus(String status) {
        List<Incident> result = new ArrayList<>();
        if (status == null) return result;
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
        System.out.println("=== INCIDENT LIST ===");
        for (Incident inc : incidents) {
            System.out.println("ID: " + inc.getIncidentId() + " | Zone: " + inc.getZone().getZoneId()+ " | Status: " + inc.getIncidentStatus() + " | Desc: " + inc.getDescription()+ " | Time: " + inc.getLocateDateTime());
        }
    }

    public List<Incident> getIncidentsByZoneId(String zoneId) {
        List<Incident> result = new ArrayList<>();
        if (zoneId == null) return result;
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
}