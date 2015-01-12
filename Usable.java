/**
 * Project 14 The Zorkening!
 * Usable Item Class. Any item which can be used with a
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Usable extends Item {
    /**
     * Creates a new item with a name and description
     *
     * @param name        The name of the item
     * @param description The description of the item
     */
    public Usable(String name, String description) {
        super(name, description);
    }

    /**
     * Uses the item. This should be overridden by any useful items
     */
    public void use() {
        System.out.println("This is an odd time and place to do that!");
    }

    /**
     * Uses an item on another item.
     * @param other The other item.
     */
    public void useOn(Item other) {
        System.out.println("This is an odd time and place to do that!");
    }
}
