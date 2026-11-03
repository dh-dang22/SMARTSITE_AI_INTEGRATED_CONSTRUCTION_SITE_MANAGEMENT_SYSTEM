    package ui_Dang_dang_sua;
import java.util.Scanner;

import Attendance.AttendanceManager;
import Person.PersonManager;
import Validatable.InputScanner;
import Zone.Zone;
import Zone.ZoneManager2;
import java.io.IOException;
import javax.xml.transform.OutputKeys;


//import Zone.ZoneManager2;
    
public class UniqueSiteManagerMenu {
   private final Scanner sc2 = new Scanner(System.in);
   // AddPerson và UniqueSiteManagerMenu: lấy cùng danh sách
    private final PersonManager pm;
    ZoneManager2 zm = ZoneManager2.getInstance();
    Zone activeZone = zm.getRandomActiveZone();
    AttendanceManager attendanceManager = AttendanceManager.getInstance();
    
    UniqueSiteManagerMenu() { this(PersonManager.getInstance()); }

    UniqueSiteManagerMenu(PersonManager pm) { this.pm = pm; }
    
    public void displayHumanResourceManagementMenu(){
        boolean running = true;
        while (running){
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            if (activeZone != null) {
                System.out.println(Draft.center("CURRENT LOCATION: " + activeZone.getZoneName() + " (" + activeZone.getZoneId() + ")"));
            } else {
                System.out.println(Draft.center("CURRENT LOCATION: No active zone assigned"));
            }
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
                PersonManager.getInstance().saveToFile();
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
                        pm.sortFromInput();
                        pm.displayAllByGehihi();
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
            System.out.println("    [7] Sort Zones By ID"); 
            System.out.println("    [8] Save file"); 
            System.out.println("    [9] Load file"); 
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
            Draft.displayMenuLine();

            if (choice == 0) {                          // Thoát menu
                System.out.print(Draft.center("Have you saved your file yet? [Y/N]: "));
                String areYouSure = tempSC.nextLine();
                switch (areYouSure.toLowerCase()) {
                    case "y":
                        running = false;
                        System.out.println(Draft.center("The system is backing to general menu ..."));
                        Draft.displayMenuLine();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                    case "n":
                        continue;
                    default:
                        System.out.println(Draft.RED+"Invalid choice!"+Draft.BAS);
                }
                
            } else {
                switch(choice){
                    case (1): // add (NOR and RES) check
                        System.out.print("    ➔ Enter the type of Zone: " + Draft.GRN+ "[1] NOR" + Draft.RED + " / " + "[2] RED" + Draft.BAS + " :");
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
                        break;
                    case(7):
                        tempZone.sortZonesById();
                    case (8):
                        tempZone.saveToFile();
                        break;
                    case (9):
                        try {
                            tempZone.loadFromFile("Zone.txt");
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
    
}//class
