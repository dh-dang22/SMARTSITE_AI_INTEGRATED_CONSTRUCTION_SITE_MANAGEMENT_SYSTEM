package ui_Dang_dang_sua;

import Person.PersonManager;
import Attendance.AttendanceManager;

public class UserVisitorMenu extends MenuUserLv00{
    AttendanceManager attendanceManager = AttendanceManager.getInstance();
    @Override
    public void displayOption(){
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in (you have akready checked-in when you logged in!)");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
                Draft.clearConsoleIDE();
                break;
            case (1):
                new PersonManager().displayPersonInformation(currentUser);
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();                break;
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
            default:
                System.out.println(Draft.center(Draft.RED+"Invalid choice! Please select again"+Draft.BAS));
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }
}
