package smartsite;
import java.io.IOException;
import ui.WelcomeMenu;

/**
 *
 * @author Minh_Khang
 */
public class Main {

    public static void main(String[] args) throws IOException {
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
        WelcomeMenu welcomemenu = new WelcomeMenu(); //welcome.displayWelcomeMenu();
        welcomemenu.displayWelcomeMenu();
        // Dang nhap thanh cong de lay duoc currentUse
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