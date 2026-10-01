package ui;

public class MenuUserLv02 extends MenuUserLv03 {
    
//    @Override
//    public void displayMenuLv(String role){
//        displayHeader();
//        displayOption();
//        displayLogOut();
//        System.out.println("Your action: ");
//        int choice = sc.nextInt();   
//        chooseOption(choice);   
//    }
    
    @Override
    public void displayOption(){
        super.displayOption();
        System.out.println("5. Take tools");
    }
    
    @Override
    public void chooseOption(int choice){
        switch (choice){
            case (5):
                //takeTools();
                break;
            default:
                super.chooseOption(choice);
                break;
        }
        
    }
}
