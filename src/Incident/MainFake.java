package Incident;

import Incident.IncidentManager;
import Zone.ZoneManager2;
import Person.PersonManager;
import java.io.IOException;

public class MainFake {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       TESTING INCIDENT MANAGER SYSTEM");
        System.out.println("==================================================");

        // 1. Khởi tạo và nạp dữ liệu cho các Singleton Manager phụ thuộc trước
        try {
            ZoneManager2.getInstance().loadFromFile("data/Zone.txt");
            PersonManager.getInstance().loadFromFile("data/Person.txt");
            System.out.println("[System]: Successfully pre-loaded Zone and Person data into memory!");
        } catch (IOException e) {
            System.out.println("[Notice]: Pre-load warning: " + e.getMessage());
        }

        // 2. Lấy Singleton của IncidentManager (đã tự đọc file data/Incident.txt)
        IncidentManager incidentManager = IncidentManager.getInstance();

        // ----------------------------------------------------------------
        // TEST 1: Hiển thị danh sách ban đầu (Các dòng đọc từ file có màu xanh)
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 1: DISPLAY ALL INCIDENTS FROM FILE");
        incidentManager.displayIncidents();
        PersonManager pm = PersonManager.getInstance();
        ZoneManager2 zm = ZoneManager2.getInstance();
        pm.displayAllByGehihi();
        zm.displayAllZones();
        // ----------------------------------------------------------------
        // TEST 2: Thêm mới Incident (Nhập ID dạng INxxxx, ZNxxxx, PRxxxx)
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 2: ADD NEW INCIDENT(S)");
        incidentManager.addIncident();

        // ----------------------------------------------------------------
        // TEST 3: Hiển thị lại danh sách sau khi thêm (Dữ liệu mới có màu đen)
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 3: DISPLAY AFTER ADDING");
        incidentManager.displayIncidents();

        // ----------------------------------------------------------------
        // TEST 4: Sắp xếp theo Incident ID (Chỉ in bản sao View, không đổi thứ tự gốc)
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 4: TEMPORARY SORTED VIEW BY ID");
        incidentManager.sortIncidentById();

        // ----------------------------------------------------------------
        // TEST 5: Tìm kiếm Incident theo ID (Hiển thị dạng BẢNG)
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 5: FIND INCIDENT BY ID");
        incidentManager.findIncidentById("IN0001");

        // ----------------------------------------------------------------
        // TEST 6: Cập nhật thông tin Incident theo ID
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 6: UPDATE INCIDENT BY ID");
        incidentManager.updateIncidentById();

        // ----------------------------------------------------------------
        // TEST 7: Xóa Incident theo ID (Có hỏi xác nhận Y/N)
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 7: DELETE INCIDENT BY ID");
        incidentManager.deleteIncidentById();

        // ----------------------------------------------------------------
        // TEST 8: Lưu dữ liệu xuống file data/Incident.txt
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 8: SAVE TO FILE");
        incidentManager.saveToFile();

        // ----------------------------------------------------------------
        // TEST 9: Hiển thị lại danh sách sau khi Save (Toàn bộ dữ liệu chuyển sang màu xanh)
        // ----------------------------------------------------------------
        System.out.println("\n>>> TEST 9: DISPLAY INCIDENTS AFTER SAVING");
        incidentManager.displayIncidents();
        
        System.out.println("\n==================================================");
        System.out.println("            ALL TESTS COMPLETED!");
        System.out.println("==================================================");
    }
}