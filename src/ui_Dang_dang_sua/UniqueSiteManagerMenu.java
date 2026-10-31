    package ui_Dang_dang_sua;
import Person.Person;
import java.util.Scanner;
import Person.PersonManager;
import Person.Worker;
import Validatable.InputScanner;
import Zone.ZoneManager2;
import java.io.IOException;
//import Zone.ZoneManager2;
    
public class UniqueSiteManagerMenu {
   private final Scanner sc2 = new Scanner(System.in);
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
            System.out.println("");
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
            Draft.displayMenuLine();
            
            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("    ➔ The system is backing to general menu ...");
                Draft.pause();
                Draft.clearConsoleIDE();
            } else {
                switch(choice){
                    case (1): // add đã xong
                        int n = InputScanner.readRange(sc2, "   ➔ Enter a number of Person(s) you want to add[1-100][-1 to cancel]: ", 1, 100, "Please enter a number between 1-100!");
                        System.out.println("");
                        if (n == -1) {
                            return;
                        }
                        for (int i = 0; i < n; i++) {
                            Draft.displayMenuLine();
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
                    case 4: // delete đã xong
                        pm.deletePersonFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case (5): // display list đã xong
                        pm.displayAllByGehihi();
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
            System.out.println(Draft.center("Zone Management Menu"));
            System.out.println("");
            System.out.println("    [1] Add Zone(s) to system");
            System.out.println("    [2] Find a Zone information");
            System.out.println("    [3] Update a Zone information");
            System.out.println("    [4] Delete a Zone");
            System.out.println("    [5] Display all Zone information"); 
            System.out.println("    [6] Display only Restricted Zone information"); 
            System.out.println("    [7] Save file"); 
            System.out.println("    [8] Load file"); 
            System.out.println("");
            System.out.println("    [0] Back to general menu");
            System.out.println("");
            System.out.print(Draft.BLU+"    ➔ Please enter your action [0-5]: "+Draft.BAS);
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED+"[!] Invalid choice! Please enter a number."+Draft.BAS));
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
                    case (1): // add (NOR and RES) check
                        System.out.print("    ➔ Enter the type of Zone: [1] NOR / [2] RES : ");
                        int choiceAdd = tempSC.nextInt();
                        switch (choiceAdd){
                            case (1):
                                tempZone.addZone();
                                break;
                            case (2):
                                tempZone.addRestrictedZone();
                                break;
                        }
                        break;
                    case (2): // find
                        System.out.print("    ➔ Enter the Zone ID(e.g., ZN0001): ");
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
                   case (6):
                        tempZone.displayRestrictedZonesOnly();
                    case (7):
                        System.out.println(Draft.center("--- [TEST]: Save Data to File ---"));
                        tempZone.saveToFile();
                        break;
                    case (8):
                        System.out.println(Draft.center("--- [TEST]: Load Data from File ---"));
                        try {
                            tempZone.loadFromFile("Zone6"+ ".txt");
                        } catch (IOException e) {
                            System.out.println("[Error]: Failed to load file: " + e.getMessage());
                        }
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
    
 public void displayPersonInformation(Person person) {
    if (person == null) {
        System.out.println(Draft.center(Draft.RED+"Can not find the logged in Person."+Draft.BAS));
        return;
    }
    Draft.displayMenuLine();
     System.out.println("");
    System.out.println(Draft.center("=== ACCOUNT INFORMATION ==="));
     System.out.println("");
    System.out.println("    🆔  ID    : " + person.getId());
    System.out.println("    👤  Name  : " + person.getFullName());
    System.out.println("    💼  Role  : " + person.getRole());
    System.out.println("    🟢  Status: " + person.getStatus());
    //person.getId(), person.getFullName(), person.getRole(), person.getStatus()
    if (person instanceof Worker) {
        Worker worker = (Worker) person;

        // Thay bằng đúng getter có trong Worker.java
        System.out.println("    Company       : " + worker.getCompanyName());
        System.out.println("    Contract type : " + worker.getContractType());
    }
}
}//class
