    package ui_Dang_dang_sua;
import Person.Person;
import Person.PersonManager;
import Person.Worker;
import Person.Visitor;
import Person.SafetyOfficer;
import Person.SiteManager;
import Person.AddPerson;
import Person.PersonManager;
import static ui_Dang_dang_sua.Draft.pause;
import java.util.Scanner;
import smartsite.CheckInput;

public class UniqueSiteManagerMenu {
    private final Scanner sc = CheckInput.getScanner();
   // AddPerson và UniqueSiteManagerMenu: lấy cùng danh sách
    private final PersonManager pm = PersonManager.getInstance();

    UniqueSiteManagerMenu(PersonManager pm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void displayManagerFeature(){
        boolean running = true;
        while (running){
            System.out.println("1. Add a person");
            System.out.println("2. Add a list of people");
            System.out.println("3. Delete a person");
            System.out.println("4. Display a person information");
            System.out.println("5. Display all people information"); //
            System.out.println("6. Display all Attendance Report"); //check-in check-out k cho can thiệp chỉnh sửa
            System.out.println("7. Display all Incident Report"); //safety phụ trách
            System.out.println("0. Back to general menu");
            System.out.println("");
            System.out.println("Please enter your action: ");
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid choice! Please enter a number.");
                pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("The system is backing to general menu ...");
                pause();
            } else {
                switch(choice){
                    case (1):
                        AddPerson ap = new AddPerson();
                        ap.addPersonFromInput();       
                        pause();
                        break;
                    case (2):
                        System.out.println("Function is not completed yet ...");
                        pause();
                        break;
                    case (3):
                        System.out.println("Function is not completed yet ...");
                        pause();
                        break;
                    case (4):
                        System.out.println("Function is not completed yet ...");
                        pause();
                        break;
                    case (5):
                        pm.displayAll();
                        pause();
                        break;
                    case (6):
                        System.out.println("Function is not completed yet ...");
                        pause();
                        break;
                    case (7):
                        System.out.println("Function is not completed yet ...");
                        pause();
                        break;
                }
            }
        }
    }
    
}