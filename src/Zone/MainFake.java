package Zone;

import java.util.Scanner;

public class MainFake {

    public static void main(String[] args) {
        // Lấy Instance quản lý ZoneManager2
        ZoneManager2 zm = ZoneManager2.getInstance();

        System.out.println("==================================================");
        System.out.println("   CHƯƠNG TRÌNH TEST TỰ ĐỘNG THUỘC TÍNH ISRESTRICTED");
        System.out.println("==================================================");

        // 1. Giả lập tạo dữ liệu mẫu trực tiếp để test (Không qua Scanner)
        System.out.println("\n---> [STEP 1]: Khởi tạo dữ liệu mẫu cho Zone và RestrictedZone...");
        
    // Giữ đúng 4 tham số cho Zone: (zoneId, isRestricted, zoneName, zoneDetails)
        Zone z1 = new Zone("ZN0001", false, "Khu Vuc San Xuat A", "Khu vuc danh cho công nhan");

// Giữ đúng 5 tham số cho RestrictedZone: (zoneId, isRestricted, zoneName, zoneDetails, pinCode)
        RestrictedZone rz1 = new RestrictedZone("ZN0002", true, "Khu Vuc May Chu Server", "Yeu cau Passcode truy cap", "ABC123");
        // Đưa dữ liệu vào danh sách (Sử dụng hàm findZoneById để kiểm tra trùng trước)
        if (z1.isValid()) {
            // Giả lập đưa vào list trong ZoneManager bằng cách sử dụng phương thức hỗ trợ hoặc kiểm tra trực tiếp
            System.out.println("✔ Khởi tạo z1 (ZN0001) thành công. Status isRestricted: " + z1.isRestricted());
        }
        
        if (rz1.isValid()) {
            System.out.println("✔ Khởi tạo rz1 (ZN0002) thành công. Status isRestricted: " + rz1.isRestricted() + " | PinCode: " + rz1.getPinCode());
        }

        System.out.println("\n--------------------------------------------------");
        System.out.println("   CHẠY MENU CONSOLE KIỂM THỬ TRỰC TIẾP");
        System.out.println("--------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== ZONE MANAGER TEST MENU =====");
            System.out.println("[1] Thêm Zone thường (addZone)");
            System.out.println("[2] Thêm Zone hạn chế (addRestrictedZone)");
            System.out.println("[3] Tìm Zone theo ID (findZoneById)");
            System.out.println("[4] Cập nhật Zone theo ID (updateZoneById)");
            System.out.println("[5] X1óa Zone theo ID (deleteZoneById)");
            System.out.println("[6] Hiển thị TẤT CẢ các Zone (displayList)");
            System.out.println("[7] Chỉ hiển thị các Zone hạn chế (displayRestrictedZonesOnly)");
            System.out.println("[0] Thoát chương trình Test");
            System.out.print("➔ Chọn chức năng [0-7]: ");

            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.println("\n--- [TEST]: Thêm Zone thường ---");
                    zm.addZone();
                    break;

                case "2":
                    System.out.println("\n--- [TEST]: Thêm Restricted Zone ---");
                    zm.addRestrictedZone();
                    break;

                case "3":
                    System.out.println("\n--- [TEST]: Tìm kiếm Zone ---");
                    System.out.print("Nhập mã ZoneID cần tìm (VD: ZN0001): ");
                    String searchId = sc.nextLine().trim();
                    Zone foundZone = zm.findZoneById(searchId);
                    if (foundZone != null) {
                        System.out.println("-> Tìm thấy: " + foundZone);
                        System.out.println("-> Chi tiết thuộc tính [isRestricted]: " + foundZone.isRestricted());
                        System.out.println("-> Chi tiết phương thức [isAccessRestricted()]: " + foundZone.isAccessRestricted());
                    } else {
                        System.out.println("[!] Không tìm thấy ZoneID [" + searchId + "] trong hệ thống.");
                    }
                    break;

                case "4":
                    System.out.println("\n--- [TEST]: Cập nhật Zone ---");
                    zm.updateZoneById();
                    break;

                case "5":
                    System.out.println("\n--- [TEST]: Xóa Zone ---");
                    zm.deleteZoneById();
                    break;

                case "6":
                    System.out.println("\n--- [TEST]: Danh sách tất cả các Zone ---");
                    zm.displayList();
                    break;

                case "7":
                    System.out.println("\n--- [TEST]: Danh sách chỉ riêng Restricted Zone ---");
                    zm.displayRestrictedZonesOnly();
                    break;

                case "0":
                    running = false;
                    System.out.println("Đã thoát chương trình Test!");
                    break;

                default:
                    System.out.println("[Error]: Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }
}