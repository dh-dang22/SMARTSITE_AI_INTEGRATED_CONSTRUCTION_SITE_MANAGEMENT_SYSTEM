package ui_Dang_dang_sua;
import Attendance.AttendanceManager;
import Person.PersonManager;

public class UserSiteManagerMenu extends MenuUserLv00{
    AttendanceManager attendanceManager = AttendanceManager.getInstance();
    @Override
    public void displayOption(){
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
        System.out.println("    [5] Display human resources management functions");
        System.out.println("    [6] Display Zone management functions");
        System.out.println("    [7] Display all History Attendance Report");
        System.out.println("    [8] Display all Incident Reports");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
                Draft.clearConsoleIDE();
                break;
            case (1): //hiển thị thông tin (đã chỉnh, ổn)
                PersonManager ht1 = new PersonManager();
                ht1.displayPersonInformation(currentUser);
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (2): //check-in (chưa chỉnh, chưa ổn)
                attendanceManager.checkInFromInput(activeZone);
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (3): //check-out (chưa chỉnh, chưa ổn)
                attendanceManager.checkOutFromInput();
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (4): //hiển thị lịch sử check-in (chưa chỉnh, chưa ổn)
                attendanceManager.History(currentUser.getId());
                Draft.displayMenuLine();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (5): //hiển thị menu Person Management (chưa chỉnh hết, chưa ổn)
                Draft.clearConsoleIDE();
                UniqueSiteManagerMenu ht5 = new UniqueSiteManagerMenu();
                ht5.displayHumanResourceManagementMenu();
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (6): //hiển thị menu Zone  Management (chưa chỉnh hết, chưa ổn)
                Draft.clearConsoleIDE();
                UniqueSiteManagerMenu ht6 = new UniqueSiteManagerMenu();
                ht6.displayZoneManagementMenu();
                Draft.pause();
                Draft.clearConsoleIDE();                
                break;
            case (7):
                UniqueSiteManagerMenu ht7 = new UniqueSiteManagerMenu();
                ht7.displayAllAttendance();
                Draft.pause();
                Draft.clearConsoleIDE();                
                break;
            case (8):
                UniqueSiteManagerMenu ht8 = new UniqueSiteManagerMenu();
                ht8.displayAllIncident();
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
