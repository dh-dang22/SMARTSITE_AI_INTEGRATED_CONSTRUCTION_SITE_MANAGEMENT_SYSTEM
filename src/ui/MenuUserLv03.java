package ui;

import java.awt.BorderLayout;
import  java.util.Scanner;
import javax.xml.transform.OutputKeys;
        
public class MenuUserLv03 {
    String role = "Site Managers";
    String name = "Mang";
    Scanner sc = new Scanner(System.in);
    
    public void displayMenuLv03(String role){
        System.out.println("---------------" + role + " Menu "+ "---------------");
        System.out.println("");

        System.out.println("Welcome " + role + " "+ name + "!");
        System.out.println("What do you want to do?");
        System.out.println("1. Display your information");
        System.out.println("2. Check-in");
        System.out.println("3. Check-out");
        System.out.println("4. Display History Attendance");
        System.out.println("0. Log out");
        chooseFunction();
        
        }//displayMenuLv03
    
    
    
    public void chooseFunction(){
        System.out.println("Your action: ");
        int choice = sc.nextInt();
        switch (choice){
            case (0):
                System.out.println("Function not complete yet!");
                //logout()
                break;
                case (1):
                    System.out.println("Function not complete yet!");
                    //displayInformation();
                    break;
                case (2):
                    System.out.println("Function not complete yet!");
                    //getCheckInTime();
                    break;
                case (3):
                    System.out.println("Function not complete yet!");
                    //getCheckOutTime();
                    break;
                case (4):
                    System.out.println("Function not complete yet!");
                    //Chưa cóa;
                    break;
                default:
                    System.out.println("Invalid choice! Please select again");
        }
    }
    
}//class
