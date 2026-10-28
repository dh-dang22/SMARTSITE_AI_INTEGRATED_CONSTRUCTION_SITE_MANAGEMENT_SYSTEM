/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Attendance;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
    // Method in file ra 
    public static AttendanceRecord fromFileLine(String line) {
    String[] fields = line.split("\\|", -1);

    if (fields.length != 5) {
        throw new IllegalArgumentException("Attendance row must contain 5 fields.");
    }

    LocalDateTime checkIn = LocalDateTime.parse(fields[3]);
    LocalDateTime checkOut =
            fields[4].trim().isEmpty()
                    ? null
                    : LocalDateTime.parse(fields[4]);

    return new AttendanceRecord(
            fields[0].trim(),
            fields[1].trim(),
            fields[2].trim(),
            checkIn,
            checkOut
    );
}
}

