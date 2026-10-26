
package ui_Dang_dang_sua;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Validatable.InputScanner;
public class Draft {
    
    static final String RED = "\u001B[31m";
    static final String GRN = "\u001B[32m";
    static final String BLU = "\u001B[34m";
    static final String BAS = "\u001B[0m";
    
    //làm mới trang bằng cách in dòng mới
    public static void clearConsoleIDE() { //in dòng trống để làm mới menu (lụm trên Gemini)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    //làm mới màn hình (Gehihi gợi ý)
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    //ngưng màn hình đến khi ấn Enter
//    public static void pause() {
//        System.out.println("                                                                                        \nPress [ENTER] to continue...");
//
//        Scanner scanner = InputScanner.getScanner();
//        if (scanner.hasNextLine()) {
//            scanner.nextLine();
//        }
//    }
    
    public static void pause() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n                                            Press [ENTER] to continue...");
        scanner.nextLine();
    }
    
    public static void functionIsNotCompletedYet(){
        System.out.println(Draft.RED + "                                        Function is not completed yet!" + Draft.BAS);
        Draft.pause();
        Draft.clearConsoleIDE();
    };
    
    
    
    //in đường kẻ ngang
    public static void displayMenuLine(){
        System.out.println("========================================================================================================================");
    }
    
    //hiển thị banner (có màu)
    public static void displayMenuHeader(){

        String header = 
            RED+ "     _  __                                         "+BAS+""+BLU+"  ____                                     "+BAS+""+GRN+" _   _             \n" +
            GRN+"    | |/ /  _ __     __ _   _ __     __ _           "+BAS+""+RED+"|  _ \\    __ _   _ __     __ _            "+BAS+""+BLU+"| | | |  _   _  __  __\n" +
            BLU+"    | ' /  | '_ \\   / _` | | '_ \\   / _` |          "+BAS+""+GRN+"| | | |  / _` | | '_ \\   / _` |           "+BAS+""+RED+"| |_| | | | | | \\ \\/ /\n" +
            RED+"    | . \\  | | | | | (_| | | | | | | (_| |          "+BAS+""+BLU+"| |_| | | (_| | | | | | | (_| |           "+BAS+""+GRN+"|  _  | | |_| |   / / \n" +
            GRN+"    |_|\\_\\ |_| |_|  \\__,_| |_| |_|  \\__, |          "+BAS+""+RED+"|____/   \\__,_| |_| |_|  \\__, |           "+BAS+""+BLU+"|_| |_|  \\__,_|  /_/\n" +
            BLU+"                                    |___/                "+BAS+""+GRN+"                    |___/                   "+BAS+""+RED+"              \n"+BAS;
        System.out.println(header);
    }
    
//    public static void displayMenuHeaderOld(){
//        final String RED = "\u001B[31m";
//        final String GRN = "\u001B[32m";
//        final String BLU = "\u001B[34m";
//        final String RESET = "\u001B[0m";
//        String header = 
//            RED+ "     _  __                                         "+BLU+"  ____                                     "+GRN+" _   _             \n" +
//            GRN+"    | |/ /  _ __     __ _   _ __     __ _           "+RED+"|  _ \\    __ _   _ __     __ _            "+BLU+"| | | |  _   _  _  __\n" +
//            BLU+"    | ' /  | '_ \\   / _` | | '_ \\   / _` |          "+GRN+"| | | |  / _` | | '_ \\   / _` |           "+RED+"| |_| | | | | | \\ \\/ /\n" +
//            RED+"    | . \\  | | | | | (_| | | | | | | (_| |          "+BLU+"| |_| | | (_| | | | | | | (_| |           "+GRN+"|  _  | | |_| |  |  |< \n" +
//            GRN+"    |_|\\_\\ |_| |_|  \\__,_| |_| |_|  \\__, |          "+RED+"|____/   \\__,_| |_| |_|  \\__, |           "+BLU+"|_| |_|  \\__,_|    |_|\n" +
//            BLU+"                                    |___/                "+GRN+"                    |___/                   "+RED+"              \n";
//        System.out.println(header);
//    }
    
   //hiển thị banner (k màu)
//    public static void displayMenuHeaderTemp(){
//        String header = 
//            "     _  __                                           ____                                      _   _             \n" +
//            "    | |/ /  _ __     __ _   _ __     __ _           |  _ \\    __ _   _ __     __ _            | | | |  _   _  _  __\n" +
//            "    | ' /  | '_ \\   / _` | | '_ \\   / _` |          | | | |  / _` | | '_ \\   / _` |           | |_| | | | | | | |/ /\n" +
//            "    | . \\  | | | | | (_| | | | | | | (_| |          | |_| | | (_| | | | | | | (_| |           |  _  | | |_| | |   < \n" +
//            "    |_|\\_\\ |_| |_|  \\__,_| |_| |_|  \\__, |          |____/   \\__,_| |_| |_|  \\__, |           |_| |_|  \\__,_| |_|\\_\\\n" +
//            "                                    |___/                                    |___/                                 \n";
//        System.out.println(header);
//    }
    
    //hiển thị lời chào
    public static void displayMenuGreeting(){
            System.out.println("                                             WELCOME TO THE S-SMARTSITE");
            System.out.println("                                          Version 2.4.7 | Terminal Edition"); //Version Semester.Lab.TimeConfig
            // Forrmateed HH/MM/SS: Giờ:Phút:Giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                LocalTime now = LocalTime.now();
                // In ghi đè lên dòng cũ bằng '\r' trên Console/Terminal
            System.out.println("\r                                                Time: " + now.format(formatter));  
    }
    
    
    public static void displayWelcomeMenuOption(){
        String option = 
            "                   [!] NOTICE: The system is ready to work. Please choose one of the option below\n" +
            "                               +-------------------------------------------------+\n" +
            "                               | 1. Sign in                                      |\n" +
            "                               | 2. View area information                        |\n" +
            "                               | 3. Exit                                         |\n" +
            "                               +-------------------------------------------------+\n";
        System.out.println(option);
    }
    
    
    
    
    //displayWelcomeMenu(); hiển thị Welcome Menu
    
    //displayLoginMenu; hiển thị Login Menu
    
    //displayMenuLv(): hiển thị Menu chung chứa các chức năng
    //displayHeader(); hiển thị Header của Menu
    //displayOption(); hiển thị các lựa chọn tùy thuộc vào role
    //displayLogOut(); hiển thị chức năng Logout
    //chooseOption(); xử lý lựa chọn chức năng



}

