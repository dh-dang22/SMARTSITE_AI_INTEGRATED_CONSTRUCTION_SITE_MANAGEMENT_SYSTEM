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
        System.out.println("    [8]  Manage incident reports");
    }
    
    @Override
    public void chooseOption(int choice){
        if (handleCommonOption(choice)) return;
        switch (choice){
            case (0):
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
                am.displayAll();
                Draft.pause();
                break;
            case (8):
                new IncidentMenu().display();
                break;
            default:
                System.out.println("                                         Invalid choice! Please select again");
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }
}
