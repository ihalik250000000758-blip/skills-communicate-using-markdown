package com.mycompany.halik_isaiah_bscs1a_simpleinvoicegenerator;

/**
 * Represents a single item in an invoice with name and price.
 * This class is immutable to ensure data integrity.
 * 
 * @author Isaiah Smile B. Halik
 * @version 1.0
 */
public class InvoiceItem {
    private final String name;
    private final double price;

    /**
     * Constructs an InvoiceItem with the specified name and price.
     * 
     * @param name  The name of the item
     * @param price The price of the item
     * @throws IllegalArgumentException if name is null or empty, or if price is negative
     */
    public InvoiceItem(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the name of the item.
     * 
     * @return The item name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the item.
     * 
     * @return The item price
     */
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", name, price);
    }
}
