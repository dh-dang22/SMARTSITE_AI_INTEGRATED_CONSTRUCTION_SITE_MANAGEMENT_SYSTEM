package smarttite;

import java.util.ArrayList;
import java.util.List;

/**
 * Quản lý danh sách sự cố (Incident)
 *
 * @author Minh_Khang
 */
public class IncidentManager {

    private final List<Incident> incidents;

    public IncidentManager() {
        this.incidents = new ArrayList<>();
    }

    public boolean addIncident(Incident incident) {
        if (incident == null || incident.getIncidentId() == null) {
            return false;
        }
        incidents.add(incident);
        return true;
    }

    public boolean updateIncidentStatus(String incidentId, String status) {
        Incident inc = searchIncidentById(incidentId);
        if (inc != null) {
            inc.setIncidentStatus(status);
            return true;
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
            System.out.println("ID: " + inc.getIncidentId() + " | Zone: " + inc.getZoneId()
                    + " | Status: " + inc.getIncidentStatus() + " | Desc: " + inc.getDesCripTion());
        }
    }
}
