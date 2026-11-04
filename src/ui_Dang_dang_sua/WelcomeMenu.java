package ui_Dang_dang_sua;

import Zone.RestrictedZone;
import Zone.Zone;
import java.util.Scanner;
import Zone.ZoneManager2;

public class WelcomeMenu {

    public void displayWelcomeMenu() {
        Scanner sc = Validatable.InputScanner.getScanner();
        boolean running = true;
        ZoneManager2 zm = ZoneManager2.getInstance();
        Zone activeZone = zm.getRandomActiveZone();

        while (running) {
            Draft.displayMenuLine();//in gạch
            Draft.displayMenuHeader();//in banner
            Draft.displayMenuLine();//in gạch
            Draft.displayMenuGreeting();//in lời chào

            Draft.displayMenuLine();//in gạch
            System.out.println("");
            if (activeZone != null) {
                System.out.println(Draft.center("CURRENT LOCATION: " + activeZone.getZoneName() + " (" + activeZone.getZoneId() + ")"));
            } else {
                System.out.println(Draft.center("CURRENT LOCATION: No active zone assigned"));
            }
            System.out.println("");
            
            Draft.displayWelcomeMenuOption();//in option lựa chọn
            System.out.println("");
            Draft.displayMenuLine();//in gạch
            System.out.print("   >>> Enter your action[1-3]: ");

            //nhập và kiểm tra phải số không
            int choice;
            try {
                if (!sc.hasNextLine()) {
                    return;
                }
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                // Nếu nhập chữ hoặc ký tự, bắt lỗi ở đây
                System.out.println(Draft.center(Draft.RED + "Please enter a valid number [1-3]!" + Draft.BAS));
                Draft.pause();
                Draft.clearConsoleIDE();
                continue;
            }

            Draft.displayMenuLine();//in gạch
            switch (choice) {
                case (1):
                    Draft.clearConsoleIDE();
                    LoginMenu menu = new LoginMenu();
                    menu.displayLoginMenu();
                    break;
                case (2):
                    System.out.println("");
                    System.out.println(Draft.center("CURRENT LOCATION INFORMATION"));
                    System.out.println("");
                    System.out.println("");
                    

                    Zone current = zm.getActiveZone();

                    if (current != null) {
                        System.out.println(Draft.center("Zone ID: " + current.getZoneId()));
                        System.out.println(Draft.center("Zone Name : " + current.getZoneName()));
                        System.out.println(Draft.center("Type : " + (current.isRestricted() ? Draft.RED+"Restricted Zone"+Draft.BAS : Draft.GRN+"Normal Zone"+Draft.BAS)));

                        // Nếu là RestrictedZone thì in thêm Mã PIN bảo mật
//                        if (current instanceof RestrictedZone) {
//                            RestrictedZone rz = (RestrictedZone) current;
//                            System.out.println(Draft.center("Security Passcode : " + rz.getPinCode()));
//                        }
                    } else {
                        System.out.println(Draft.center(Draft.RED + "No active zone assigned at the moment!" + Draft.BAS));
                    }

                    System.out.println();
                    System.out.println("");
                    Draft.displayMenuLine();
                    Draft.pause();
                    Draft.clearConsoleIDE();
                    break;
                case (3):
                    //turnOff();
                    System.out.println(Draft.center("See you later!"));
                    running = false;
                    Draft.pause();
                    break;
                default:
                    System.out.println(Draft.center(Draft.RED + "Invalid choice!!!" + Draft.BAS));
                    Draft.pause();
                    Draft.clearConsoleIDE();
                    break;
            }//switch-case choice

        } //while (running)

    } //displayWelcomeMenu()

}//class
