package Validatable;

import java.util.Scanner;

public class CheckContractType {
    private static final Scanner sc = new Scanner(System.in);

    public static String checkContractType() {
        while (true) {
            System.out.print("Enter Contract Type (FULLTIME/PARTTIME/SEASONAL): ");
            String contractType = sc.nextLine().trim().toUpperCase();

            if (contractType.equals("FULLTIME") || contractType.equals("PARTTIME") ) {
                return contractType;
            }
            System.out.println("[Error]: Contract Type must be FULLTIME, PARTTIME! Please try again.");
        }
    }
}