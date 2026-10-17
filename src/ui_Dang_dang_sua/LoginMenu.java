
   package ui_Dang_dang_sua;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Person.Person;
import Person.PersonManager;
import Attendance.AttendanceManager;
//public class LoginMenu {
    /*public void displayLoginMenu(){
    Scanner sc = new Scanner(System.in);
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
    System.out.println("        [!] NOTICE: Please enter your user name and password below!!!");
    System.out.println("");
    System.out.println("   >>> Enter your username  ");
    String userName = sc.nextLine();
    System.out.println("   >>> Enter your password  ");
    String password = sc.nextLine();
    System.out.println("User name: " + userName + "; password: " + password);
    
    //getRole()
    String role = "Visitor";
    //String role = getRole(userName, password); //kiểm tra role để gọi menu tương ứng
    switch (role){
    case ("Visitor"):
    MenuUserLv03 menuVisitor = new MenuUserLv03();
    menuVisitor.displayMenuLv03(role);
    case ("Worker"):
    MenuUserLv03 menuWorker = new MenuUserLv03();
    menuWorker.displayMenuLv03(role);
    case ("Contractor"):
    MenuUserLv03 menuContractor = new MenuUserLv03();
    menuContractor.displayMenuLv03(role);
    case ("Safety Officier"):
    MenuUserLv03 menuSafetyOfficer = new MenuUserLv03();
    menuSafetyOfficer.displayMenuLv03(role);
    case ("Site Manager"):
    MenuUserLv03 menuSiteManager = new MenuUserLv03();
    menuSiteManager.displayMenuLv03(role);
    }
    
    }//while(running)
    }*///dislplayLoginMenu()


public class LoginMenu {
    private PersonManager pm;
    private AttendanceManager am;

    // Constructor mặc định tự khởi tạo Manager nếu chưa có
    public LoginMenu() {
        this.pm = new PersonManager(); // Hoặc cách khởi tạo tương ứng trong project của bạn
        this.am = new AttendanceManager();
    }

    public LoginMenu(PersonManager pm, AttendanceManager am) {
        this.pm = pm;
        this.am = am;
    }  
    
    public void displayLoginMenu() {
        Scanner sc = new Scanner(System.in);
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
            System.out.println("        [!] NOTICE: Please enter your user name and password below!!!");
            System.out.println("");
            System.out.print("   >>> Enter your username  ");
            String code = sc.nextLine();
            System.out.print("   >>> Enter your password  ");
            String password = sc.nextLine();
            System.out.print("User code: " + code + "; password: " + password);

            Person currentUser = pm.login(code);
            if (currentUser == null) {
                System.out.println("[!] Invalid code or account is inactive!");
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