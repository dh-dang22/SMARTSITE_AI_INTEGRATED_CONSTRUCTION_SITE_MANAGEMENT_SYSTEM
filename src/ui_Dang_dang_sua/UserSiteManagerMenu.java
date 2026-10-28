package ui_Dang_dang_sua;

public class UserSiteManagerMenu extends MenuUserLv00{
    @Override
    public void displayOption(){
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
        System.out.println("    [5] Display human resources management functions");
        System.out.println("    [6] Display zone management functions");
        System.out.println("    [7] Display all History Attendance Report");
        System.out.println("    [8] Display all Incident Reports");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
                break;
            case (1):
                UniqueSiteManagerMenu ht1 = new UniqueSiteManagerMenu();
                ht1.displayPersonInformation(currentUser);
                Draft.pause();
                Draft.clearConsoleIDE();
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
                UniqueSiteManagerMenu ht5 = new UniqueSiteManagerMenu();
                ht5.displayHumanResourceManagementMenu();
                break;
            case (6):
                UniqueSiteManagerMenu ht6 = new UniqueSiteManagerMenu();
                ht6.displayZoneManagementMenu();
                break;
            case (7):
                UniqueSiteManagerMenu ht7 = new UniqueSiteManagerMenu();
                ht7.displayAllAttendance();
                break;
            case (8):
                UniqueSiteManagerMenu ht8 = new UniqueSiteManagerMenu();
                ht8.displayAllIncident();
                break;
            default:
                System.out.println(Draft.center(Draft.RED+"Invalid choice! Please select again"+Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }
}
