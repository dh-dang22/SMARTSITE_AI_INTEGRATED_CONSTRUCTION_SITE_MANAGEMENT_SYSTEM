//package smartsite;
//
//import Zone.ZoneManager2;
//import java.util.Scanner;
//
///**
// * 
// * @author GIA HUY
// */
//public class runtest {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        ZoneManager2 zoneManager = new ZoneManager2();
//        boolean exit = false;
//
//        System.out.println("==========================================");
//        System.out.println("   CHƯƠNG TRÌNH THỦ TÍNH NĂNG ZONE MANAGER  ");
//        System.out.println("==========================================");
//
//        while (!exit) {
//            System.out.println("\n--- MENU QUẢN LÝ VÙNG (ZONES) ---");
//            System.out.println("1. Thêm Vùng thường (Add Zone)");
//            System.out.println("2. Thêm Vùng hạn chế (Add Restricted Zone)");
//            System.out.println("3. Hiển thị tất cả Vùng (Display All)");
//            System.out.println("4. Chỉ hiển thị Vùng hạn chế (Display Restricted Only)");
//            System.out.println("5. Cập nhật thông tin Vùng (Update Zone)");
//            System.out.println("6. Xóa Vùng theo ID (Delete Zone)");
//            System.out.println("0. Thoát chương trình");
//            System.out.println("----------------------------------");
//
//            // Dùng ngay hàm readRange của CheckInput để chọn Menu an toàn không lo crash
//            int choice = CheckInput.readRange(sc, "Nhập lựa chọn của bạn (0-6): ", 0, 6, "Lựa chọn không hợp lệ! Vui lòng chọn từ 0 đến 6.");
//
//            switch (choice) {
//                case 1:
//                    System.out.println("\n--- THÊM VÙNG THƯỜNG ---");
//                    zoneManager.addZone(sc);
//                    break;
//
//                case 2:
//                    System.out.println("\n--- THÊM VÙNG HẠN CHẾ ---");
//                    zoneManager.addRestrictedZone(sc);
//                    break;
//
//                case 3:
//                    System.out.println("\n--- DANH SÁCH TẤT CẢ CÁC VÙNG ---");
//                    zoneManager.displayList();
//                    break;
//
//                case 4:
//                    System.out.println("\n--- DANH SÁCH VÙNG HẠN CHẾ ---");
//                    zoneManager.displayRestrictedZonesOnly();
//                    break;
//
//                case 5:
//                    System.out.println("\n--- CẬP NHẬT VÙNG ---");
//                    zoneManager.updateZoneById(sc);
//                    break;
//
//                case 6:
//                    System.out.println("\n--- XÓA VÙNG ---");
//                    zoneManager.deleteZoneById(sc);
//                    break;
//
//                case 0:
//                case -1: // Trường hợp ngắt luồng (Ctrl+C / EOF)
//                    System.out.println("\nĐã thoát chương trình thử nghiệm. Tạm biệt!");
//                    exit = true;
//                    break;
//
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//            }
//        }
//        sc.close();
//    }
//}