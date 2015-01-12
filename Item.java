/**
 * Project 14 The Zorkening!
 * Item Class. Represents an item.
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Item {
    protected String name;
    protected String description;

    /**
     * Creates a new item with a name and description 
     * @param name The name of the item
     * @param description The description of the item
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the name of the item 
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item 
     * @param name The new name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the item 
     * @return The description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item 
     * @param description The new description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the string representation of the item 
     * @return The string representation of the item
     */
    public String toString() {
        return "Item," + name + "," + description;
    }
}