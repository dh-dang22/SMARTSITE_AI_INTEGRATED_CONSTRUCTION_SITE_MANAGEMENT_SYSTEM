
package ui_Dang_dang_sua;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static ui_Dang_dang_sua.Draft.clearConsoleIDE;

public class WelcomeMenu {

    public void displayWelcomeMenu(){
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("================================================================================"); //80 dấu =
            System.out.println("");//Hình lấy từ Gehihi
            System.out.println("      ___           ___           ___           ___           ___");
            System.out.println("     /\\  \\         /\\  \\         /\\  \\         /\\  \\         /\\  \\");
            System.out.println("    /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\");
            System.out.println("   /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\");
            System.out.println("  /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\");
            System.out.println(" /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\");
            System.out.println(" \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/");
            System.out.println("  \\:\\__\\        \\:\\__\\        \\:\\__\\        \\:\\__\\        \\:\\__\\");
            System.out.println("   \\/__/         \\/__/         \\/__/         \\/__/         \\/__/");
            System.out.println("");
            System.out.println("================================================================================"); //80 dấu =
            System.out.println("                         WELCOME TO THE SSMARTSITE");
            System.out.println("                      Version 2.4.3 | Terminal Edition"); //Version Semester.Lab.TimeConfig
            // Forrmateed HH/MM/SS: Giờ:Phút:Giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                LocalTime now = LocalTime.now();
                // In ghi đè lên dòng cũ bằng '\r' trên Console/Terminal
            System.out.println("\r                              Time: " + now.format(formatter));         
            System.out.println("================================================================================"); //80 dấu =
            System.out.println("");
            System.out.println("[!] NOTICE: The system is ready to work. Please choose one of the option below");
            System.out.println("");
            System.out.println("        +------------------------------------------------------+"); //54 dấu -
            System.out.println("        | 1. Sign in                                           |");
            System.out.println("        | 2. View area information                             |");
            System.out.println("        | 3. Exit                                              |");
            System.out.println("        +------------------------------------------------------+"); //54 dấu -
            System.out.println("");
            System.out.println("================================================================================"); //80 dấu =
            System.out.print("   >>> Enter your action[1-3]: ");
            int choice = Integer.parseInt(sc.nextLine());
            System.out.println("================================================================================"); //80 dấu =
            switch (choice){
                case (1):
                    clearConsoleIDE();
                    LoginMenu menu = new LoginMenu();
                    menu.displayLoginMenu();
                    break;
                case (2):
                    System.out.println("Function is not completed yet!");
                    running = false;
                    //displayZoneInformation();
                    break;
                case (3):
                    //turnOff();
                    System.out.println("See you late!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!!!");
                    break;
            }//switch-case choice

        } //while (running)

    } //displayWelcomeMenu()

}//class
