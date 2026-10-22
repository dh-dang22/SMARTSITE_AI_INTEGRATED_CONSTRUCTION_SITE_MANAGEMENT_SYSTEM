
   package ui_Dang_dang_sua;

import java.util.Scanner;
import Person.Person;
import Person.PersonManager;
import Attendance.AttendanceManager;
import java.io.IOException;
import Validatable.CheckPassword;
import Validatable.InputScanner;
import static ui_Dang_dang_sua.Draft.clearConsoleIDE;
import static ui_Dang_dang_sua.Draft.displayMenuGreeting;
import static ui_Dang_dang_sua.Draft.displayMenuHeader;
import static ui_Dang_dang_sua.Draft.displayMenuLine;
   
public class LoginMenu {
    private PersonManager pm = PersonManager.getInstance(); // Khai bao them doi tuong pm moi
    private AttendanceManager am;
    private final CheckPassword checker = new CheckPassword();
    private boolean loadPeople;
    private static boolean peopleLoaded;

    // Constructor mặc định tự khởi tạo Manager nếu chưa có
    public LoginMenu() {
        this.pm = PersonManager.getInstance(); 
        this.am = MenuSession.ATTENDANCE;
        this.loadPeople = true;
    }

    public LoginMenu(PersonManager pm, AttendanceManager am) {
        this.pm = pm;
        this.am = am;
    }  
    
    public void displayLoginMenu() {
        // Đọc dữ liệu trước khi nhận thông tin đăng nhập.
        try {
            if (loadPeople && !peopleLoaded) {
                pm.loadFromFile("data/Person.txt");
                peopleLoaded = true;
                loadPeople = false;
            }
            checker.loadFromFile("data/accounts.txt");
        } catch (IOException e) {
            System.out.println("[!] Cannot load login data: " + e.getMessage());
            return;
        }
        Login login = new Login(pm, checker);
        Scanner sc = InputScanner.getScanner();
        boolean running = true;
        while (running){
            displayMenuLine();//in gạch
            displayMenuHeader();//in banner
            displayMenuLine();//in gạch
            displayMenuGreeting();//in lời chào
            displayMenuLine();//in gạch
            System.out.println("");
            System.out.println("                            [!] NOTICE: Please enter your code and password below!!!");
            System.out.println("");
            // String code 
            System.out.print("         >>> Enter your code: ");
            if (!sc.hasNextLine()) return;
            String code = sc.nextLine().trim();

            /* if (!CheckPassword.checrekCode(code)) {
            System.out.println("[!]Invalid code format");
            continue;
            }*/

            System.out.print("         >>> Enter your password: ");
            if (!sc.hasNextLine()) return;
            String password = sc.nextLine();

            /*if (password.isEmpty()) {
            System.out.println("[!] Password is wrong");
            continue;
            }*/
            Person currentUser = login.login(code, password);

              if (currentUser == null) {
                System.out.println("Dang nhap that bai!");
                Draft.pause();
                continue;
            }
            System.out.println(Draft.GRN+"\n                                               [+] Login successful!"+Draft.BAS);
            System.out.println("                                        [+] Welcome, " +Draft.BLU+ currentUser.getFullName() +Draft.BAS+ " [" + currentUser.getRole() + "]");
            System.out.println("");
            Draft.displayMenuLine();
            Draft.pause();
            Draft.clearConsoleIDE();
            
            String role = currentUser.getRole();
            if (role == null) {
                System.out.println(Draft.RED+"                                [!] Account has no role assigned."+Draft.BAS);
                return;
            }
        // Điều hướng Menu theo Role
            switch (role.trim().toUpperCase()) {
                case "SITE_MANAGER":
                    clearConsoleIDE();
                    new UserSiteManagerMenu().displayMenuLv(currentUser, pm, am);
                    running = false;
                    break;
                case "SAFETY_OFFICER":
                    clearConsoleIDE();
                    new UserSafetyOfficerMenu().displayMenuLv(currentUser, pm, am);
                    running = false;
                    break;
                case "WORKER":
                    clearConsoleIDE();
                    new UserWorkerMenu().displayMenuLv(currentUser, pm, am);
                    running = false;
                    break;
                case "CONTRACTOR":
                    clearConsoleIDE();
                    new UserContractorMenu().displayMenuLv(currentUser, pm, am);
                    running = false;
                    break;
                case "VISITOR":
                    clearConsoleIDE();
                    new UserVisitorMenu().displayMenuLv(currentUser, pm, am);
                    running = false;
                    break;
                default:
                    System.out.println(Draft.RED+"                                [!] Unknown role: " + role + Draft.BAS);
                    ui_Dang_dang_sua.Draft.pause();
            }
        }//while running for menu
    }//displayLoginMenu
}//class
