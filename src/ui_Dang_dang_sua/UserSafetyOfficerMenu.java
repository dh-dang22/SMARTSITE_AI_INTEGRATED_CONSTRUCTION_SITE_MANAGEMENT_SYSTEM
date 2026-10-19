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
                System.out.println(Draft.RED+"                              Function is not completed yet!"+Draft.BAS);
                Draft.clearConsoleIDE();
                Draft.pause();
                break;
            case (2):
                System.out.println(Draft.RED+"                              Function is not completed yet!"+Draft.BAS);
                Draft.clearConsoleIDE();
                Draft.pause();
                break;
            case (3):
                 System.out.println(Draft.RED+"                              Function is not completed yet!"+Draft.BAS);
                Draft.clearConsoleIDE();
                Draft.pause();
                break;
            case (4):
                System.out.println(Draft.RED+"                              Function is not completed yet!"+Draft.BAS);
                Draft.clearConsoleIDE();
                Draft.pause();
                break;
            case (5):
                 System.out.println(Draft.RED+"                              Function is not completed yet!"+Draft.BAS);
                Draft.clearConsoleIDE();
                Draft.pause();
                break;
            default:
                System.out.println(Draft.RED+"                           Invalid choice! Please select again"+Draft.BAS);
                Draft.pause();
                Draft.clearConsoleIDE();
        }
    }
}
