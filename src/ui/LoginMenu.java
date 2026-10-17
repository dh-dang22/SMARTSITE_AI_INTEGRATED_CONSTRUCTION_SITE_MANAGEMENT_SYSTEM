

   package ui;
import Person.PersonManager;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import smartsite.CheckPassword;
public class LoginMenu {

    LoginMenu(PersonManager pm, CheckPassword checker) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void displayLoginMenu(){
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
    
}
}
}
