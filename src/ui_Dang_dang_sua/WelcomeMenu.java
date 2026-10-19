
package ui_Dang_dang_sua;

import java.util.Scanner;

public class WelcomeMenu {

    public void displayWelcomeMenu(){
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            Draft.displayMenuLine();//in gạch
            Draft.displayMenuHeader();//in banner
            Draft.displayMenuLine();//in gạch
            Draft.displayMenuGreeting();//in lời chào
            Draft.displayMenuLine();//in gạch
            Draft.displayWelcomeMenuOption();//in option lựa chọn
            Draft.displayMenuLine();//in gạch
            System.out.print("   >>> Enter your action[1-3]: ");
            int choice = Integer.parseInt(sc.nextLine());
            Draft.displayMenuLine();//in gạch
            switch (choice){
                case (1):
                    Draft.clearConsoleIDE();
                    LoginMenu menu = new LoginMenu();
                    menu.displayLoginMenu();
                    break;
                case (2):
                    System.out.println(Draft.RED + "                    Function is not completed yet!" + Draft.BAS);
                    running = false;
                    //displayZoneInformation();
                    break;
                case (3):
                    //turnOff();
                    System.out.println("                                          See you late!");
                    running = false;
                    break;
                default:
                    System.out.println(Draft.RED+"                                        Invalid choice!!!" + Draft.BAS);
                    Draft.pause();
                    Draft.clearConsoleIDE();
                    break;
            }//switch-case choice

        } //while (running)

    } //displayWelcomeMenu()

}//class
