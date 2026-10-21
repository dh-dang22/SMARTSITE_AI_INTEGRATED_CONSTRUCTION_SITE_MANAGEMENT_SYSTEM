package Validatable;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputScanner {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readRange(Scanner sc, String _____Enter_number_of_people_you_want_to_a, int i, int i0, String please_enter_the_number_in_range_110) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private InputScanner() {
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static String nextLine() {
        while (true) {
            try {
                if (!scanner.hasNextLine()) {
                    return null;
                }
                String input = scanner.nextLine();
                String cleanInput = input.trim().replaceAll("\\s+", " ");

                if (cleanInput.isEmpty()) {
                    System.out.println("[Error]: Input cannot be empty! Please try again.");
                    continue;
                }
                    
                return cleanInput;
            } catch (NoSuchElementException e) {
                return null; 
            }
        }
    }
}