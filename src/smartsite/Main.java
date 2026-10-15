package smartsite;
import ui.WelcomeMenu;
import java.util.Scanner;
/**
 *
 * @author Minh_Khang
 */
public class Main {

    public static void main(String[] args) {
   /* PersonManager pm = new PersonManager(5);
    
    System.out.println("=== DEMO PERSON MANAGER ===");
    
    System.out.println("\n1. Add persons");
    boolean add1 = pm.addPerson(new Worker("P001", "Tran Minh Khang", "W001", "WORKER", "ACTIVE", "BuildCo", "Full-time"));
    boolean add2 = pm.addPerson(new Visitor("P002", "Bach Gia Huy", "V001", "VISITOR", "ACTIVE", "Site Visit", "Manager A"));
    boolean add3 = pm.addPerson(new Person("P003", "Dang Hai Dang", "S001", "SAFETY_OFFICER", "ACTIVE"));
    
    System.out.println("Add P001: " + add1);
    System.out.println("Add P002: " + add2);
    System.out.println("Add P003: " + add3);
    
    System.out.println("\n2. Display all people");
    pm.displayAll();
    
    System.out.println("\n3. Search person by ID");
    Person found = pm.findPersonByID("P001");
    if (found != null) {
    System.out.println("Found: " + found);
    } else {
    System.out.println("Person not found.");
    }
    
    System.out.println("\n4. Add duplicate ID");
    boolean duplicate = pm.addPerson(new Person("P001", "Duplicate Person", "D001", "WORKER", "ACTIVE"));
    System.out.println("Add duplicate P001: " + duplicate);
    
    System.out.println("\n5. Sort by name");
    boolean sorted = pm.sortByName();
    System.out.println("Sort result: " + sorted);
    pm.displayAll();
    
    System.out.println("\n6. Login demo");
    Person currentUser = pm.login("W001");
    if (currentUser != null) {
    System.out.println("Login successful: " + currentUser.getFullName());
    } else {
    System.out.println("Login failed.");
    }
    }*/
                    //Ham checkout va doi tuong check out
    Scanner sc= new Scanner(System.in);
    PersonManager pm = new PersonManager();
     AttendanceManager am = new AttendanceManager();
     //Gia tr? checkin bang code
    pm.addPerson(new Worker("P001", "Tran Minh Khang", "W001", "WORKER", "ACTIVE", "BuildCo", "Full-time"));
    pm.addPerson(new Visitor("P002", "Bach Gia Huy", "V001", "VISITOR", "ACTIVE", "Site Visit", "Manager A"));
    pm.addPerson(new SafetyOfficer("P003", "Dang Hai Dang", "S001", "SAFETY_OFFICER", "ACTIVE", 2, "LIC-01"));

        WelcomeMenu welcome = new WelcomeMenu(pm, am);
        welcome.displayWelcomeMenu();
    // Khoi tao doi tuong quan ly de diem danh
    System.out.print("Nhap code: ");
    String code = sc.nextLine();    
    // Dang nhap thanh cong de lay duoc currentUser
    Person currentUser = pm.login(code);
    if(currentUser==null){
            System.out.println("Ðang nhap that bai");
    }
    else{
        System.out.println("Ðang nhap thanh cong:" +currentUser.getFullName());
    }
    //Sau khi dang nhap thanh cong thi co the check out
    /*  boolean isSuccess = am.checkOut(code);
    if (isSuccess){
    System.out.println("Checkout thanh cong");
    }
    else
    {
    System.out.println("Chua checkint");
    }*/
}
}