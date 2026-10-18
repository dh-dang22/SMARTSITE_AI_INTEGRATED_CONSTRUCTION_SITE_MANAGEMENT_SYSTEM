
package ui_Dang_dang_sua;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Draft {
    
    public static void clearConsoleIDE() { //in dòng trống để làm mới menu (lụm trên Gemini)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    public static void displayMenuLine(){
        System.out.println("========================================================================================================================");
    }
    
    public static void displayMenuHeader(){
        String header = 
            "  _  __                                     ____                                      _   _             \n" +
            " | |/ /  _ __     __ _   _ __     __ _     |  _ \\    __ _   _ __     __ _            | | | |  _   _  _  __\n" +
            " | ' /  | '_ \\   / _` | | '_ \\   / _` |    | | | |  / _` | | '_ \\   / _` |           | |_| | | | | | | |/ /\n" +
            " | . \\  | | | | | (_| | | | | | | (_| |    | |_| | | (_| | | | | | | (_| |           |  _  | | |_| | |   < \n" +
            " |_|\\_\\ |_| |_|  \\__,_| |_| |_|  \\__, |    |____/   \\__,_| |_| |_|  \\__, |           |_| |_|  \\__,_| |_|\\_\\\n" +
            "                                 |___/                              |___/                                 \n";
        System.out.println(header);
    }
    
    public static void displayMenuGreeting(){
            System.out.println("                                         WELCOME TO THE SSMARTSITE");
            System.out.println("                                      Version 2.4.3 | Terminal Edition"); //Version Semester.Lab.TimeConfig
            // Forrmateed HH/MM/SS: Giờ:Phút:Giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                LocalTime now = LocalTime.now();
                // In ghi đè lên dòng cũ bằng '\r' trên Console/Terminal
            System.out.println("\r                                            Time: " + now.format(formatter));  
    }
    
    public static void displayWelcomeMenuOption(){
        String option = 
            "               [!] NOTICE: The system is ready to work. Please choose one of the option below\n\n\n" +
            "                          +-------------------------------------------------+\n" +
            "                           | 1. Sign in                                      |\n" +
            "                           | 2. View area information                        |\n" +
            "                           | 3. Exit                                         |\n" +
            "                          +-------------------------------------------------+\n\n\n";
        System.out.println(option);
    }
    
    
    
    
    //displayWelcomeMenu(); hiển thị Welcome Menu
    
    //displayLoginMenu; hiển thị Login Menu
    
    //displayMenuLv(): hiển thị Menu chung chứa các chức năng
    //displayHeader(); hiển thị Header của Menu
    //displayOption(); hiển thị các lựa chọn tùy thuộc vào role
    //displayLogOut(); hiển thị chức năng Logout
    //chooseOption(); xử lý lựa chọn chức năng


    public static void pause() { // ngưng màn hình
        System.out.println("\n      Press [ENTER] to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}

