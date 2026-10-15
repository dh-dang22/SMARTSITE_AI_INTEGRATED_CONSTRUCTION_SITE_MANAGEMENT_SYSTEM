package ui;

import java.util.Scanner;
import smartsite.Person;
import smartsite.Worker;
import smartsite.Visitor;
import static ui.Draft.pause;

public class MenuUserLv02 extends MenuUserLv03 {

    @Override
    public void displayOption() {
        super.displayOption();
        System.out.println("5. Take tools");
        System.out.println("6. Add person");
    }

    @Override
    public void chooseOption(int choice) {
        switch (choice) {
            case (5):
                System.out.println("Function not complete yet!");
                pause();
                break;
            case (6):
                
                break;
            default:
                super.chooseOption(choice);
                break;
        }
    }
}
