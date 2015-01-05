import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Project 14 The Zorkening!
 * Room Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Room {
    protected List<Item> items;
    protected List<Character> characters;
    protected String name;
    protected String description;
    protected Room north;
    protected Room south;
    protected Room east;
    protected Room west;
    protected Room up;
    protected Room down;

    public Room(String name, String description) {
        this.items = new ArrayList<Item>();
        this.characters = new ArrayList<Character>();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public void setUp(Room up) {
        this.up = up;
    }

    public void setDown(Room down) {
        this.down = down;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public Room getUp() {
        return up;
    }

    public Room getDown() {
        return down;
    }

    public Room goNorth() {
        return north;
    }

    public Room goSouth() {
        return south;
    }

    public Room goEast() {
        return east;
    }

    public Room goWest() {
        return west;
    }

    public Room goUp() {
        return up;
    }

    public Room goDown() {
        return down;
    }
}
