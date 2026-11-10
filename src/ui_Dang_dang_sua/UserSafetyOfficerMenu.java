package ui_Dang_dang_sua;

import Attendance.AttendanceManager;
import Incident.IncidentManager;
import Person.PersonManager;
import Validatable.CheckId;
import Validatable.InputScanner;
import java.util.Scanner;

public class UserSafetyOfficerMenu extends MenuUserLv00 {
    private final AttendanceManager attendanceManager = AttendanceManager.getInstance();
    private final Scanner sc = InputScanner.getScanner();

    @Override
    public void displayOption() {
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
        System.out.println("    [5] Display Incident Management functions");
    }

    @Override
    public void chooseOption(int choice) {
        switch (choice) {
            case (0):
                Draft.clearConsoleIDE();
                break;
            case (1):
                new PersonManager().displayPersonInformation(currentUser);
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (2):
                attendanceManager.checkInFromInput(activeZone);
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (3):
                attendanceManager.checkOutFromInput();
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (4):
                attendanceManager.History(currentUser.getId());
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (5):
                ManagementMenu.displayIncidentManagementMenu();
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            default:
                System.out.println(Draft.center(Draft.RED + "Invalid choice! Please select again" + Draft.BAS));
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }

    /** Input and navigation for the incident management subsystem. */

}