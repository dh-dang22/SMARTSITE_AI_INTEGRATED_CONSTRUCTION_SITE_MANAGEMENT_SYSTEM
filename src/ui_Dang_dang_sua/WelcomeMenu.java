
package ui_Dang_dang_sua;

import java.util.Scanner;

public class WelcomeMenu {

    public void displayWelcomeMenu(){
        Scanner sc = Validatable.InputScanner.getScanner();
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

            //nhập và kiểm tra phải số không
            int choice;
            try {
                if (!sc.hasNextLine()) return;
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                // Nếu nhập chữ hoặc ký tự, bắt lỗi ở đây
                System.out.println(Draft.center(Draft.RED + "Please enter a valid number [1-3]!" + Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();
                continue;
            }

            Draft.displayMenuLine();//in gạch
            switch (choice){
                case (1):
                    Draft.clearConsoleIDE();
                    LoginMenu menu = new LoginMenu();
                    menu.displayLoginMenu();
                    break;
                case (2):
                    MenuSession.ZONES.displayList();
                    Draft.pause();
                    break;
                case (3):
                    //turnOff();
                    System.out.println(Draft.center("See you late!"));
                    running = false;
                    Draft.pause();
                    Draft.clearConsoleIDE();
                    break;
                default:
                    System.out.println(Draft.center(Draft.RED+"Invalid choice!!!" + Draft.BAS));
                    Draft.pause();
                    Draft.clearConsoleIDE();
                    break;
            }//switch-case choice

        } //while (running)

    } //displayWelcomeMenu()

}//class
