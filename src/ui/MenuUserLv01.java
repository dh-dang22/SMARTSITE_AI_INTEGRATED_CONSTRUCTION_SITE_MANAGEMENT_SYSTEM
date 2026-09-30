
package ui;

public class MenuUserLv01 extends MenuUserLv02{
    public void displayMenuLv01(String role){
        displayHeader01();
        displayOption01();
        displayLogOut();
        System.out.println("Your action: ");
        int choice = sc.nextInt();   
        chooseOption01(choice);       
    }
    
    public void displayHeader01(){
        super.displayHeader02();
    }
    
    public void displayOption01(){
        super.displayOption02();
        System.out.println("6. Show all history attendance");
    }
    
    public void displayLogOut(){
        System.out.println("0. Log out");    
    }

    public void inputChoice(){
        System.out.println("Your action: ");
        int choice = sc.nextInt();
    }
    
    public void chooseOption01(int choice){
        switch (choice){
            case (6):
                //showAllHistoryAttendance();
                break;
            default:
                super.chooseOption03(choice);
                break;
        }
        
    }
    
}
