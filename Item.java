/**
 * Project 14 The Zorkening!
 * Item Class
 *
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

    public String toString() {
        return "Item," + name + "," + description;
    }

    public void use() {
        System.out.println("This is an odd time and place to do that!");
    }

    public void useOn(Item other) {
        System.out.println( "This is an odd time and place to do that!");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}