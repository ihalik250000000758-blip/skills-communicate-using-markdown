package com.mycompany.halik_isaiah_bscs1a_simpleinvoicegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles file operations for the invoice generator.
 * Provides methods to create, write, and read invoice files.
 * 
 * @author Isaiah Smile B. Halik
 * @version 1.0
 */
public class FileManager {

    /**
     * Creates a new file or verifies that it exists.
     * 
     * @param fileName The file to create
     * @return true if file was created successfully or already exists, false otherwise
     */
    public static boolean createFile(File fileName) {
        try {
            if (fileName.createNewFile()) {
                System.out.println("✓ File has been created successfully: " + fileName.getName());
                return true;
            } else {
                System.out.println("⚠ File already exists: " + fileName.getName());
                System.out.print("Do you want to overwrite it? (yes/no): ");
                try (Scanner scanner = new Scanner(System.in)) {
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (response.equals("yes") || response.equals("y")) {
                        // Clear the file content
                        new FileWriter(fileName, false).close();
                        System.out.println("✓ File will be overwritten.");
                        return true;
                    } else {
                        System.out.println("✗ Operation cancelled. File was not overwritten.");
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("✗ Error creating file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Writes invoice content to a file.
     * 
     * @param fileName The file to write to
     * @param content  The content to write
     * @return true if write was successful, false otherwise
     */
    public static boolean writeToFile(File fileName, String content) {
        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            fileWriter.write(content);
            System.out.println("\n✓ Successfully wrote invoice to file: " + fileName.getName());
            return true;
        } catch (IOException e) {
            System.err.println("✗ Error writing to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reads and displays the content of a file.
     * 
     * @param fileName The file to read
     * @return true if read was successful, false otherwise
     */
    public static boolean readFile(File fileName) {
        if (!fileName.exists()) {
            System.err.println("✗ File does not exist: " + fileName.getName());
            return false;
        }

        try (Scanner scanner = new Scanner(fileName)) {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("INVOICE PREVIEW");
            System.out.println("=".repeat(80));
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isBlank()) {
                    System.out.println(line);
                }
            }
            
            System.out.println("=".repeat(80));
            return true;
        } catch (IOException e) {
            System.err.println("✗ Error reading file: " + e.getMessage());
            return false;
        }
    }
}
