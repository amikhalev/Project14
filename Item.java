/**
 * Project 14 The Zorkening!
 * Item Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Item {
    protected String name;
    protected String description;
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return "Item," + name + "," + description;
    }
}