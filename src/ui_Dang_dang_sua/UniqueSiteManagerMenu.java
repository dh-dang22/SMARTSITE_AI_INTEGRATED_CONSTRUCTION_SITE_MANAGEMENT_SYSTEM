package ui_Dang_dang_sua;

import java.util.Scanner;

import Attendance.AttendanceManager;
import Person.PersonManager;
import Validatable.InputScanner;
import Zone.Zone;
import Zone.ZoneManager2;
import java.io.IOException;

//import Zone.ZoneManager2;
public class UniqueSiteManagerMenu {

    private final Scanner sc2 = new Scanner(System.in);
    // AddPerson và UniqueSiteManagerMenu: lấy cùng danh sách
    private final PersonManager pm;
    ZoneManager2 zm = ZoneManager2.getInstance();
    Zone activeZone = zm.getRandomActiveZone();
    AttendanceManager attendanceManager = AttendanceManager.getInstance();

    UniqueSiteManagerMenu() {
        this(PersonManager.getInstance());
    }

    UniqueSiteManagerMenu(PersonManager pm) {
        this.pm = pm;
    }

    public void displayHumanResourceManagementMenu() {
        boolean running = true;
        while (running) {
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            if (activeZone != null) {
                System.out.println(Draft.center("CURRENT LOCATION: " + activeZone.getZoneName() + " (" + activeZone.getZoneId() + ")"));
            } else {
                System.out.println(Draft.center("CURRENT LOCATION: No active zone assigned"));
            }
            Draft.displayMenuLine();
            System.out.println("");
            System.out.println(Draft.center("Human Resource Management Menu"));
            System.out.println("");
            System.out.println("    [1]  Add Person(s) to system");
            System.out.println("    [2]  Find a Person information");
            System.out.println("    [3]  Update a Person information");
            System.out.println("    [4]  Delete a Person");
            System.out.println("    [5]  Display all people information"); //
            System.out.println("    [6]  Sort people by name");
            System.out.println("");
            System.out.println("    [0]  Back to general menu");
            System.out.println("");
            System.out.print(Draft.BLU + "    ➔ Please enter your action [0-7]: " + Draft.BAS);
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                if (!sc2.hasNextLine()) {
                    return;
                }
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED + "[!] Invalid choice! Please enter a number!" + Draft.BAS));
                Draft.pause();
                continue;
            }
            Draft.displayMenuLine();

            if (choice == 0) {                          // Thoát menu
                PersonManager.getInstance().saveToFile();
                running = false;
                System.out.println("    ➔ The system is backing to general menu ...");
            } else {
                switch (choice) {
                    case (1): // thêm Person (đã chỉnh, chưa ổn) (quên chưa ổn chỗ nào cmnr)
                        int n = InputScanner.readRange(sc2, Draft.BLU + "   ➔ Enter a number of Person(s) you want to add[1-100][-1 to cancel]: " + Draft.BAS, 1, 100, "Please enter a number between 1-100!");
                        System.out.println("");
                        if (n == -1) {
                            return;
                        }
                        for (int i = 0; i < n; i++) {
                            Draft.displayMenuLine();
                            System.out.println(Draft.center("--- Person " + (i + 1) + " ---"));
                            pm.addPersonFromInput();
                        }
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 2: // tìm kiếm Person (chưa chỉnh, chưa ổn) (hàm tìm kiếm trả giá trị Person, hok chỉnh layout đc, yêu cầu viết hàm trả giá trị void)
                        pm.findPersonFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case (3): // cập nhật Person (chưa chỉnh, chưa ổn) (chưa viết hàm chỉnh sửa)
                        Draft.functionIsNotCompletedYet();
                        break;
                    case 4: // xóa Person (chưa chỉnh, chưa ổn) (quên chưa ổn chỗ nào cmnr)
                        pm.deletePersonFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case (5): // hiển thị danh sách (đã chỉnh, chưa ổn) (đề xuất hiển thị thêm code kế bên ID, sửa lỗi license của SO dính vào status)
                        pm.displayAllByGehihi();
                        Draft.displayMenuLine();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 6: // sắp xếp danh sách (đã chỉnh, chưa ổn) (ở phần sort theo role, cho chọn role sort ra
                        pm.sortFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                }
            }
        } //while
    }//display

    public void displayZoneManagementMenu() {
        ZoneManager2 tempZone = ZoneManager2.getInstance();
        Scanner tempSC = new Scanner(System.in);
        boolean running = true;
        while (running) {
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            Draft.displayMenuLine();
            System.out.println(Draft.center("Zone Management Menu"));
            System.out.println("");
            System.out.println("    [1] Add Zone(s) to system");
            System.out.println("    [2] Find a Zone information");
            System.out.println("    [3] Update a Zone information");
            System.out.println("    [4] Delete a Zone");
            System.out.println("    [5] Display Zone information");
            System.out.println("    [6] Sort Zones By ID");
            System.out.println("    [7] Save file");
            System.out.println("");
            System.out.println("    [0] Back to general menu");
            System.out.println("");
            System.out.print(Draft.BLU + "    ➔ Please enter your action [0-5]: " + Draft.BAS);
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED + "[!] Invalid choice! Please enter a number." + Draft.BAS));
                Draft.pause();
                continue;
            }
            Draft.displayMenuLine();

            if (choice == 0) {                          // Thoát menu
                System.out.print(Draft.center("Have you saved your file yet? [Y/N]: "));
                String areYouSure = tempSC.nextLine();
                switch (areYouSure.toLowerCase()) {
                    case "y":
                        running = false;
                        System.out.println(Draft.center("The system is backing to general menu ..."));
                        Draft.displayMenuLine();
                    case "n":
                        continue;
                    default:
                        System.out.println(Draft.RED + "Invalid choice!" + Draft.BAS);
                }

            } else {
                switch (choice) {
                    case (1): // thêm Zone (đã chỉnh, đã ổn) 
                        System.out.print("    ➔ Enter the type of Zone: " + Draft.GRN + "[1] NOR" + Draft.RED + " / " + "[2] RED" + Draft.BAS + " : ");
                        int choiceAdd = tempSC.nextInt();
                        switch (choiceAdd) {
                            case (1):
                                tempZone.addZone();
                                break;
                            case (2):
                                tempZone.addRestrictedZone();
                                break;
                        }
                        break;
                    case (2): // tìm kiếm khu vực (chưa chỉnh, chưa ổn) (viết lại, yêu cầu: viêt hàm tìm kiếm là void để ui chỉnh sửa đc, hàm giá trị Person chỉ dùng để gom giá trị tìm kiếm (trong Zalo có nói))
                        System.out.print("    ➔ Enter the Zone ID(e.g., ZN0001): ");
                        String tempID = tempSC.nextLine();
                        if (tempID != null) {
                            System.out.println(tempZone.findZoneById(tempID));
                        } else {
                            System.out.println();
                            System.out.println(Draft.center(Draft.RED + "[Error]: Zone ID [" + tempID + "] not found in system!" + Draft.BAS));
                        }
                        break;
                    case (3): // cập nhật khu vực (đã chỉnh, chưa ổn) (yêu cầu bổ sung chỉnh sửa Type và PIN code, tạo một switch case cho người dùng chọn thông tin chỉnh sửa)
                        tempZone.updateZoneById();
                        break;
                    case (4): // xóa khu vực (đã chỉnh, đã ổn)
                        tempZone.deleteZoneById();
                        break;
                    case (5): // hiển thị danh sách khu vực (đã chỉnh, đã ổn)
                        tempZone.displayOption();
                        break;

                    case (6): // sắp xếp danh sách khu vực (đã chỉnh, chưa ổn) (yêu cầu: Sắp xếp theo ID nhưng k làm thay đổi thứ tự gốc trong file) 
                        tempZone.sortZonesById();
                    case (7): // lưu dữ liệu (chưa chỉnh, đã ổn)
                        tempZone.saveToFile();
                        break;
//                    case (9): // tải dữ liệu (chưa chỉnh, hok rõ)
//                        try {
//                            tempZone.loadFromFile("Zone.txt");
//                        } catch (IOException e) {
//                            System.out.println(Draft.center(Draft.RED+"[Error]: Failed to load file: " + e.getMessage()+Draft.GRN));
//                        }
//                        break;
                }
            }
            Draft.pause();
            Draft.clearConsoleIDE();
        } //while
    }//display

    public void displayAllAttendance() {
        Draft.functionIsNotCompletedYet();
    }//display

    public void displayAllIncident() {
        Draft.functionIsNotCompletedYet();
    }//display

}//class
