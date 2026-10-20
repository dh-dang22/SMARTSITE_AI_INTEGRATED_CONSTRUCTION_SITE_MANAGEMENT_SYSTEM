package ui_Dang_dang_sua;
import Person.PersonManager;

public class UserSiteManagerMenu extends MenuUserLv00{
    // AddPerson và UniqueSiteManagerMenu: lấy cùng danh sách
    private final PersonManager pm = PersonManager.getInstance();
    @Override
    public void displayOption(){
        System.out.println("    [1] ➔ Display your information");
        System.out.println("    [2] ➔ Check-in");
        System.out.println("    [3] ➔ Check-out");
        System.out.println("    [4] ➔ Display History Attendance");
        System.out.println("    [5] ➔ Display human resources management functions");
        System.out.println("    [6] ➔ Display zone management functions");
        System.out.println("    [7] ➔ Display all History Attendance Report");
        System.out.println("    [8] ➔ Display all Incident Report");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
                break;
            case (1):
                Draft.functionIsNotCompletedYet();
                break;
            case (2):
                Draft.functionIsNotCompletedYet();
                break;
            case (3):
                Draft.functionIsNotCompletedYet();
                break;
            case (4):
                Draft.functionIsNotCompletedYet();
                break;
            case (5):
                Draft.clearConsoleIDE();
                UniqueSiteManagerMenu ht = new UniqueSiteManagerMenu();
                ht.displayHumanResourceManagementMenu();
                break;
            case (6):
                UniqueSiteManagerMenu htZone = new UniqueSiteManagerMenu();
                htZone.displayZoneManagementMenu();
                break;
            case (7):
                Draft.functionIsNotCompletedYet();
                break;
            case (8):
                Draft.functionIsNotCompletedYet();
                break;
            default:
                System.out.println("                                         Invalid choice! Please select again");
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }
}
