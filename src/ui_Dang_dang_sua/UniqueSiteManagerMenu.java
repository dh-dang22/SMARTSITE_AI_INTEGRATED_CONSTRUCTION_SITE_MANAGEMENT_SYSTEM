    package ui_Dang_dang_sua;
    
import Person.AddPerson;
import java.util.Scanner;
import smartsite.CheckInput;
import Person.PersonManager;
    
public class UniqueSiteManagerMenu {
    private final Scanner sc = CheckInput.getScanner();
   // AddPerson và UniqueSiteManagerMenu: lấy cùng danh sách
    private final PersonManager pm = PersonManager.getInstance();

    UniqueSiteManagerMenu() {
    }
    
    public void displayManagerFeature(){
        boolean running = true;
        while (running){
            System.out.println("");
            System.out.println("    [1] ➔ Add Person(s) to system");
            System.out.println("    [2] ➔ Find a Person information");
            System.out.println("    [3] ➔ Update a Person information");
            System.out.println("    [4] ➔ Delete a Person");
            System.out.println("    [5] ➔ Display all people information"); //
            System.out.println("");
            System.out.println("    [0] ➔ Back to general menu");
            System.out.println("");
            System.out.println("    ➔ Please enter your action [0-7]: ");
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid choice! Please enter a number.");
                Draft.pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("The system is backing to general menu ...");
                Draft.pause();
            } else {
                switch(choice){
                    case (1): // add
                       int n = CheckInput.readRange(sc,"    - Enter number of people you want to add [1-10]:", 1, 10, "Please enter the number in range 1-10!");
                        for (int i = 1; i<=n; i++){
                            AddPerson apM = new AddPerson();
                            apM.addPersonFromInput();   
                            Draft.clearConsoleIDE();
                        }
                        Draft.pause();
                    case (2): // find
                        System.out.println("    a. Search by ID");
                        System.out.println("    b. Search by code");
                        System.out.println("    - Enter the methods you want to use to search[1-2]: ");
                        int findChoice = 1;
                        try {
                            // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                            choice = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("[!] Invalid choice! Please enter a number.");
                            Draft.pause();
                            continue;
                        }
                        switch (findChoice) {
                            case 1:
                                System.out.println("    - Enter the Person ID you want to search: ");
                                String tempID = "";
                                
                                PersonManager findID = new PersonManager();
                                findID.findPersonByID(tempID);
                                break;
                            case 2:
                                System.out.println("    - Enter the Person ID you want to search: ");
                                String tempCode = "";
                                
                                PersonManager findCode = new PersonManager();
                                findCode.findPersonByCode(tempCode);
                                break;                            
                            default:
                                System.out.println("    - ERROR!!! ");
                                break;
                        }
                        Draft.clearConsoleIDE();
                        break;

                    case (3): //update
                        Draft.functionIsNotCompletedYet();
                        Draft.clearConsoleIDE();
                        break;
                    case (4): // delete
                        Draft.functionIsNotCompletedYet();
                        Draft.clearConsoleIDE();
                        break;
                    case (5): // display list
                        pm.displayAll();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                }
            }
        } //while
    }//display
    
}//class