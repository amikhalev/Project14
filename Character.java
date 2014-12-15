import java.util.Vector;
public class Character{
    protected String name;
    protected String description;
    protected Vector<Item> inventory;
    protected int health, attack, defense;
    protected boolean hostile;
    public Character(String name, String description, Vector<Item> inventory, int health, int attack, int defense, boolean hostile){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.hostile = hostile;
    }
    
    public Character(String name, Vector<Item> inventory){
        this.name = name;
        this.inventory = inventory;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefense(){
        return defense;
    }
}