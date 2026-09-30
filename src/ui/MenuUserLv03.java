package ui;

import java.awt.BorderLayout;
import  java.util.Scanner;
import javax.xml.transform.OutputKeys;
        
public class MenuUserLv03 {
    String role = "Site Manager";
    String name = "Mang";
    Scanner sc = new Scanner(System.in);
    
    public void displayMenuLv03(String role){
        displayHeader03();
        displayOption03();
        displayLogOut();
        System.out.println("Your action: ");
        int choice = sc.nextInt();        
        chooseOption03(choice);
        
    }
    
    public void displayHeader03(){
        System.out.println("---------------" + role + " Menu "+ "---------------");
        System.out.println("");
        System.out.println("Welcome " + role + " "+ name + "!");
        System.out.println("What do you want to do?");    
    }
    
    public void displayOption03(){
        System.out.println("1. Display your information");
        System.out.println("2. Check-in");
        System.out.println("3. Check-out");
        System.out.println("4. Display History Attendance");
    }
    
    public void displayLogOut(){
        System.out.println("0. Log out");    
    }
    
    public void chooseOption03(int choice){
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
