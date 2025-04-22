package com.JimsCoffee.JimsCoffee;

import java.util.List;

/**
 * Represents a coffee product with various attributes such as name, type, size, and more.
 */
public class Coffee {
    private int id;
    private String name;
    private String type;
    private String size;
    private double price;
    private String roastLevel;
    private String origin;
    private boolean isDecaf;
    private int stock;
    private List<String> flavorNotes;
    private String brewMethod;

    /**
     * Constructs a new Coffee object with the specified attributes.
     *
     * @param id          The unique identifier of the coffee.
     * @param name        The name of the coffee.
     * @param type        The type of coffee (e.g., espresso, latte).
     * @param size        The size of the coffee (e.g., small, medium, large).
     * @param price       The price of the coffee.
     * @param roastLevel  The roast level of the coffee (e.g., light, medium, dark).
     * @param origin      The origin of the coffee beans.
     * @param isDecaf     Whether the coffee is decaffeinated.
     * @param stock       The available stock quantity of the coffee.
     * @param flavorNotes A list of flavor notes associated with the coffee.
     * @param brewMethod  The recommended brewing method for the coffee.
     */
    public Coffee(int id, String name, String type, String size, double price, String roastLevel, String origin, boolean isDecaf, int stock, List<String> flavorNotes, String brewMethod) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.roastLevel = roastLevel;
        this.origin = origin;
        this.isDecaf = isDecaf;
        this.stock = stock;
        this.flavorNotes = flavorNotes;
        this.brewMethod = brewMethod;
    }

    /**
     * Gets the coffee ID.
     * @return The coffee ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the coffee.
     * @return The coffee name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the coffee.
     * @return The coffee type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the size of the coffee.
     * @return The coffee size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the price of the coffee.
     * @return The coffee price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the roast level of the coffee.
     * @return The roast level.
     */
    public String getRoastLevel() {
        return roastLevel;
    }

    /**
     * Gets the origin of the coffee.
     * @return The coffee origin.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Checks if the coffee is decaffeinated.
     * @return True if the coffee is decaffeinated, false otherwise.
     */
    public boolean isDecaf() {
        return isDecaf;
    }

    /**
     * Gets the stock quantity of the coffee.
     * @return The stock quantity.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Gets the list of flavor notes of the coffee.
     * @return A list of flavor notes.
     */
    public List<String> getFlavorNotes() {
        return flavorNotes;
    }

    /**
     * Gets the recommended brewing method.
     * @return The brewing method.
     */
    public String getBrewMethod() {
        return brewMethod;
    }

    /**
     * Sets the name of the coffee.
     * @param name The coffee name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the coffee.
     * @param type The coffee type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the size of the coffee.
     * @param size The coffee size.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Sets the price of the coffee.
     * @param price The coffee price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the roast level of the coffee.
     * @param roastLevel The roast level.
     */
    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    /**
     * Sets the origin of the coffee.
     * @param origin The coffee origin.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Sets whether the coffee is decaffeinated.
     * @param isDecaf True if the coffee is decaffeinated, false otherwise.
     */
    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }

    /**
     * Sets the stock quantity of the coffee.
     * @param stock The stock quantity.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets the flavor notes of the coffee.
     * @param flavorNotes A list of flavor notes.
     */
    public void setFlavorNotes(List<String> flavorNotes) {
        this.flavorNotes = flavorNotes;
    }

    /**
     * Sets the brewing method of the coffee.
     * @param brewMethod The brewing method.
     */
    public void setBrewMethod(String brewMethod) {
        this.brewMethod = brewMethod;
    }
}