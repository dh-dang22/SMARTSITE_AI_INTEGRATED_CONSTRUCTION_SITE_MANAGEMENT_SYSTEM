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
    private String assigNeeld;
    private String desCripTion;
    private String incidentStatus;
    private String locateDateTime;

    public Incident() {
    }

    public Incident(String incidentId, String zoneId, String reportId, String assigNeeld, String desCripTion, String incidentStatus, String locateDateTime) {
        this.incidentId = incidentId;
        this.zoneId = zoneId;
        this.reportId = reportId;
        this.assigNeeld = assigNeeld;
        this.desCripTion = desCripTion;
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
        return assigNeeld;
    }

    public void setAssigNeeld(String assigNeeld) {
        this.assigNeeld = assigNeeld;
    }

    public String getDesCripTion() {
        return desCripTion;
    }

    public void setDesCripTion(String desCripTion) {
        this.desCripTion = desCripTion;
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
