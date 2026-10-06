/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

import java.time.LocalDateTime;

/**
 *
 * @author Minh_Khang
 */
public class Incident {

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
