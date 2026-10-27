package ui_Dang_dang_sua;

public class UserWorkerMenu extends MenuUserLv00{

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
            case (0):
                break;
            default:
                System.out.println(Draft.center(Draft.RED+"Invalid choice! Please select again"+Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }
}
