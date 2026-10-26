package ui_Dang_dang_sua;

import java.util.Scanner;
import Person.Person;
import Person.PersonManager;
import Attendance.AttendanceManager;

public abstract class MenuUserLv00 {
    
    // Khai báo protected để tất cả các lớp con (Lv01, Lv02, Lv03) đều dùng chung được
    protected Person currentUser;
    protected PersonManager pm;
    protected AttendanceManager am;

    public void displayMenuLv(Person currentUser, PersonManager pm, AttendanceManager am) {
        // Lưu lại dữ liệu truyền vào
        this.currentUser = currentUser;
        this.pm = pm;
        this.am = am;

        Scanner sc = Validatable.InputScanner.getScanner();
        boolean running = true;

        while (running) {
            displayHeader();                            // Hiển thị lời chào và role
            displayOption();                            // Hiển thị danh sách chức năng (lớp con tự định nghĩa)
            System.out.println("");
            displayLogOut();                            // Hiển thị: 0. Log out
            
            System.out.print("    ➔ Please enter your action : ");
            int choice;
            try {
                // Đọc an toàn, tránh lỗi nuốt dòng và crash khi nhập chữ
                if (!sc.hasNextLine()) return;
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Draft.RED + "                                        Please enter a valid number!" + Draft.BAS);
                Draft.pause();
                continue;
            }

            if (choice == 0) {                          // Thoát menu
                running = false;
                System.out.println("    ➔ The system is logging out...");
                Draft.pause();
            } else {
                chooseOption(choice);                   // Xử lý chức năng đã chọn
            }
        } // while
    } // displayMenuLv

    public void displayHeader() {
        String userRole = currentUser.getRole();
        String userName = currentUser.getFullName();

        Draft.displayMenuLine();
        Draft.displayMenuHeader();
        Draft.displayMenuLine();
        Draft.displayMenuGreeting();
        Draft.displayMenuLine();
        System.out.println("");
        System.out.println("                                                " + userRole + " Menu ");
        System.out.println("");
        
    }

    protected boolean handleCommonOption(int choice) {
        switch (choice) {
            case 1: System.out.println(currentUser); break;
            case 2: am.checkInFromInput(currentUser, MenuSession.ZONES); break;
            case 3: am.checkOutFromInput(currentUser); break;
            case 4: am.displayHistory(currentUser.getId()); break;
            default: return false;
        }
        Draft.pause();
        Draft.clearConsoleIDE();
        return true;
    }

     abstract void displayOption();

    public void displayLogOut() {
        System.out.println("    [0] Log out");
    }

     abstract void chooseOption(int choice);

} // class