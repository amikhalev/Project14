import java.util.ArrayList;
import java.util.List;

/**
 * Project 14 The Zorkening!
 * Player Class
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Player extends Character {
    private static final String description = "A lonely traveller…";
    private static final int health = 20;
    private static final int attack = 0;
    private static final int defense = 10;
    protected List<Equippable> equipped;
    protected int money;
    /**
     * Player constructor
     * @param name Player's name
     */
    public Player(String name) {
        super(name, description, new Item[0], health, attack, defense, false);
        this.equipped = new ArrayList<>();
        this.money = 0;
    }
    
    /**
     * Get the player's attack strength
     * @return the player's attack strength
     */
    public int getAttack() {
        int itemAttack = 0;
        for (Equippable item : equipped) {
            if (item instanceof Weapon) {
                itemAttack += ((Weapon) item).getAttack();
            }
        }
        return attack + itemAttack;
    }

    /**
     * Get the player's defense
     * @return the player's defense
     */
    public int getDefense() {
        int itemDefense = 0;
        for (Equippable item : equipped) {
            if (item instanceof Armor) {
                itemDefense += ((Armor) item).getDefense();
            }
        }
        return defense + itemDefense;
    }

    /**
     * Get the name of the player
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the player
     * @return description of the player
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get player's inventory
     * @return player's inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Equip an Item
     * @param equipment Item to equip
     */
    public void equip(Equippable equipment) {
        equipped.add(equipment);
    }

    /*
    public String toString() {
    String items = ",";
    for(int i = 0; i < inventory.size(); i++) {
    items += inventory.toArray()[i].toString() + ",";
    }
    return "Player" + "," + name + "," + inventory.size() + items;
    }
     */
}