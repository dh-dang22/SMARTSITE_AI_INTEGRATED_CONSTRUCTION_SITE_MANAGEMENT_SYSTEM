package Validatable;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputScanner {

    private static final Scanner scanner = new Scanner(System.in);

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