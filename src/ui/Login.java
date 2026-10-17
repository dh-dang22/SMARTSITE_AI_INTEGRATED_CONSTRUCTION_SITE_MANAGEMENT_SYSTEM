package ui;
import Person.Person;
import Person.PersonManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Minh_Khang
 */
public class Login {
    
    private final PersonManager pm;
    private final CheckPassword checker;

    public Login(PersonManager pm, CheckPassword checker) {
        this.pm = pm;
        this.checker = checker;
    }

    public Person login(String code, String password) {
        if (code == null || code.trim().isEmpty()
                || password == null || password.isEmpty()) {
            return null;
        }

        if (!checker.check(code, password)) {
            return null;
        }

        // Hàm này đã kiểm tra code và trạng thái ACTIVE.
        return pm.login(code);
    }
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
    MenuUserLv00 menuVisitor = new MenuUserLv00();
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
    
    //while(running)
    

}
