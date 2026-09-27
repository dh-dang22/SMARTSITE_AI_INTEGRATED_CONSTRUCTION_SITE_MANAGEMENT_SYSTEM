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
    private String zoneId;
    private String reportId;
    private String assigneeID;
    private String description;
    private String incidentStatus;
    private String locateDateTime;

    public Incident() {
    }

    public Incident(String incidentId, String zoneId, String reportId, String assigneeID, String description, String incidentStatus, String locateDateTime) {
        this.incidentId = incidentId;
        this.zoneId = zoneId;
        this.reportId = reportId;
        this.assigneeID = assigneeID;
        this.description = description;
        this.incidentStatus = incidentStatus;
        this.locateDateTime = locateDateTime;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getAssigNeeld() {
        return assigneeID;
    }

    public void setAssigNeeld(String assigneeID) {
        this.assigneeID = assigneeID;
    }

    public String getDesCripTion() {
        return description;
    }

    public void setDesCripTion(String description) {
        this.description = description;
    }

    public String getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(String incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    public String getLocateDateTime() {
        return locateDateTime;
    }

    public void setLocateDateTime(String locateDateTime) {
        this.locateDateTime = locateDateTime;
    }
    
}
