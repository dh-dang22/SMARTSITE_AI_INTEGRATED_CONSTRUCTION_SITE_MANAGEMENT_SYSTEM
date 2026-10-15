
   package ui;

import file.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import smartsite.Person;
import smartsite.PersonManager;
import smartsite.AttendanceManager;
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
       /*private final PersonManager pm;
    private final AttendanceManager am;

<<<<<<< Updated upstream
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
            
            //String role = getRole(userName, password); //kiểm tra role để gọi menu tương ứng
            //getRole()
            String role = "";
            switch (userName){
                case ("Dang"):
                    role = "Site Manager";
                    break;
                case ("Khang"):
                    role = "Worker";
                    break;
                case ("Huy"):
                    role = "Visitor";
                    break;
                default:
                    System.out.println("Account not found!");
            }
            
            switch (role){
                case ("Visitor"):
                    MenuUserLv03 menuVisitor = new MenuUserLv03();
                    menuVisitor.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Worker"):
                    MenuUserLv03 menuWorker = new MenuUserLv02();
                    menuWorker.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Contractor"):
                    MenuUserLv02 menuContractor = new MenuUserLv02();
                    menuContractor.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Safety Officier"):
                    MenuUserLv02 menuSafetyOfficer = new MenuUserLv02();
                    menuSafetyOfficer.displayMenuLv(role, userName);
                    running = false;
                    break;
                case ("Site Manager"):
                    MenuUserLv01 menuSiteManager = new MenuUserLv01();
                    menuSiteManager.displayMenuLv(role, userName);
                    running = false;
                    break;
            }//switch
        
        }//while(running)
    }//dislplayLoginMenu()
=======
    public LoginMenu(PersonManager pm,
                     AttendanceManager am) {
        this.pm = pm;
        this.am = am;
    }

    public void displayLoginMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== SMARTSITE LOGIN ===");
        System.out.print("Enter your code: ");

        String code = sc.nextLine().trim();

        Person currentUser = pm.login(code);

        if (currentUser == null) {
            System.out.println("Invalid code or inactive account.");
            return;
        }

        System.out.println("Login successful!");
        System.out.println("Welcome: "
                + currentUser.getFullName());

        String role = currentUser.getRole();

        if (role == null) {
            System.out.println("Account has no role.");
            return;
        }

        switch (role.trim().toUpperCase()) {

            case "SAFETY_OFFICER":
            case "SITE_MANAGER":
                new MenuUserLv01()
                    .displayMenuLv01(currentUser, am);
                break;

            case "WORKER":
            case "CONTRACTOR":
                new MenuUserLv02()
                    .displayMenuLv02(currentUser, am);
                break;

            case "VISITOR":
                new MenuUserLv03()
                    .displayMenuLv03(currentUser, am);
                break;

            default:
                System.out.println("Unsupported role: " + role);
        }
    }
>>>>>>> Stashed changes
}//class */
public class LoginMenu {
    private final PersonManager pm;
    private final AttendanceManager am;

    public LoginMenu(PersonManager pm, AttendanceManager am) {
        this.pm = pm;
        this.am = am;
    }

    public void displayLoginMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("          SMARTSITE LOGIN              ");
        System.out.println("========================================");
        System.out.print("Enter your personnel code (e.g., W001, V001, S001): ");
        String code = sc.nextLine().trim();

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
             ArrayList<String> persons = FileManager.readFile();
                for (String person : persons) {
            System.out.println(person);
                }
            case "SAFETY_OFFICER":
                new MenuUserLv01().displayMenuLv(currentUser, pm, am);
                break;

            case "WORKER":
            case "CONTRACTOR":
                new MenuUserLv02().displayMenuLv(currentUser, pm, am);
                break;

            case "VISITOR":
                new MenuUserLv03().displayMenuLv(currentUser, pm, am);
                break;

            default:
                System.out.println("[!] Unknown role: " + role);
                ui.Draft.pause();
        }
    }
}
