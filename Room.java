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

    //<<<<<<< Updated upstream
    public Room(Item[] items, Character[] characters, String name, String description) {
        this.items = new ArrayList<Item>(Arrays.asList(items));
        this.characters = new ArrayList<Character>(Arrays.asList(characters));
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
    /*public void setDown(Room down) {
    //=======
    //  v-----This seems out of place. Maybe copy-pasta fail? 
    public Room(List<Item> items, List<Character> characters) {
    this.items = items;
    this.characters = characters;
    }

    public Room(List<Item> items, List<Character> characters, Room north, Room south, Room east, Room west, Room up, Room down) {
    this.items = items;
    this.characters = characters;
    this.north = north;
    this.south = south;
    this.east = east;
    this.west = west;
    this.up = up;
    //    >>>>>>> Stashed changes
    this.down = down;
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
    //<<<<<<< Updated upstream
    }*/
    //=======
}
//>>>>>>> Stashed changes