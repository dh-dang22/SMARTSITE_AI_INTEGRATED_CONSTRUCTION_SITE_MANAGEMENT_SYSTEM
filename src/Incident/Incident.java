/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Incident;
import java.time.LocalDateTime;
import Zone.Zone;
import smartsite.Validatable;

/**
 *
 * @author Minh_Khang
 */
public class Incident implements Validatable{

    private String incidentId;
    private Zone zone;
    private String reportId;
    private String assigneeId;
    private String description;
    private String incidentStatus;
    private String locateDateTime;

    public Incident() {
    }

    public Incident(String incidentId, Zone zone, String reportId, String assigneeId, String description, String incidentStatus, String locateDateTime) {
        this.setIncidentId(incidentId);
        this.setZone(zone);
        this.setReportId(reportId);
        this.setAssigneeId(assigneeId);
        this.setDescription(description);
        this.setIncidentStatus(incidentStatus);
        this.setLocateDateTime(locateDateTime);
    }

    @Override
    public boolean isValid() {
        if (isBlank(incidentId)) {
            return false;
        }
        if (zone == null || isBlank(zone.getZoneId())) {
            return false;
        }
        if (isBlank(reportId)) {
            return false;
        }
        if (isBlank(assigneeId)) {
            return false;
        }
        if (isBlank(description)) {
            return false;
        }
        return !isBlank(incidentStatus);
    }

    // Phương thức kiểm tra tiện ích tĩnh
    public static boolean checkValidIncident(Incident inc) {
        if (inc == null) {
            System.out.println("Incident object is null!");
            return false;
        }
        if (!inc.isValid()) {
            System.out.println("Incident contains invalid or empty fields!");
            return false;
        }
        return true;
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    public String getIncidentId() {
        return incidentId;
    }

    public final void setIncidentId(String incidentId) {
        if (incidentId.isEmpty()) {
            throw new IllegalArgumentException("Incident Id can not be empty");
        }
        this.incidentId = incidentId;
    }

    public Zone getZone() {
        return zone;
    }

    public final void setZone(Zone zone) {
        if (zone == null) {
            throw new IllegalArgumentException("Zone reference cannot be null!");
        }

        if (zone.getZoneId() == null || zone.getZoneId().trim().isEmpty()) {
            throw new IllegalArgumentException("Zone must have a valid Zone ID!");
        }
        this.zone = zone ;
    }

    public String getReportId() {
        return reportId;
    }

    public final void setReportId(String reportId) {
        if (reportId.isEmpty() ) {
            throw new IllegalArgumentException("Report Id can not be empty");
        }
        this.reportId = reportId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public final void setAssigneeId(String assigneeId) {
        if (assigneeId.isEmpty()) {
            throw new IllegalArgumentException("Assignee Id can not be empty");
        }
        this.assigneeId = assigneeId;
    }

    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description can not be empty");
        }
        this.description = description;
    }

    public String getIncidentStatus() {
        return incidentStatus;
    }

    public final void setIncidentStatus(String incidentStatus) {
           if (incidentStatus.isEmpty()) {
            throw new IllegalArgumentException("Incident status can not be empty");
        }
        this.incidentStatus = incidentStatus;
    }

    public String getLocateDateTime() {
        return locateDateTime;
    }

    public final void setLocateDateTime(String locateDateTime) {
        this.locateDateTime = locateDateTime;
    }

}
