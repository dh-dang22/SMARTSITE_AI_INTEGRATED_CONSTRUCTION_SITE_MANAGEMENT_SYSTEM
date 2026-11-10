package ui_Dang_dang_sua;

import account.AccountManager;
import java.util.Scanner;

import Attendance.AttendanceManager;
import Person.PersonManager;
import Validatable.InputScanner;
import Zone.Zone;
import Zone.ZoneManager2;
import Incident.IncidentManager;
//import Zone.ZoneManager2;
public class UniqueSiteManagerMenu {

    private final Scanner sc2 = new Scanner(System.in);
    // AddPerson và UniqueSiteManagerMenu: lấy cùng danh sách
    private final PersonManager pm;
    ZoneManager2 zm = ZoneManager2.getInstance();
    Zone activeZone = zm.getRandomActiveZone();
    AttendanceManager attendanceManager = AttendanceManager.getInstance();

    UniqueSiteManagerMenu() {
        this(PersonManager.getInstance());
    }

    UniqueSiteManagerMenu(PersonManager pm) {
        this.pm = pm;
    }

    public void displayHumanResourceManagementMenu() {
        boolean running = true;
        while (running) {
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
            System.out.println("    [7]  Save to file");
            System.out.println("");
            System.out.println("    [0]  Back to general menu");
            System.out.println("");
            System.out.print(Draft.BLU + "    ➔ Please enter your action [0-7]: " + Draft.BAS);
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                if (!sc2.hasNextLine()) {
                    return;
                }
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED + "[!] Invalid choice! Please enter a number!" + Draft.BAS));
                Draft.pause();
                continue;
            }
            Draft.displayMenuLine();

            if (choice == 0) {                          // Thoát menu
                PersonManager.getInstance().saveToFile();
                running = false;
                System.out.println("    ➔ The system is backing to general menu ...");
            } else {
                switch (choice) {
                    case (1): // thêm Person (đã chỉnh, chưa ổn) (quên chưa ổn chỗ nào cmnr)
                        int n = InputScanner.readRange(sc2, Draft.BLU + "   ➔ Enter a number of Person(s) you want to add[1-10][-1 to cancel]: " + Draft.BAS, 1, 10, "Please enter a number between 1-10!");
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
                    case 2: // tìm kiếm Person (chưa chỉnh, chưa ổn) (hàm tìm kiếm trả giá trị Person, hok chỉnh layout đc, yêu cầu viết hàm trả giá trị void)
                        pm.searchPersonFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case (3): // cập nhật Person (chưa chỉnh, chưa ổn) (chưa viết hàm chỉnh sửa)
                        pm.updatePersonById();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 4: // xóa Person (chưa chỉnh, chưa ổn) (quên chưa ổn chỗ nào cmnr)
                        pm.deletePersonFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case (5): // hiển thị danh sách (đã chỉnh, chưa ổn) (đề xuất hiển thị thêm code kế bên ID, sửa lỗi license của SO dính vào status)
                        pm.displayAllByGehihi();
                        Draft.displayMenuLine();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 6: // sắp xếp danh sách (đã chỉnh, chưa ổn) (ở phần sort theo role, cho chọn role sort ra
                        pm.sortFromInput();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                        break;
                    case 7 :
                        pm.saveToFile();
                        Draft.pause();
                        Draft.clearConsoleIDE();
                }
            }
        } //while
    }//display

    public void displayZoneManagementMenu() {
        ZoneManager2 tempZone = ZoneManager2.getInstance();
        Scanner tempSC = new Scanner(System.in);
        boolean running = true;
        while (running) {
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
            System.out.println("    [5] Display Zone information");
            System.out.println("    [6] Sort Zones By ID");
            System.out.println("    [7] Save file");
            System.out.println("");
            System.out.println("    [0] Back to general menu");
            System.out.println("");
            System.out.print(Draft.BLU + "    ➔ Please enter your action [0-5]: " + Draft.BAS);
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                choice = Integer.parseInt(sc2.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.center(Draft.RED + "[!] Invalid choice! Please enter a number." + Draft.BAS));
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
                    case "n":
                        continue;
                    default:
                        System.out.println(Draft.RED + "Invalid choice!" + Draft.BAS);
                }

            } else {
                switch (choice) {
                    case (1): // thêm Zone (đã chỉnh, đã ổn) 
                        System.out.print("    ➔ Enter the type of Zone: " + Draft.GRN + "[1] NOR" + Draft.RED + " / " + "[2] RED" + Draft.BAS + " : ");
                        int choiceAdd = tempSC.nextInt();
                        switch (choiceAdd) {
                            case (1):
                                tempZone.addZone();
                                break;
                            case (2):
                                tempZone.addRestrictedZone();
                                break;
                        }
                        break;
                    case (2): // tìm kiếm khu vực (chưa chỉnh, chưa ổn) (viết lại, yêu cầu: viêt hàm tìm kiếm là void để ui chỉnh sửa đc, hàm giá trị Person chỉ dùng để gom giá trị tìm kiếm (trong Zalo có nói))
                        System.out.print("    ➔ Enter the Zone ID(e.g., ZN0001): ");
                        String tempID = tempSC.nextLine();
                        if (tempID != null) {
                            zm.displayFlex(tempID);
                        } else {
                            System.out.println();
                            System.out.println(Draft.center(Draft.RED + "[Error]: Zone ID [" + tempID + "] not found in system!" + Draft.BAS));
                        }
                        break;
                    case (3): // cập nhật khu vực (đã chỉnh, chưa ổn) (yêu cầu bổ sung chỉnh sửa Type và PIN code, tạo một switch case cho người dùng chọn thông tin chỉnh sửa)
                        tempZone.updateZoneById();
                        break;
                    case (4): // xóa khu vực (đã chỉnh, đã ổn)
                        tempZone.deleteZoneById();
                        break;
                    case (5): // hiển thị danh sách khu vực (đã chỉnh, đã ổn)
                        tempZone.displayOption();
                        break;

                    case (6): // sắp xếp danh sách khu vực (đã chỉnh, chưa ổn) (yêu cầu: Sắp xếp theo ID nhưng k làm thay đổi thứ tự gốc trong file) 
                        tempZone.sortZonesById();
                        break;
                    case (7): // lưu dữ liệu (chưa chỉnh, đã ổn)
                        tempZone.saveToFile();
                        break;
//                    case (9): // tải dữ liệu (chưa chỉnh, hok rõ)
//                        try {
//                            tempZone.loadFromFile("Zone.txt");
//                        } catch (IOException e) {
//                            System.out.println(Draft.center(Draft.RED+"[Error]: Failed to load file: " + e.getMessage()+Draft.GRN));
//                        }
//                        break;
                }
            }
            Draft.pause();
            Draft.clearConsoleIDE();
        } //while
    }//display

    public void displayAllAttendance() {
        AttendanceManager am = AttendanceManager.getInstance();
        am.displayAll();
    }//display

    public void displayAllIncident() {
        IncidentManager im = IncidentManager.getInstance();
        
        im.displayIncidents();
        
    }//display
    
    
    /// Hàm qu?n lý tài kho?n c?a admin
       public void manageAccounts() {
        try {
            while (true) {
                AccountManager standardAccounts = new AccountManager("data/accounts.txt");
                AccountManager adminAccounts = new AccountManager("data/admin_accounts.txt");

                displayAccountTable("STANDARD ACCOUNTS", false, standardAccounts);
                displayAccountTable("ADMIN ACCOUNTS", true, adminAccounts);

                System.out.println("[1] Add/reset account");
                System.out.println("[2] Delete account");
                System.out.println("[3] Check/refresh account status");
                System.out.println("[0] Back");
                System.out.print("Choose: ");
                if (!sc2.hasNextLine()) {
                    return;
                }
                String action = sc2.nextLine().trim();
                if ("0".equals(action)) {
                    return;
                }
                if ("3".equals(action)) {
                    continue;
                }
                if (!"1".equals(action) && !"2".equals(action)) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                System.out.println("[1] Standard account (accounts.txt)");
                System.out.println("[2] Admin account (admin_accounts.txt)");
                System.out.print("Choose account type: ");
                if (!sc2.hasNextLine()) {
                    return;
                }
                String accountType = sc2.nextLine().trim();
                if (!"1".equals(accountType) && !"2".equals(accountType)) {
                    System.out.println("Invalid account type.");
                    continue;
                }

                AccountManager accounts = "2".equals(accountType)
                        ? adminAccounts : standardAccounts;
                String accountFile = "2".equals(accountType)
                        ? "data/admin_accounts.txt" : "data/accounts.txt";

                String code;
                while (true) {
                    System.out.print("Enter person code: ");
                    if (!sc2.hasNextLine()) {
                        return;
                    }
                    code = sc2.nextLine().trim();
                    if (!code.isEmpty()) {
                        break;
                    }
                    System.out.println("Person code cannot be empty. Please try again.");
                }

                if ("1".equals(action)) {
                    Person.Person person = pm.findPersonByCode(code);
                    boolean correctRole = person != null
                            && ("2".equals(accountType)
                            ? "ADMIN".equalsIgnoreCase(person.getRole())
                            : !"ADMIN".equalsIgnoreCase(person.getRole()));
                    if (!correctRole) {
                        System.out.println("Person code not found or does not match the selected account type.");
                        continue;
                    }

                    String password;
                    while (true) {
                        System.out.print("Enter new password: ");
                        if (!sc2.hasNextLine()) {
                            return;
                        }
                        password = sc2.nextLine();
                        if (!password.trim().isEmpty()) {
                            break;
                        }
                        System.out.println("Password cannot be empty. Please try again.");
                    }

                    if (accounts.savePassword(code, password, pm)) {
                        System.out.println("Account saved.");
                    } else {
                        System.out.println("Could not save account.");
                    }
                } else if (accounts.deleteAccount(code)) {
                    System.out.println("Account deleted.");
                } else {
                    System.out.println("Account not found in " + accountFile + ".");
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Cannot update accounts.txt: " + e.getMessage());
        }
    }

    private void displayAccountTable(String title, boolean adminTable,
            AccountManager accounts) {
        System.out.println();
        System.out.println("==================== " + title + " ====================");
        System.out.printf("%-8s | %-18s | %-15s | %-15s%n",
                "ID", "Name", "Code", "Account status");
        System.out.println("---------------------------------------------------------------");

        boolean foundPerson = false;
        for (Person.Person person : pm.getAllPeople()) {
            boolean isAdmin = "ADMIN".equalsIgnoreCase(person.getRole());
            if (isAdmin != adminTable) {
                continue;
            }
            foundPerson = true;
            boolean hasAccount = accounts.hasAccount(person.getCode());
            String status = hasAccount
                    ? Draft.GRN + "HAS ACCOUNT" + Draft.BAS
                    : Draft.RED + "NO ACCOUNT" + Draft.BAS;
            System.out.printf("%-8s | %-18s | %-15s | %s%n",
                    person.getId(), person.getFullName(), person.getCode(), status);
        }
        if (!foundPerson) {
            System.out.println("No people in this account category.");
        }
        System.out.println("---------------------------------------------------------------");
    }
   
}//class    
