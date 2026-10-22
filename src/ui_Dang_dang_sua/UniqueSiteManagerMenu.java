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
            System.out.println("                                         Human Resource Management Menu ");
            System.out.println("");
<<<<<<< Updated upstream
            System.out.println("    [1]  Add Person(s) to system");
            System.out.println("    [2]  Find a Person information");
            System.out.println("    [3]  Update a Person information");
            System.out.println("    [4]  Delete a Person");
            System.out.println("    [5]  Display all people information"); //
=======
            System.out.println("    [1] ➔ Add Person(s) to system");
            System.out.println("    [2] ➔ Find a Person information");
            System.out.println("    [3] ➔ Update a Person information");
            System.out.println("    [4] ➔ Delete a Person");
            System.out.println("    [6] Sort people by name");
            System.out.println("    [7] Display role descriptions");
            System.out.println("    [5] ➔ Display all people information"); //
>>>>>>> Stashed changes
            System.out.println("");
            System.out.println("    [0]  Back to general menu");
            System.out.println("");
            System.out.print("    ➔ Please enter your action [0-7]: ");
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                if (!sc2.hasNextLine()) return;
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid choice! Please enter a number.");
                Draft.pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("                                The system is backing to general menu ...");
                Draft.pause();
                Draft.clearConsoleIDE();
            } else {
                switch(choice){
                    case (1): // add
<<<<<<< Updated upstream
                        int n = sc2.nextInt();
                        System.out.println("     Enter number of Person(s) you want to add: ");
                       for (int i = 1; i<=n; i++){
                            PersonManager apM = new PersonManager();
                            apM.addPersonFromInput();
                            Draft.clearConsoleIDE();
                        }
                        Draft.pause();
                        Draft.clearConsoleIDE();
                    case (2): // find
                        System.out.println("    a. Search by ID");
                        System.out.println("    b. Search by code");
                        System.out.println("    - Enter the methods you want to use to search[1-2]: ");
                        int findChoice = 1;
                        try {
                            // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                            choice = Integer.parseInt(sc2.nextLine().trim());
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
=======
                            int n = InputScanner.readRange(
        sc2,
        "Nhap so luong Person can them [1-100]: ",
        1,
        100,
        "Vui long nhap so nguyen tu 1 den 100!"
    );
    if (n == -1) {
        return;
    }
    for (int i = 0; i < n; i++) {
        System.out.println("--- Person " + (i + 1) + " ---");
        pm.addPersonFromInput();
    }
    Draft.pause();
    Draft.clearConsoleIDE();
    break;
                    case 2:
                        pm.findPersonFromInput();
>>>>>>> Stashed changes
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 6:
                        pm.sortByName();
                        pm.displayAll();
                        Draft.pause();
                        break;
                    case 7:
                        pm.displayRoleDescriptions();
                        Draft.pause();
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
                }
            }
        } //while
    }//display

    public void displayZoneManagementMenu(){
        ZoneManager2 tempZone = MenuSession.ZONES;
        Scanner tempSC = InputScanner.getScanner();
        boolean running = true;
        while (running){
            Draft.displayMenuLine();
            Draft.displayMenuHeader();
            Draft.displayMenuLine();
            Draft.displayMenuGreeting();
            Draft.displayMenuLine();
            System.out.println("                                                Zone Management Menu ");
            System.out.println("");
<<<<<<< Updated upstream
            System.out.println("    [1] Add Zone(s) to system");
            System.out.println("    [2] Find a Zone information");
            System.out.println("    [3] Update a Zone information");
            System.out.println("    [4] Delete a Zone");
            System.out.println("    [5] Display all Zone information"); //
=======
            System.out.println("    [1]  Add Zone(s) to system");
            System.out.println("    [2]  Find a Zone information");
            System.out.println("    [3]  Update a Zone information");
            System.out.println("    [4]  Delete a Zone");
            System.out.println("    [5]  Display all Zone information"); //
            System.out.println("    [6]  Add restricted zone(s)");
            System.out.println("    [7]  Display restricted zones");
>>>>>>> Stashed changes
            System.out.println("");
            System.out.println("    [0] ➔ Back to general menu");
            System.out.println("");
            System.out.print("    ➔ Please enter your action [0-7]: ");
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                if (!sc2.hasNextLine()) return;
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid choice! Please enter a number.");
                Draft.pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("                                The system is backing to general menu ...");
                Draft.pause();
                Draft.clearConsoleIDE();
                return;
            } else {
                switch(choice){
                    case 6:
                        tempZone.addRestrictedZone();
                        break;
                    case 7:
                        tempZone.displayRestrictedZonesOnly();
                        break;
                    case (1): // add
                        if (!sc2.hasNextLine()) return;
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
    
    
    
    
    
    
//    public void displayAllIncident(){
//        Incident.IncidentManager.displayIncidents();
//    }//display
}//class