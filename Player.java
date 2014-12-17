import java.util.Vector;
/**
 * Project 14 The Zorkening!
 * Player Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Player extends Character {
    protected String description;
    protected Vector<Equippable> equipped;
    protected Vector<Item> inventory;
    protected int money, health, baseAttack, baseDefense;
    public Player(String name, Vector<Item> inventory) {
        super(name, inventory);
    }

    public Player(String name, String description, Vector<Item> inventory, int health, int attack, int defense, boolean hostile) {
        super(name, description, inventory, health, attack, defense, hostile);
    }

    public int getBaseAttack() {
        return attack;
    }

    public int getBaseDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void Equip(Item item) {
        inventory.add(item);
    }
}