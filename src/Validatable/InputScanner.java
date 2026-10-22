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
    public static int readRange(
        Scanner sc,
        String message,
        int min,
        int max,
        String errorMessage) {

    while (true) {
        System.out.print(message);

        if (!sc.hasNextLine()) {
            return -1; // Hết đầu vào
        }

        try {
            int value = Integer.parseInt(sc.nextLine().trim());

            if (value >= min && value <= max) {
                return value;
            }
        } catch (NumberFormatException e) {
            // Nhập chữ hoặc số không chuyển được sang int.
        }

        System.out.println(errorMessage);
    }
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