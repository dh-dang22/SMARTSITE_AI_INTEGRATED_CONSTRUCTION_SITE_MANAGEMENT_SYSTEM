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
        System.out.println("    [5] Human resources and account management");
        System.out.println("    [6] Zone management");
        System.out.println("    [7] Incident management");
        System.out.println("    [8] Display all attendance records");
        System.out.println("    [9] Display all incident reports");
    }

    @Override
    public void chooseOption(int choice) {
        UniqueSiteManagerMenu management = new UniqueSiteManagerMenu();

        switch (choice) {
            case 1:
                UniqueSiteManagerMenu admin = new UniqueSiteManagerMenu();
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
                attendanceManager.displayAll();
                break;
            case 5:
                management.displayHumanResourceManagementMenu();
                break;
            case 6:
                management.displayZoneManagementMenu();
                break;
            case 7:
                IncidentMenu.getInstance().display();
                break;
            case 8:
                management.displayAllAttendance();
                break;
            case 9:
                management.displayAllIncident();
                break;
            default:
                System.out.println("Invalid choice. Please select again.");
        }

        Draft.displayMenuLine();
        Draft.pause();
        Draft.clearConsoleIDE();
        
    }
}