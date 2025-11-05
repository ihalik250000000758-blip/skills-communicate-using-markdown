package com.mycompany.halik_isaiah_bscs1a_simpleinvoicegenerator;

import java.io.File;
import java.util.Scanner;

/**
 * Simple Invoice Generator - Main Application
 * 
 * This program generates professional invoices by collecting buyer and seller information,
 * item details, and saving them to a text file. The application features input validation,
 * error handling, and a user-friendly interface.
 * 
 * Features:
 * - Multiple item entry with automatic total calculation
 * - Comprehensive input validation
 * - Professional invoice formatting
 * - File overwrite protection
 * - Clear error messages and user feedback
 * 
 * @author Isaiah Smile B. Halik
 * @version 2.0
 * @since 2025
 */
public class Halik_Isaiah_BSCS1A_SimpleInvoiceGenerator {

    private static final String BORDER = "=" + "=".repeat(79);
    private static final String WELCOME_MESSAGE = 
        "\n" + BORDER + "\n" +
        "          SIMPLE INVOICE GENERATOR - Version 2.0\n" +
        BORDER + "\n" +
        "Welcome! This program will help you create professional invoices.\n" +
        BORDER + "\n";

    /**
     * Main entry point of the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            displayWelcomeMessage();
            
            // Step 1: Get item information
            Invoice invoice = collectInvoiceData(scanner);
            
            // Step 2: Get filename and create file
            String filename = InputValidator.readFilename(scanner, 
                "\nEnter filename (Example: invoice.txt): ");
            File file = new File(filename);
            
            // Step 3: Create file and handle overwrite
            if (!FileManager.createFile(file)) {
                System.out.println("\n✗ Invoice generation cancelled.");
                return;
            }
            
            // Step 4: Write invoice to file
            String invoiceContent = invoice.formatInvoice();
            if (FileManager.writeToFile(file, invoiceContent)) {
                // Step 5: Display the invoice
                FileManager.readFile(file);
                
                System.out.println("\n" + BORDER);
                System.out.println("✓ Invoice generated successfully!");
                System.out.println("✓ Saved to: " + file.getAbsolutePath());
                System.out.println(BORDER);
            } else {
                System.err.println("\n✗ Failed to write invoice to file.");
            }
            
        } catch (Exception e) {
            System.err.println("\n✗ An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    /**
     * Displays a welcoming message to the user.
     */
    private static void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Collects all invoice data from the user including items, buyer, and seller information.
     * 
     * @param scanner The scanner to read user input
     * @return A complete Invoice object
     */
    private static Invoice collectInvoiceData(Scanner scanner) {
        // Collect item information
        System.out.println("\n--- ITEM INFORMATION ---");
        int itemCount = InputValidator.readPositiveInt(scanner, 
            "How many items do you want to add? ");
        
        // Collect items temporarily
        String[] itemNames = new String[itemCount];
        double[] itemPrices = new double[itemCount];
        
        System.out.println("\nPlease enter the item details:");
        for (int i = 0; i < itemCount; i++) {
            System.out.println("\nItem " + (i + 1) + ":");
            itemNames[i] = InputValidator.readNonEmptyString(scanner, 
                "  Name (Example: Adobo Chicken, Pencil, Ballpen): ");
            itemPrices[i] = InputValidator.readPositiveDouble(scanner, 
                "  Price (Example: 100.65, 5.62, 10): PHP ");
        }
        
        // Collect buyer information
        System.out.println("\n--- BUYER INFORMATION ---");
        String buyerName = InputValidator.readNonEmptyString(scanner, 
            "Name (Example: Isaiah Smile B. Halik): ");
        String buyerAddress = InputValidator.readNonEmptyString(scanner, 
            "Address (Example: 45 Rizal Ave., Quezon City, 1100): ");
        String buyerEmail = InputValidator.readEmail(scanner, 
            "Email (Example: ihalik_250000000758@uic.edu.ph): ");
        
        // Collect seller information
        System.out.println("\n--- SELLER INFORMATION ---");
        String sellerName = InputValidator.readNonEmptyString(scanner, 
            "Name (Example: Hazeljhon Dela Rosa): ");
        String sellerAddress = InputValidator.readNonEmptyString(scanner, 
            "Address (Example: 123 Mango St., Cebu City, 6000): ");
        String sellerEmail = InputValidator.readEmail(scanner, 
            "Email (Example: hdelarosa_250000000572@uic.edu.ph): ");
        
        // Collect date information
        System.out.println("\n--- DATE INFORMATION ---");
        String invoiceDate = InputValidator.readNonEmptyString(scanner, 
            "Invoice Date (Example: December 30, 2025): ");
        String dueDate = InputValidator.readNonEmptyString(scanner, 
            "Due Date (Example: January 30, 2026): ");
        
        // Create invoice object
        Invoice invoice = new Invoice(buyerName, buyerAddress, buyerEmail,
                                     sellerName, sellerAddress, sellerEmail,
                                     invoiceDate, dueDate);
        
        // Add items to invoice
        for (int i = 0; i < itemCount; i++) {
            invoice.addItem(new InvoiceItem(itemNames[i], itemPrices[i]));
        }
        
        return invoice;
    }
}
