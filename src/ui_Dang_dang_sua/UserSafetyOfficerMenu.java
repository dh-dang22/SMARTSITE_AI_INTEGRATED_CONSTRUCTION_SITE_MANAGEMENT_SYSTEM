package ui_Dang_dang_sua;

public class UserSafetyOfficerMenu extends MenuUserLv00{

    @Override
    public void displayOption(){
        System.out.println("    [1] Display your information");
        System.out.println("    [2] Check-in");
        System.out.println("    [3] Check-out");
        System.out.println("    [4] Display History Attendance");
        System.out.println("    [5] Display Incident Management functions");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
                break;
            case (5):                
                Draft.functionIsNotCompletedYet();
                break;
            default:
                System.out.println(Draft.center(Draft.RED+"Invalid choice! Please select again"+Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();        }
    }
}
