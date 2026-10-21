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
        try {
            if (!scanner.hasNextLine()) {
                return null;
            }
            String input = scanner.nextLine();
            return input.trim().replaceAll("\\s+", " ");
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}