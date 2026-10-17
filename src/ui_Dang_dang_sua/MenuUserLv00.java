package ui_Dang_dang_sua;

import java.util.Scanner;
import static ui_Dang_dang_sua.Draft.pause;
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

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayHeader();                            // Hiển thị lời chào và role
            displayOption();                            // Hiển thị danh sách chức năng (lớp con tự định nghĩa)
            displayLogOut();                            // Hiển thị: 0. Log out
            
            System.out.print("Your action: ");
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
                System.out.println("The system is logging out...");
                pause();
            } else {
                chooseOption(choice);                   // Xử lý chức năng đã chọn
            }
        } // while
    } // displayMenuLv

    public void displayHeader() {
        String role = currentUser.getRole();
        String name = currentUser.getFullName();

        System.out.println("---------------- " + role + " Menu ----------------");
        System.out.println("");
        System.out.println("Welcome " + role + ": " + name + "!");
        System.out.println("What do you want to do?");
    }

     abstract void displayOption();

    public void displayLogOut() {
        System.out.println("0. Log out");
    }

     abstract void chooseOption(int choice);

} // class