
package ui;
import smartsite.Login;
import smartsite.CheckPassword;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static ui.Draft.clearConsoleIDE;
import Person.PersonManager;
import java.io.IOException;

public class WelcomeMenu {
     PersonManager pm = new PersonManager();
     CheckPassword checker = new CheckPassword();

        public WelcomeMenu() {
            try {
                pm.loadFromFile("data/Person.txt");       // Nạp danh sách người dùng
                checker.loadFromFile("data/accounts.txt"); // Nạp mật khẩu
            } catch (IOException e) {
                System.out.println("[!] Lỗi đọc file: " + e.getMessage());
            }
 }
        Login login=new Login();
    public void displayWelcomeMenu() throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("================================================================================");
            System.out.println("");
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
            System.out.println("================================================================================");
            System.out.println("                         WELCOME TO THE SSMARTSITE");
            System.out.println("                      Version 1.0.0 | Terminal Edition");
            // Forrmateed HH/MM/SS: Giờ:Phút:Giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                LocalTime now = LocalTime.now();
                // In ghi đè lên dòng cũ bằng '\r' trên Console/Terminal
            System.out.println("\r                              Time: " + now.format(formatter));         
            System.out.println("================================================================================");
            System.out.println("");
            System.out.println("[!] NOTICE: The system is ready to work. Please choose one of the option below");
            System.out.println("");
            System.out.println("        +------------------------------------------------------+"); //54 dấu -
            System.out.println("        | 1. Sign in                                           |");
            System.out.println("        | 2. View area information                             |");
            System.out.println("        | 3. Exit                                              |");
            System.out.println("        +------------------------------------------------------+"); //54 dấu -
            System.out.println("");
            System.out.println("================================================================================");
            System.out.println("   >>> Enter your action[1-3]: ");
            int choice = Integer.parseInt(sc.nextLine());
            System.out.println("================================================================================"); //need asycnhron
            switch (choice){
                case (1):
                    clearConsoleIDE();
                    Login Login = new Login(pm, checker);
                    try {
                        menuLogin.displayLoginMenu();
                    } catch (Exception e) {
                        System.out.println("[!] Login error: " + e.getMessage());
                    }
                    break;
                case (2):
                    System.out.println("Function is not completed yet!");
                    running = false;
                    clearConsoleIDE();
                    //displayZoneInformation();
                    break;
                case (3):
                    //turnOff();
                    System.out.println("See you late!");
                    clearConsoleIDE();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!!!");
                    break;
            }//switch-case choice
        } //while (running)

    } //displayWelcomeMenu()

}//class 
