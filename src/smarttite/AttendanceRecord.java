/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarttite;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author Minh_Khang
 */
public class AttendanceRecord {

    private final String recordId;
    private final String personId;
    private final String zoneId;
    private final LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public AttendanceRecord(String recordId, String personId, String zoneId,
                            LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.recordId = recordId;
        this.personId = personId;
        this.zoneId = zoneId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getPersonId() {
        return personId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public boolean isOpen() {
        return checkOutTime == null;
    }

    public void markCheckOut(LocalDateTime time) {
        this.checkOutTime = time;
    }

    public long calculateDurationMinutes() {
        LocalDateTime end = (checkOutTime == null) ? LocalDateTime.now() : checkOutTime;
        return Duration.between(checkInTime, end).toMinutes();
    }

    public String toFileLine() {
        return String.join("|",
                recordId,
                personId,
                zoneId,
                checkInTime.toString(),
                checkOutTime == null ? "" : checkOutTime.toString());
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" + "recordId=" + recordId + ", personId=" + personId
                + ", zoneId=" + zoneId + ", checkInTime=" + checkInTime
                + ", checkOutTime=" + checkOutTime + '}';
    }
}

