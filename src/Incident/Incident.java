package Incident;

import Zone.Zone;
import Person.Person;
import Validatable.Validatable;

public class Incident implements Validatable {

    private String incidentId;
    private Zone zone;
    private Person person;
    private String description;
    private String status;
    private String locateDateTime;
    private boolean isSaved;

    public Incident() {
        this.isSaved = false;
    }

    public Incident(String incidentId, Zone zone, Person person, String description, String status, String locateDateTime, boolean isSaved) {
        this.setIncidentId(incidentId);
        this.setZone(zone);
        this.setPerson(person);
        this.setDescription(description);
        this.setStatus(status);
        this.setLocateDateTime(locateDateTime);
        this.isSaved = isSaved;
    }

    public Incident(String incidentId, Zone zone, Person person, String description, String status, String locateDateTime) {
        this(incidentId, zone, person, description, status, locateDateTime, false);
    }

    @Override
    public boolean isValid() {
        if (isBlank(incidentId) || !incidentId.trim().matches("^IN\\d{4}$")) {
            return false;
        }
        if (zone == null || isBlank(zone.getZoneId())) {
            return false;
        }
        if (person == null || isBlank(person.getId())) {
            return false;
        }
        if (isBlank(description)) {
            return false;
        }
        return !isBlank(status);
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public String getIncidentId() {
        return incidentId;
    }

    public final void setIncidentId(String incidentId) {
        if (isBlank(incidentId)) {
            throw new IllegalArgumentException("Incident ID cannot be empty!");
        }
        this.incidentId = incidentId;
    }

    public Zone getZone() {
        return zone;
    }

    public final void setZone(Zone zone) {
        if (zone == null || isBlank(zone.getZoneId())) {
            throw new IllegalArgumentException("Zone reference cannot be null or empty!");
        }
        this.zone = zone;
    }

    public Person getPerson() {
        return person;
    }

    // Helper lấy trực tiếp Person ID ra cho hiển thị và lưu file
    public String getPersonId() {
        return (person != null) ? person.getId() : "N/A";
    }

    public final void setPerson(Person person) {
        if (person == null || isBlank(person.getId())) {
            throw new IllegalArgumentException("Person reference cannot be null or empty!");
        }
        this.person = person;
    }

    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        if (isBlank(description)) {
            throw new IllegalArgumentException("Description cannot be empty!");
        }
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public final void setStatus(String status) {
        if (isBlank(status)) {
            throw new IllegalArgumentException("Status cannot be empty!");
        }
        this.status = status;
    }

    public String getLocateDateTime() {
        return locateDateTime;
    }

    public final void setLocateDateTime(String locateDateTime) {
        this.locateDateTime = locateDateTime;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    @Override
    public String toString() {
        return "IncidentId: " + incidentId 
                + " | ZoneId: " + (zone != null ? zone.getZoneId() : "N/A")
                + " | PersonId: " + getPersonId() 
                + " | Status: " + status 
                + " | Time: " + locateDateTime;
    }

    public String toFileString() {
        String zoneId = (zone != null) ? zone.getZoneId() : "";
        String pId = getPersonId();
        return incidentId + "|" + zoneId + "|" + pId + "|" + description + "|" + status + "|" + locateDateTime;
    }
}