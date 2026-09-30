package ui;

public class MenuUserLv02 extends MenuUserLv03 {
    
    public void displayMenuLv02(String role){
        displayHeader02();
        displayOption02();
        displayLogOut();
        System.out.println("Your action: ");
        int choice = sc.nextInt();   
        chooseOption02(choice);   
    }
    
    public void displayHeader02(){
        super.displayHeader03();
    }
    
    public void displayOption02(){
        super.displayOption03();
        System.out.println("5. Take tools");
    }
    
    public void displayLogOut(){
        System.out.println("0. Log out");    
    }
    
    public void inputChoice(){
        System.out.println("Your action: ");
        int choice = sc.nextInt();
    }
    
    public void chooseOption02(int choice){
        switch (choice){
            case (5):
                //takeTools();
                break;
            default:
                super.chooseOption03(choice);
                break;
        }
        
    }
}
