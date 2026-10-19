package ui_Dang_dang_sua;

import static ui_Dang_dang_sua.Draft.pause;

public class UserSafetyOfficerMenu extends MenuUserLv00{

    @Override
    public void displayOption(){
        System.out.println("    [1] ➔ Display your information");
        System.out.println("    [2] ➔ Check-in");
        System.out.println("    [3] ➔ Check-out");
        System.out.println("    [4] ➔ Display History Attendance");
        System.out.println("    [5] ➔ Display unique function");
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
                Draft.functionIsNotCompletedYet();
                break;
            default:
                Draft.functionIsNotCompletedYet();
        }
    }
}
