/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Attendance;

import Person.Person;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Zone.Zone;
import Zone.RestrictedZone;
import Zone.ZoneManager2;
//import cho phan doc file va check file attendance record
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.time.temporal.TemporalQueries.zone;
import java.util.Scanner;

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

try {
    saveToFile();
    return true;
} catch (IOException e) {
    records.remove(record);
    System.out.println("Không lưu được Attendance: " + e.getMessage());
    return false;
}
}
        public boolean checkOut(String personId) {
    AttendanceRecord rec = findOpenRecord(personId);
    if (rec == null) {
        return false;                       // chưa check-in hoặc đã check-out
    }

    LocalDateTime oldCheckOut = rec.getCheckOutTime();   // null, dùng để hoàn tác
    rec.setCheckOutTime(LocalDateTime.now());

    try {
        saveToFile();
        return true;
    } catch (IOException e) {
        rec.setCheckOutTime(oldCheckOut);   // lưu lỗi thì trả lại trạng thái cũ
        System.out.println("Không lưu được Attendance: " + e.getMessage());
        return false;
    }
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

    public void checkInFromInput(Person currentUser, Zone zm) {
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
        java.util.Scanner sc = Validatable.InputScanner.getScanner();
        if (zm instanceof RestrictedZone) {
            System.out.print("Enter zone PIN: ");
            if (!sc.hasNextLine()) {
                return;
            }
            if (!((RestrictedZone) zm).getPinCode().equals(sc.nextLine().trim())) {
                System.out.println("Incorrect PIN.");
                return;
            }
        }
        System.out.println(checkIn(currentUser, zm) ? "Check-in successful." : "Check-in failed.");
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

    public void checkInFromInput(Zone activeZone) {
        Person currentUser = ui_Dang_dang_sua.TempSession.getCurrentUser();

        if (currentUser == null) {
            System.out.println("Không tìm thấy người dùng đang đăng nhập.");
            return;
        }

        Zone zoneManager = activeZone;
       // public final ZoneManager2 zm ;
        
        checkInFromInput(currentUser, zoneManager);
    }

    public void checkOutFromInput() {
        Person currentUser = ui_Dang_dang_sua.TempSession.getCurrentUser();

        if (currentUser == null) {
            System.out.println("Không tìm thấy người dùng đang đăng nhập.");
            return;
        }

        checkOutFromInput(currentUser);
    }
    private static final String ATTENDANCE_FILE = "data/Attendance.txt";

    public void loadFromFile() throws IOException {
        Path path = Paths.get(ATTENDANCE_FILE);

        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }

        List<AttendanceRecord> loaded = new ArrayList<>();

        try ( BufferedReader reader
                = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    loaded.add(AttendanceRecord.fromFileLine(line));
                } catch (RuntimeException e) {
                    System.out.println(
                            "Bỏ qua dòng Attendance lỗi " + lineNumber
                            + ": " + e.getMessage()
                    );
                }
            }
        }

        records.clear();
        records.addAll(loaded);
    }

    private void saveToFile() throws IOException {
        Path path = Paths.get(ATTENDANCE_FILE);
        Files.createDirectories(path.getParent());

        try ( BufferedWriter writer
                = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (AttendanceRecord record : records) {
                writer.write(record.toFileLine());
                writer.newLine();
            }
        }
    }

    public void History(String personId) {
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
        System.out.println("Check-out : " + (record.isOpen()
                ? "Chua check-out"
                : record.getCheckOutTime().toString()));
        System.out.println("So phut   : " + record.calculateDurationMinutes());
    }
// Dùng khi gọi từ menu chính
public void displayAttendanceManagementMenu() {
    Person currentUser =
            ui_Dang_dang_sua.TempSession.getCurrentUser();
    if (currentUser == null) {
        System.out.println("Không tìm thấy người dùng đang đăng nhập.");
        return;
    }
    ZoneManager2 zoneManager = new ZoneManager2();
    displayAttendanceManagementMenu(currentUser, zoneManager);
}
// Dùng khi đã có sẵn người dùng và ZoneManager2
// Anh Ð có th? s? d?ng printout c?a hàm này d? lmaf vo menu 
public void displayAttendanceManagementMenu(
        Person currentUser,
        ZoneManager2 zoneManager) {
    if (currentUser == null || zoneManager == null) {
        System.out.println("Không có dữ liệu người dùng hoặc khu vực.");
        return;
    }
}
}
