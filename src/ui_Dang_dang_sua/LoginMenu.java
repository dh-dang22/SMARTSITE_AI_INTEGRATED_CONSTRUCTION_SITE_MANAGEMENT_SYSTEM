
   package ui_Dang_dang_sua;

import java.util.Scanner;
import Person.Person;
import Person.PersonManager;
import Attendance.AttendanceManager;
import java.io.IOException;
import smartsite.CheckInput;
import smartsite.CheckPassword;
import static ui_Dang_dang_sua.Draft.clearConsoleIDE;
import static ui_Dang_dang_sua.Draft.displayMenuGreeting;
import static ui_Dang_dang_sua.Draft.displayMenuHeader;
import static ui_Dang_dang_sua.Draft.displayMenuLine;
   
public class LoginMenu {
    private PersonManager pm = PersonManager.getInstance();
    private AttendanceManager am;
    private final CheckPassword checker = new CheckPassword();
    private boolean loadPeople;

    // Constructor mặc định tự khởi tạo Manager nếu chưa có
    public LoginMenu() {
        this.pm = new PersonManager(); // Hoặc cách khởi tạo tương ứng trong project của bạn
        this.am = new AttendanceManager();
        this.loadPeople = true;
    }

    public LoginMenu(PersonManager pm, AttendanceManager am) {
        this.pm = pm;
        this.am = am;
    }  
    
    public void displayLoginMenu() {
        // Đọc dữ liệu trước khi nhận thông tin đăng nhập.
        try {
            if (loadPeople) {
                pm.loadFromFile("data/Person.txt");
                loadPeople = false;
            }
            checker.loadFromFile("data/accounts.txt");
        } catch (IOException e) {
            System.out.println("[!] Cannot load login data: " + e.getMessage());
            return;
        }
        Login login = new Login(pm, checker);
        Scanner sc = CheckInput.getScanner();
        boolean running = true;
        while (running){
            displayMenuLine();//in gạch
            displayMenuHeader();//in banner
            displayMenuLine();//in gạch
            displayMenuGreeting();//in lời chào
            displayMenuLine();//in gạch
            System.out.println("");
            System.out.println("                                    [!] NOTICE: Please enter your code and password below!!!");
            System.out.println("");
            System.out.print("         >>> Enter your code: ");
            if (!sc.hasNextLine()) return; //xóa dữ liệu đệm, tránh lỗi sc.nextLine()
            String code = sc.nextLine();
            System.out.print("         >>> Enter your password:  ");
            if (!sc.hasNextLine()) return;
            String password = sc.nextLine();

            Person currentUser = login.login(code, password);
            if (currentUser == null) {
                System.out.println("                                [!] Invalid code or password, or account is inactive!");
                Draft.pause();
                return;
            }

            System.out.println("\n                              [+] Login successful!");
            System.out.println("                            [+] Welcome, " + currentUser.getFullName() + " [" + currentUser.getRole() + "]");
            System.out.println("");
            Draft.displayMenuLine();
            Draft.clearConsoleIDE();
            
            String role = currentUser.getRole();
            if (role == null) {
                System.out.println("[!] Account has no role assigned.");
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
                    System.out.println("[!] Unknown role: " + role);
                    ui_Dang_dang_sua.Draft.pause();
            }
        }//while running for menu
    }//displayLoginMenu
}//class
