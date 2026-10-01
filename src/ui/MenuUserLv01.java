
package ui;

public class MenuUserLv01 extends MenuUserLv02{
    
    @Override
    public void displayMenuLv(String role){
        displayHeader();
        displayOption();
        displayLogOut();
        System.out.println("Your action: ");
        int choice = sc.nextInt();   
        chooseOption(choice);       
    }
    
    @Override
    public void displayOption(){
        super.displayOption();
        System.out.println("6. Show all history attendance");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (6):
                //showAllHistoryAttendance();
                break;
            default:
                super.chooseOption(choice);
                break;
        }
        
    }
    
}
