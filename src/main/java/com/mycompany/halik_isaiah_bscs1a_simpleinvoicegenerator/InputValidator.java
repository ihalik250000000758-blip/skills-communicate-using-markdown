package com.mycompany.halik_isaiah_bscs1a_simpleinvoicegenerator;

import java.util.Scanner;

/**
 * Validates and sanitizes user input for the invoice generator.
 * Provides robust input validation with retry mechanisms.
 * 
 * @author Isaiah Smile B. Halik
 * @version 1.0
 */
public class InputValidator {

    /**
     * Reads a positive integer from the user with validation.
     * 
     * @param scanner The scanner to read input from
     * @param prompt  The prompt message to display
     * @return A valid positive integer
     */
    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.err.println("✗ Input cannot be empty. Please try again.");
                continue;
            }
            
            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    System.err.println("✗ Please enter a positive number greater than 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.err.println("✗ Invalid number format. Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads a positive double from the user with validation.
     * 
     * @param scanner The scanner to read input from
     * @param prompt  The prompt message to display
     * @return A valid positive double
     */
    public static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.err.println("✗ Input cannot be empty. Please try again.");
                continue;
            }
            
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.err.println("✗ Please enter a non-negative number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.err.println("✗ Invalid number format. Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a non-empty string from the user with validation.
     * 
     * @param scanner The scanner to read input from
     * @param prompt  The prompt message to display
     * @return A non-empty string
     */
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.err.println("✗ Input cannot be empty. Please try again.");
                continue;
            }
            
            return input;
        }
    }

    /**
     * Reads an email address with basic validation.
     * 
     * @param scanner The scanner to read input from
     * @param prompt  The prompt message to display
     * @return A valid email address
     */
    public static String readEmail(Scanner scanner, String prompt) {
        // Basic email pattern: at least one character before @, then domain with at least one dot
        String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
        
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.err.println("✗ Email cannot be empty. Please try again.");
                continue;
            }
            
            if (!input.matches(emailPattern)) {
                System.err.println("✗ Invalid email format. Please use format: username@domain.com");
                continue;
            }
            
            // Additional check: @ must come before the last dot
            int atIndex = input.indexOf('@');
            int lastDotIndex = input.lastIndexOf('.');
            if (atIndex >= lastDotIndex) {
                System.err.println("✗ Invalid email format. Domain must contain a dot after @");
                continue;
            }
            
            return input;
        }
    }

    /**
     * Reads a filename with validation.
     * 
     * @param scanner The scanner to read input from
     * @param prompt  The prompt message to display
     * @return A valid filename
     */
    public static String readFilename(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.err.println("✗ Filename cannot be empty. Please try again.");
                continue;
            }
            
            // Check for invalid characters in filename
            if (input.matches(".*[<>:\"|?*].*")) {
                System.err.println("✗ Filename contains invalid characters. Please avoid: < > : \" | ? *");
                continue;
            }
            
            // Add .txt extension if not present
            if (!input.endsWith(".txt")) {
                input = input + ".txt";
                System.out.println("ℹ Added .txt extension: " + input);
            }
            
            return input;
        }
    }
}
