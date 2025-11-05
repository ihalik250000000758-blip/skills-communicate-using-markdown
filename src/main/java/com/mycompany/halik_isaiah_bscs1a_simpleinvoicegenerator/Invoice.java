package com.mycompany.halik_isaiah_bscs1a_simpleinvoicegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an invoice with buyer and seller information, items, and dates.
 * This class encapsulates all invoice data and provides methods to calculate totals.
 * 
 * @author Isaiah Smile B. Halik
 * @version 1.0
 */
public class Invoice {
    private final String buyerName;
    private final String buyerAddress;
    private final String buyerEmail;
    private final String sellerName;
    private final String sellerAddress;
    private final String sellerEmail;
    private final String invoiceDate;
    private final String dueDate;
    private final List<InvoiceItem> items;

    /**
     * Constructs an Invoice with the specified details.
     * 
     * @param buyerName    Name of the buyer
     * @param buyerAddress Address of the buyer
     * @param buyerEmail   Email of the buyer
     * @param sellerName   Name of the seller
     * @param sellerAddress Address of the seller
     * @param sellerEmail  Email of the seller
     * @param invoiceDate  Date when invoice was issued
     * @param dueDate      Date when payment is due
     * @throws IllegalArgumentException if any parameter is null or empty
     */
    public Invoice(String buyerName, String buyerAddress, String buyerEmail,
                   String sellerName, String sellerAddress, String sellerEmail,
                   String invoiceDate, String dueDate) {
        if (buyerName == null || buyerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer name cannot be null or empty");
        }
        if (buyerAddress == null || buyerAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer address cannot be null or empty");
        }
        if (buyerEmail == null || buyerEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer email cannot be null or empty");
        }
        if (sellerName == null || sellerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller name cannot be null or empty");
        }
        if (sellerAddress == null || sellerAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller address cannot be null or empty");
        }
        if (sellerEmail == null || sellerEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller email cannot be null or empty");
        }
        if (invoiceDate == null || invoiceDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Invoice date cannot be null or empty");
        }
        if (dueDate == null || dueDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Due date cannot be null or empty");
        }
        
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
        this.buyerEmail = buyerEmail;
        this.sellerName = sellerName;
        this.sellerAddress = sellerAddress;
        this.sellerEmail = sellerEmail;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the invoice.
     * 
     * @param item The invoice item to add
     * @throws IllegalArgumentException if item is null
     */
    public void addItem(InvoiceItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Invoice item cannot be null");
        }
        items.add(item);
    }

    /**
     * Calculates the total amount of all items in the invoice.
     * 
     * @return The total amount
     */
    public double calculateTotal() {
        return items.stream()
                    .mapToDouble(InvoiceItem::getPrice)
                    .sum();
    }

    /**
     * Generates a formatted string representation of the invoice.
     * 
     * @return Formatted invoice as a string
     */
    public String formatInvoice() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n");
        sb.append("========================================================================================\n");
        sb.append("                          SIMPLE INVOICE GENERATOR                                      \n");
        sb.append("========================================================================================\n");
        sb.append(String.format("%-20s %-60s%n", "ISSUED:", invoiceDate));
        sb.append(String.format("%-20s %-60s%n", "DUE:", dueDate));
        sb.append("----------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-40s %-40s%n", "BILLED TO:", "FROM:"));
        sb.append(String.format("%-40s %-40s%n", buyerName, sellerName));
        sb.append(String.format("%-40s %-40s%n", buyerAddress, sellerAddress));
        sb.append(String.format("%-40s %-40s%n", buyerEmail, sellerEmail));
        sb.append("----------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-50s %29s%n", "ITEM", "PRICE (PHP)"));
        sb.append("----------------------------------------------------------------------------------------\n");
        
        for (InvoiceItem item : items) {
            sb.append(String.format("%-50s %29.2f%n", item.getName(), item.getPrice()));
        }
        
        sb.append("----------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-50s %29.2f%n", "TOTAL:", calculateTotal()));
        sb.append("========================================================================================\n");
        
        return sb.toString();
    }

    // Getters
    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public List<InvoiceItem> getItems() {
        return java.util.Collections.unmodifiableList(items);
    }
}
