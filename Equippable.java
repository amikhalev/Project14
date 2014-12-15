/**
 * Project 14 The Zorkening!
 * Equippable Item Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Equippable extends Item{
    public Equippable(String name, String description){
        super(name, description);
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
}