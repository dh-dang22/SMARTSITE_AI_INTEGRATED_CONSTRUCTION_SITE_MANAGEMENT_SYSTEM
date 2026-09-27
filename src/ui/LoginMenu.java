
package ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoginMenu {
    public void displayLoginMenu(){
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("================================================================================");
            System.out.println("");
            System.out.println("      ___           ___           ___           ___           ___");
            System.out.println("     /\\  \\         /\\  \\         /\\  \\         /\\  \\         /\\  \\");
            System.out.println("    /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\");
            System.out.println("   /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\");
            System.out.println("  /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\");
            System.out.println(" /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\ /_/\\:\\ \\:\\__\\");
            System.out.println(" \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/ \\~\\:\\ \\/__/");
            System.out.println("  \\:\\__\\        \\:\\__\\        \\:\\__\\        \\:\\__\\        \\:\\__\\");
            System.out.println("   \\/__/         \\/__/         \\/__/         \\/__/         \\/__/");
            System.out.println("");
            System.out.println("================================================================================");
            System.out.println("                         WELCOME TO THE SSMARTSITE");
            System.out.println("                      Version 1.0.0 | Terminal Edition");
            // Forrmateed HH/MM/SS: Giờ:Phút:Giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                LocalTime now = LocalTime.now();
                // In ghi đè lên dòng cũ bằng '\r' trên Console/Terminal
            System.out.println("\r                              Time: " + now.format(formatter));         
            System.out.println("================================================================================");
            System.out.println("");
            System.out.println("        [!] NOTICE: Please enter your user name and password below!!!");
            System.out.println("");
            System.out.println("   >>> Enter your username  ");
            String userName = sc.nextLine();
            System.out.println("   >>> Enter your password  ");
            String password = sc.nextLine();
            System.out.println("User name: " + userName + "; password: " + password);
            //checkRole()
            //String role = checkRole(userName, password); //kiểm tra role để gọi menu tương ứng
            //if (role == "Visitors"){
            //    MenuUserLv03 htMenuUserLv03 = new MenuUserLv03();
            //    htMenuUserLv03.displayMenu(role);
            //}
        
        }//while(running)
    }//dislplayLoginMenu()
}//class
