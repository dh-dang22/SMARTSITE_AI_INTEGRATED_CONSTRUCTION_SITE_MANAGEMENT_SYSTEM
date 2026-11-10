package ui_Dang_dang_sua;

import Attendance.AttendanceManager;

public class MenuAdminKhangSua extends MenuUserLv00 {

    private final AttendanceManager attendanceManager =
            AttendanceManager.getInstance();

    @Override
    public void displayOption() {
        System.out.println("    [1] ManageAccount");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display your attendance history");
        System.out.println("    [5] Display Account management");

    }

    @Override
    public void chooseOption(int choice) {
        ManagementMenu management = new ManagementMenu();

        switch (choice) {
            case 1:
                ManagementMenu admin = new ManagementMenu();
                admin.manageAccounts();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case 2:
                attendanceManager.checkInFromInput(activeZone);
                break;
            case 3:
                attendanceManager.checkOutFromInput();
                break;
            case 4:
                attendanceManager.displayAllAttendanceReport();
                break;
            case 5:
                management.displayHumanResourceManagementMenu();
                break;
            default:
                System.out.println("Invalid choice. Please select again.");
        }

        Draft.displayMenuLine();
        Draft.pause();
        
    }
}