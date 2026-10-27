/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Attendance;

import Person.Person;
import Person.PersonManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Zone.Zone;
import Zone.RestrictedZone;
import Zone.ZoneManager2;

/**
 *
 * @author Minh_Khang
 */
public class AttendanceManager {
    private static AttendanceManager instance;
    private final List<AttendanceRecord> records = new ArrayList<>();
    // Khai báo instance giong nhu ben class person
    public static AttendanceManager getInstance() {
        if (instance == null) {
            instance = new AttendanceManager();
        }
        return instance;
    }
      public boolean checkIn(Person person, Zone zone) {

    if (person == null || zone == null
            || person.getId() == null
            || zone.getZoneId() == null
            || !person.isActive()) {
        return false;
    }// Tham khao AI
    if (findOpenRecord(person.getId()) != null) {
        return false;
    }
    AttendanceRecord record = new AttendanceRecord(
        java.util.UUID.randomUUID().toString(),
        person.getId(),
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
        public AttendanceRecord findOpenRecord(String personId) {
    if (personId == null || personId.trim().isEmpty()) {
        return null;
    }

    for (AttendanceRecord record : records) {
        if (record.getPersonId().equalsIgnoreCase(personId.trim())
                && record.isOpen()) {
            return record;
        }
    }

    return null;
    }
    public void checkInFromInput(Person currentUser, ZoneManager2 zm) {
    if (currentUser == null || zm == null
            || currentUser.getId() == null
            || currentUser.getId().trim().isEmpty()) {
        System.out.println("Khong co du lieu nguoi dung hoac khu vuc!");
        return;
    }

    if (!currentUser.isActive()) {
        System.out.println("Tai khoan khong hoat dong!");
        return;
    }

    if (findOpenRecord(currentUser.getId()) != null) {
        System.out.println("Ban da check-in va chua check-out!");
        return;
    }

    zm.displayList();

    java.util.Scanner sc = Validatable.InputScanner.getScanner();
    System.out.print("Enter Zone ID: ");
    if (!sc.hasNextLine()) return;
    Zone zone = zm.findZoneById(sc.nextLine().trim());
    if (zone == null) {
        System.out.println("Zone not found.");
        return;
    }
    if (zone instanceof RestrictedZone) {
        System.out.print("Enter zone PIN: ");
        if (!sc.hasNextLine()) return;
        if (!((RestrictedZone) zone).getPinCode().equals(sc.nextLine().trim())) {
            System.out.println("Incorrect PIN.");
            return;
        }
    }
    System.out.println(checkIn(currentUser, zone) ? "Check-in successful." : "Check-in failed.");
 }
 public void checkOutFromInput(Person currentUser) {
 if (currentUser == null
 || currentUser.getId() == null
 || currentUser.getId().trim().isEmpty()) {
 System.out.println("Khong xac dinh duoc nguoi dung!");
 return;
 }
 
 if (checkOut(currentUser.getId())) {
 System.out.println("Check-out thanh cong!");
 } else {
 System.out.println("Ban chua check-in hoac da check-out!");
 }
 }
 public void displayHistory(String personId) {
 if (personId == null || personId.trim().isEmpty()) {
 System.out.println("Person ID khong hop le!");
 return;
 }
 
 boolean found = false;
 System.out.println("\n--- LICH SU DIEM DANH: " + personId + " ---");
 
 for (AttendanceRecord record : records) {
 if (record.getPersonId().equalsIgnoreCase(personId.trim())) {
 displayRecord(record);
 found = true;
 }
 }
 
 if (!found) {
 System.out.println("Chua co lich su diem danh!");
 }
 }
 public void displayAll() {
 System.out.println("\n--- TAT CA LICH SU DIEM DANH ---");
 
 if (records.isEmpty()) {
 System.out.println("Danh sach diem danh rong!");
 return;
 }
 
 for (AttendanceRecord record : records) {
 displayRecord(record);
 }
 }
 private void displayRecord(AttendanceRecord record) {
 System.out.println("----------------------------------------");
 System.out.println("Record ID : " + record.getRecordId());
 System.out.println("Person ID : " + record.getPersonId());
 System.out.println("Zone ID   : " + record.getZoneId());
 System.out.println("Check-in  : " + record.getCheckInTime());
 System.out.println("Check-out : " + (
 record.isOpen()
 ? "Chua check-out"
 : record.getCheckOutTime().toString()
 ));
 System.out.println("So phut   : " + record.calculateDurationMinutes());
 }

}
