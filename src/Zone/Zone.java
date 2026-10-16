/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartsite;

/**
 *
 * @author Minh_Khang
 */
public class Zone implements Validatable {

    protected String zoneId;
    protected String zoneName;
    protected String zoneDetails;

    public Zone() {
    }

    public Zone(String zoneId, String zoneName, String zoneDetails) {
        this.setZoneId(zoneId);
        this.setZoneName(zoneName);
        this.setZoneDetails(zoneDetails);
    }

    @Override
    public boolean isValid() {
        if (zoneId == null || !zoneId.trim().matches("^Z\\d{3}$")) {
            return false;
        }
        if (zoneName == null || zoneName.trim().isEmpty()) {
            return false;
        }
        if (zoneDetails == null || zoneDetails.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public String getZoneId() {
        return zoneId;
    }

    public final void setZoneId(String zoneId) {
        if (zoneId == null || zoneId.trim().isEmpty()) {
            throw new IllegalArgumentException("Zone id cannot be empty.");
        }
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public final void setZoneName(String zoneName) {
        if (zoneName == null || zoneName.trim().isEmpty()) {
            throw new IllegalArgumentException("Zone name cannot be empty.");
        }
        this.zoneName = zoneName;
    }

    public String getZoneDetails() {
        return zoneDetails;
    }

    public final void setZoneDetails(String zoneDetails) {
        if (zoneDetails == null || zoneDetails.trim().isEmpty()) {
            throw new IllegalArgumentException("Zone details cannot be empty.");
        }
        this.zoneDetails = zoneDetails;
    }

    public boolean isAccessRestricted() {
        return false;
    }

    @Override
    public String toString() {
        return "Zone[" + "zoneId=" + zoneId + ", zoneName=" + zoneName + ", zoneDetails=" + zoneDetails + ']';
    }

}
