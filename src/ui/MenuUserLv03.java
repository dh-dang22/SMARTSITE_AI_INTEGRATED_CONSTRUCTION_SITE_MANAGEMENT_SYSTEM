package ui;
        
public class MenuUserLv03 extends MenuUserLv00{    
    @Override
    public void displayOption(){
        System.out.println("1. Display your information");
        System.out.println("2. Check-in");
        System.out.println("3. Check-out");
        System.out.println("4. Display History Attendance");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (0):
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
