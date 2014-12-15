import java.util.Vector;
/**
 * Project 14 The Zorkening!
 * Room Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Room{
    protected Vector<Item> items;
    protected Vector<Character> characters;
    protected Room north;
    protected Room south;
    protected Room east;
    protected Room west;
    protected Room up;
    protected Room down;

    public Room(Vector<Item> items, Vector<Character> characters){

    }

    public Room goNorth(){
        return north;
    }

    public Room goSouth(){
        return south;
    }

    public Room goEast(){
        return east;
    }

    public Room goWest(){
        return west;
    }

    public Room goUp(){
        return up;
    }

    public Room goDown(){
        return down;
    }
}