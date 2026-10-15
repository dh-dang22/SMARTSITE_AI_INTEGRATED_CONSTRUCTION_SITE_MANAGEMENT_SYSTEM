package ui;

import static ui.Draft.pause;
import smartsite.Main;
public class MenuUserLv01 extends MenuUserLv02{

   public MenuUserLv01() {
        super();
    }

    
   @Override
    public void displayOption() {
        super.displayOption();
        System.out.println("7. Show all history attendance");
    }
    @Override
    public void chooseOption(int choice) {
        switch (choice) {
            case (7):
                System.out.println("Function not complete yet!");
                pause();
                break;
            default:
                super.chooseOption(choice);
                break;
        }
    }
}
