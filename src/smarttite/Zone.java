/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

/**
 *
 * @author Minh_Khang
 */
public class Zone {
    private String zoneId;
    private String zoneName;
    private String zoneDetails;
    public Zone(){}
    public Zone(String zoneId, String zoneName, String zoneDetails) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.zoneDetails = zoneDetails;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
         if (zoneId == null || zoneId.trim().isEmpty()) {
            throw new IllegalArgumentException("Zone id cannot be empty.");
        }
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
         if (zoneDetails == null || zoneDetails.trim().isEmpty()) {
            throw new IllegalArgumentException("Zone details cannot be empty.");
        }
        this.zoneDetails = zoneDetails;
    }

    public String getZoneDetails() {
        return zoneDetails;
    }

    public void setZoneDetails(String zoneDetails) {
        this.zoneDetails = zoneDetails;
    }
    
}
