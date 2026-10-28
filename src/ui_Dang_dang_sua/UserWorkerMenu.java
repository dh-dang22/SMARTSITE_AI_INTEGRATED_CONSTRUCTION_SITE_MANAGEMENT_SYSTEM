package ui_Dang_dang_sua;

import Attendance.AttendanceManager;

public class UserWorkerMenu extends MenuUserLv00{

        AttendanceManager attendanceManager = AttendanceManager.getInstance();
    @Override
    public void displayOption(){
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
    }
    
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (1):
                new UniqueSiteManagerMenu().displayPersonInformation(currentUser);
                Draft.pause();
                break;
            case (2):
                attendanceManager.checkInFromInput();
                break;
            case (3):
                 attendanceManager.checkOutFromInput();
                break;
            case (4):
                attendanceManager.displayHistory(currentUser.getId());
                Draft.pause();
                break;
            case (0):
                break;
            default:
                System.out.println(Draft.center(Draft.RED+"Invalid choice! Please select again"+Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }

}
