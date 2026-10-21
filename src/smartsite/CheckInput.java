package smartsite;

import java.util.Scanner;


/**
 *
 * @author GIA HUY
 */
public class CheckInput  {
    /* readString la ham kiem tra input co rong hay la null hay khong voi cac tham so dau vao la sc  ( input ), 
    noticeMes la thong bao he thong xuat ra de nhap yeu cau nhap input, 
    errorMes la thong bao xuat hien khi input khong hop le voi cac dieu kien va yeu cau nhap lai */
    private static final Scanner sc = new Scanner(System.in); 
    public static String checkEmpty(Scanner sc, String noticeMes, String errorMes) {
        System.out.println(noticeMes);
        while(true) {
            if (!sc.hasNextLine()) { // Chống crash khi hết luồng nhập (EOF)
                return ""; // tra ve chuoi rong giup chuong trinh thoat ra 
            }
            String input = sc.nextLine().trim();
            if (!input.isEmpty()){
                return input;
            }
            System.out.println(errorMes);
        }
    }
    // kiem tra theo pattern
    public static String readPattern (Scanner sc , String noticeMes, String pattern, String errorMes ){
        System.out.println(noticeMes);
        while(true){
            if (!sc.hasNextLine()) { 
                return ""; 
            }
            String input = sc.nextLine().trim();
            if ( !input.isEmpty() && input.matches(pattern) ){
                return input;
            }
            System.out.println(errorMes);
        }
    }
    //kiem tra theo range
    public static int readRange (Scanner sc, String noticeMes, int min, int max, String errorMes){
        System.out.println(noticeMes);
        while(true){
            if (!sc.hasNextLine()) { 
                return -1 ; 
            }
            try {
            int input = Integer.parseInt(sc.nextLine().trim());
            if (input >= min && input <= max  ){
                return input ; 
            }
            } catch(NumberFormatException e) {
                System.out.println(e.getMessage()+" is not a integer! ");
                System.out.println(errorMes);
            }
        }
    }
    
    public static boolean checkObject(Validatable obj) {
    if (obj == null) {
        System.out.println("Error: Object is null!");
        return false;
    }
    if (!obj.isValid()) {
        System.out.println("Error: Object contains invalid data!");
        return false;
    }
    return true;
}
      private static final Scanner INPUT = new Scanner(System.in);

        public static Scanner getScanner() {
    return INPUT;
}
    
}
