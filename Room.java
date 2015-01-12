import java.util.ArrayList;
import java.util.List;

/**
 * Project 14 The Zorkening!
 * Room Class
 *
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
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.name = name;
        this.description = description;
    }

    public Room(List<Item> items, List<Character> characters, Room north, Room south, Room east, Room west, Room up, Room down) {
        this.items = items;
        this.characters = characters;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.up = up;
        this.down = down;
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

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public Room getUp() {
        return up;
    }

    public void setUp(Room up) {
        this.up = up;
    }

    public Room getDown() {
        return down;
    }

    public void setDown(Room down) {
        this.down = down;
    }

    protected void goCheck(Room room) {
        if (room == null)
            System.out.println("You hit your head on the wall and get a headache :(");
    }

    public Room goNorth() {
        goCheck(north);
        return north;
    }

    public Room goSouth() {
        goCheck(south);
        return south;
    }

    public Room goEast() {
        goCheck(east);
        return east;
    }

    public Room goWest() {
        goCheck(west);
        return west;
    }

    public Room goUp() {
        goCheck(up);
        return up;
    }

    public Room goDown() {
        goCheck(down);
        return down;
    }
    /*
    public String toString() {
    String roomItems = ",";
    String characters = ",";
    for(int i = 0; i < items.size(); i++) {
    roomItems += items.toArray()[i].toString() + ",";
    }

    //for(int i = 0; i < characters.size(); i++){
    //roomCharacters += characters.toArray()[i].toString() + ",";
    //}
    //return items.size() + itemCharacters + characters.size() + roomCharacters + north.toString() + south.toString() + east.toString() + west.toString() + up.toString + down.toString();
    //return null;
    }
    */

}