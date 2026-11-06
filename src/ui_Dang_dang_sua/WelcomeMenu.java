package ui_Dang_dang_sua;

import Zone.RestrictedZone;
import Zone.Zone;
import java.util.Scanner;
import Zone.ZoneManager2;

public class WelcomeMenu {

    public void displayWelcomeMenu() {
        Scanner sc = Validatable.InputScanner.getScanner();
        ZoneManager2 zm = ZoneManager2.getInstance();
        Zone activeZone = zm.getRandomActiveZone();
        boolean running = true;


        
        while (running) {
            
            //banner cảnh báo khu vực RES
            if (activeZone instanceof RestrictedZone) { 
                RestrictedZone rz = (RestrictedZone) activeZone; 
                Draft.displayMenuLine();
                Draft.displayMenuHeader(); 
                Draft.displayMenuLine(); 
                System.out.println(Draft.center(Draft.RED + "[!] SECURITY CHECKPOINT: RESTRICTED ZONE DETECTED!" + Draft.BAS)); 
                System.out.println(Draft.center("Location: " + rz.getZoneName() + " (" + rz.getZoneId() + ")"));
                System.out.println(Draft.center("Enter PIN Code to proceed.")); 
                Draft.displayMenuLine(); 
                int countWrong = 0 ;

                while (true) {
                    System.out.print("   >>> Enter Restricted Zone PIN [ABCxxx] : ");
                    String inputPin = sc.nextLine().trim();

                    if (rz.getPinCode().equals(inputPin)) { 
                        System.out.println(Draft.center(Draft.GRN + "[+] Access Granted to Welcome Menu!" + Draft.BAS)); 
                        Draft.pause(); 
                        Draft.clearConsoleIDE();
                        break;
                    } else {
                        System.out.println("");
                        System.out.println(Draft.center(Draft.RED + "[Error]: Incorrect PIN Code! Please try again." + Draft.BAS));
                        countWrong++ ;
                        System.out.println("");
                        System.out.println(Draft.center(Draft.RED + "You have entered the wrong PIN "+ countWrong+" times." + Draft.BAS));
                        System.out.println(Draft.center(Draft.RED + "When you entered wrong 3 times, the system will end ! \n" + Draft.BAS));
                        if (countWrong >= 3){
                            System.out.println("");
                            System.out.println(Draft.center(Draft.RED + "[!] You have entered the wrong PIN 3 times. Access denied! Exiting system..." + Draft.BAS));
                            Draft.pause();
                            System.exit(0);
                        }
                    }
                }
            }
            
            
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
