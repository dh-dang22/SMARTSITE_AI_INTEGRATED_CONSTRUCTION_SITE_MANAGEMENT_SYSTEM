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
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
                break;
            case (1):
                //displayInformation();
                System.out.println("                                         Function is not completed yet!");
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (2):
                //getCheckInTime();
                System.out.println("                                            Check-in successful!");
                System.out.println("                                                ... but ...");
                System.out.println("                                         Function is not completed yet!");
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (3):
                //getCheckOutTime();
                System.out.println("                                            Check-out successful!");
                System.out.println("                                                ... but ...");
                System.out.println("                                         Function is not completed yet!");
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (4):
                System.out.println("                                         Function is not completed yet!");
                Draft.pause();
                Draft.clearConsoleIDE();
                break;
            case (5):
                UniqueSiteManagerMenu ht = new UniqueSiteManagerMenu();
                ht.displayManagerFeature();
                break;
            default:
                System.out.println("                                         Invalid choice! Please select again");
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }
}
