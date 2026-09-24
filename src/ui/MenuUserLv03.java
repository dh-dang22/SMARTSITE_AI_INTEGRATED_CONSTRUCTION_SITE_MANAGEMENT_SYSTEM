package ui;

import  java.util.Scanner;
        
public class MenuUserLv03 {
    String role = "Admin";
    String name = "Mang";
    Scanner sc = new Scanner(System.in);
    
    public void displayMenu(String role){
        System.out.println("---------------" + role + "---------------");
        System.out.println("");

        System.out.println("Welcome " + name + "!");
        System.out.println("What do you want to do?");
        System.out.println("1. Display your information");
        System.out.println("2. Check-in");
        System.out.println("3. Check-out");
        System.out.println("4. Display History Attendance");
        System.out.println("0. Log out");

        System.out.println("Your action: ");
        int choice = sc.nextInt();
        switch (choice){
            case (0):
                //logout()
                break;
                case (1):
                    //displayInformation();
                    break;
                case (2):
                    //getCheckInTime();
                    break;
                case (3):
                    //getCheckOutTime();
                    break;
                case (4):
                    //Chưa cóa;
                    break;
                    
        }
    }

}
