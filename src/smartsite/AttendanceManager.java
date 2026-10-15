/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartsite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh_Khang
 */
public class AttendanceManager {
    private final List<AttendanceRecord> records = new ArrayList<>();
    private final String filePath="attendance.txt"; // dua duong dan chua file txt vao chuong trinh
      //B1: tim ban ghi dang mo
    /*  public boolean checkIn(Person person, Zone zone) {
    String personid = person.getid();
    for (AttendanceRecord rec : records) {
    if (rec.getPersonId().equals(personid) && rec.getCheckOutTime() == null) {
    return false;
    }
    }
    
    // TODO: Tạo bản ghi check-in mới
    return false;
    }*/
      public boolean checkIn(Person person, Zone zone) {

    if (person == null || zone == null
            || person.getId() == null
            || zone.getZoneId() == null
            || !person.isActive()) {
        return false;
    }// Tham khao AI

    String personId = person.getId();

    for (AttendanceRecord rec : records) {
        if (rec.getPersonId().equalsIgnoreCase(personId)
                && rec.isOpen()) {
            return false;
        }
    }
    // Tham khao
    String recordId = java.util.UUID.randomUUID().toString();

    AttendanceRecord record = new AttendanceRecord(
        recordId,
        personId,
        zone.getZoneId(),
        LocalDateTime.now(),
        null
    );

    records.add(record);
    return true;
}
        public boolean checkOut(String personId) {
         for (AttendanceRecord rec : records) {
             // Tìm bản ghi đúng ID người dùng và CHƯA Check-out
             if (rec.getPersonId().equalsIgnoreCase(personId) && rec.getCheckOutTime() == null) {
                 rec.setCheckOutTime(LocalDateTime.now()); // Ghi nhận giờ ra
                 return true; // Check-out thành công!
             }
         }
         return false; // Không tìm thấy lượt check-in nào đang mở
     }
}
