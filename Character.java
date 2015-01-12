import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project 14 The Zorkening!
 * Weapon Class
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Character {
    protected String name;
    protected String description;
    protected List<Item> inventory;
    protected int health, attack, defense;
    protected boolean hostile;

    public Character(String name, String description, Item[] inventory, int health, int attack, int defense, boolean hostile) {
        this.name = name;
        this.description = description;
        this.inventory = new ArrayList<Item>(Arrays.asList(inventory));
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.hostile = hostile;
    }

    /**
     * Get the name of the character
     *
     * @return name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the character
     *
     * @return description of the character
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the character's health
     *
     * @return the character's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set the character's health
     *
     * @param health the character's health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Get the character's attack strength
     *
     * @return the character's attack strength
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Set the character's attack strength
     *
     * @param attack the character's attack strength
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Get the character's defense
     *
     * @return the character's defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Set the character's defense
     *
     * @param defense the character's defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Get the character's hostility status
     *
     * @return hostility status
     */
    public boolean getHostile() {
        return hostile;
    }

    /**
     * Set the character's hostility status
     *
     * @param hostile hostility status
     */
    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    /**
     * Add item to character inventory
     *
     * @param item Item to add to character inventory
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Get character's inventory
     *
     * @return character's inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Attack a character
     *
     * @param target Character to be attacked
     * @return whether the attack succeeded
     */
    public boolean attack(Character target) {
        int damage = (getAttack() - target.getDefense());
        String name = target.getName();
        if (damage > 0) {
            target.setHealth(target.getHealth() - damage);
            System.out.printf("You attacked the %s for %d damage!\n", name, damage);
            if (target.getHealth() <= 0) {
                return true;
            }
        } else {
            System.out.printf("The %s's armor is to strong!\n", name);
        }
        return false;
    }

    /**
     * Kill the character
     */
    public void die() {
        System.out.printf("You kill the %s\n", getName());
    }

    /**
     * Represent the character as a String
     *
     * @return character as a String
     */
    public String toString() {
        String items = ",";
        for (int i = 0; i < inventory.size(); i++) {
            items += inventory.toArray()[i].toString() + ",";
        }
        return name + "," + description + "," + inventory.size() + items + "," + health + "," + attack + "," + defense + "," + hostile;
    }
}