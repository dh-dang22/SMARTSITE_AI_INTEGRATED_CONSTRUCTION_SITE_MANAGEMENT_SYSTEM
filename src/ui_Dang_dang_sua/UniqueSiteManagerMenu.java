    package ui_Dang_dang_sua;
import java.util.Scanner;
import Person.PersonManager;
import Validatable.InputScanner;
import Zone.ZoneManager2;
//import Zone.ZoneManager2;
    
public class UniqueSiteManagerMenu {
   private final Scanner sc2 = InputScanner.getScanner();
   // AddPerson và UniqueSiteManagerMenu: lấy cùng danh sách
    private final PersonManager pm;

    UniqueSiteManagerMenu() { this(PersonManager.getInstance()); }

    UniqueSiteManagerMenu(PersonManager pm) { this.pm = pm; }
    
    public void displayHumanResourceManagementMenu(){
        boolean running = true;
        while (running){
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            Draft.displayMenuLine();
            System.out.println(Draft.center("Human Resource Management Menu"));
            System.out.println("");
            System.out.println("    [1]  Add Person(s) to system");
            System.out.println("    [2]  Find a Person information");
            System.out.println("    [3]  Update a Person information");
            System.out.println("    [4]  Delete a Person");
            System.out.println("    [5]  Display all people information"); //
            System.out.println("    [6]  Sort people by name");
            System.out.println("    [7]  Display role descriptions");
            System.out.println("");
            System.out.println("    [0]  Back to general menu");
            System.out.println("");
            System.out.print(Draft.BLU+"    ➔ Please enter your action [0-7]: "+Draft.BAS);
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                if (!sc2.hasNextLine()) return;
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED+"[!] Invalid choice! Please enter a number!"+Draft.BAS));
                Draft.pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println(Draft.center("The system is backing to general menu ..."));
                Draft.pause();
                Draft.clearConsoleIDE();
            } else {
                switch(choice){
                    case (1): // add
                        int n = InputScanner.readRange(sc2, "Enter a number of Person(s) you want to add[1-100][-1 to cancel]: ", 1, 100, "Please enter a number between 1-100!");
                        if (n == -1) {
                            return;
                        }
                        for (int i = 0; i < n; i++) {
                            System.out.println(Draft.center("--- Person " + (i + 1) + " ---"));
                            pm.addPersonFromInput();
                        }
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 2:
                        pm.findPersonFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case (3): //update
                        Draft.functionIsNotCompletedYet();
                        break;
                   case 4: // delete
                        pm.deletePersonFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case (5): // display list
                        pm.displayAll();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 6:
                        pm.sortByName();
                        pm.displayAll();
                        Draft.pause();
                        break;
                    case 7:
                        //pm.displayRoleDescriptions();
                        Draft.functionIsNotCompletedYet();
                        break;                
                }
            }
        } //while
    }//display
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void displayZoneManagementMenu(){
        ZoneManager2 tempZone = new ZoneManager2();
        Scanner tempSC = new Scanner(System.in);
        boolean running = true;
        while (running){
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            Draft.displayMenuLine();
            System.out.println("                                                Zone Management Menu ");
            System.out.println("");
            System.out.println("    [1] Add Zone(s) to system");
            System.out.println("    [2] Find a Zone information");
            System.out.println("    [3] Update a Zone information");
            System.out.println("    [4] Delete a Zone");
            System.out.println("    [5] Display all Zone information"); //
            System.out.println("");
            System.out.println("    [0] Back to general menu");
            System.out.println("");
            System.out.print("    ➔ Please enter your action [0-7]: ");
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid choice! Please enter a number.");
                Draft.pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("The system is backing to general menu ...");
                Draft.pause();
                Draft.clearConsoleIDE();
            } else {
                switch(choice){
                    case (1): // add
                        tempZone.addZone();   
                        break;
                    case (2): // find
                        System.out.print("    Enter the Zone code: ");
                       
                        String tempID = tempSC.nextLine();
                        System.out.println(tempZone.findZoneById(tempID));
                        break;
                    case (3): //update
                        tempZone.updateZoneById();
                        break;
                    case (4): // delete
                        tempZone.deleteZoneById();
                        break;
                   case (5): // display list
                        tempZone.displayList();
                        break;
                }
            }
            Draft.pause();
            Draft.clearConsoleIDE();
        } //while
    }//display
    
    

    
    
    public void displayAllAttendance(){
        Draft.functionIsNotCompletedYet();
    }//display
    
    
    
    public void displayAllIncident(){
        Draft.functionIsNotCompletedYet();
    }//display
    
}//class