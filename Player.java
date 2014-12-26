import java.util.List;
/**
 * Project 14 The Zorkening!
 * Player Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Player extends Character {
    protected Equippable[] equipped;
    protected List<Item> inventory;
    protected int money, baseAttack, baseDefense;

    public Player(String name, String description, Item[] inventory, int health, int attack, int defense) {
        super(name, description, inventory, health, attack, defense);
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

    public void equip(Item item) {
        inventory.add(item);
    }
}
