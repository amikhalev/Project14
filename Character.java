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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean getHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }

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

    public void die() {
        System.out.printf("You kill the %s\n", getName());
    }

    public String toString() {
        String items = ",";
        for (int i = 0; i < inventory.size(); i++) {
            items += inventory.toArray()[i].toString() + ",";
        }
        return name + "," + description + "," + inventory.size() + items + "," + health + "," + attack + "," + defense + "," + hostile;
    }
}