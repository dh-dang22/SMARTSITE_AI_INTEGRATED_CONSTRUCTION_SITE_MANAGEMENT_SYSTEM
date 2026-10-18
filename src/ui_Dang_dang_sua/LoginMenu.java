
   package ui_Dang_dang_sua;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Person.Person;
import Person.PersonManager;
import Attendance.AttendanceManager;
import java.io.IOException;
import smartsite.CheckPassword;
public class LoginMenu {
    private PersonManager pm;
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
        Scanner sc = Draft.getScanner();
        boolean running = true;
        while (running){
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
            System.out.println("        [!] NOTICE: Please enter your code and password below!!!");
            System.out.println("");
            System.out.print("   >>> Enter your code: ");
            if (!sc.hasNextLine()) return; //xóa dữ liệu đệm, tránh lỗi sc.nextLine()
            String code = sc.nextLine();
            System.out.print("   >>> Enter your password:  ");
            if (!sc.hasNextLine()) return;
            String password = sc.nextLine();

            Person currentUser = login.login(code, password);
            if (currentUser == null) {
                System.out.println("[!] Invalid code or password, or account is inactive!");
                return;
            }

            System.out.println("\n[+] Login successful!");
            System.out.println("[+] Welcome, " + currentUser.getFullName() + " [" + currentUser.getRole() + "]");

            String role = currentUser.getRole();
            if (role == null) {
                System.out.println("[!] Account has no role assigned.");
                return;
            }
        // Điều hướng Menu theo Role
            switch (role.trim().toUpperCase()) {
                case "SITE_MANAGER":
                    new UserSiteManagerMenu().displayMenuLv(currentUser, pm, am);
                    break;
                case "SAFETY_OFFICER":
                    new UserSafetyOfficerMenu().displayMenuLv(currentUser, pm, am);
                    break;
                case "WORKER":
                    new UserWorkerMenu().displayMenuLv(currentUser, pm, am);
                    break;
                case "CONTRACTOR":
                    new UserContractorMenu().displayMenuLv(currentUser, pm, am);
                    break;
                case "VISITOR":
                    new UserVisitorMenu().displayMenuLv(currentUser, pm, am);
                    break;
                default:
                    System.out.println("[!] Unknown role: " + role);
                    ui_Dang_dang_sua.Draft.pause();
            }
        }//while running for menu
    }//displayLoginMenu
}//class
