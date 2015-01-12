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

    public Player(String name) {
        super(name, description, new Item[0], health, attack, defense, false);
        this.equipped = new ArrayList<>();
        this.money = 0;
    }

    public int getAttack() {
        int itemAttack = 0;
        for (Equippable item : equipped) {
            if (item instanceof Weapon) {
                itemAttack += ((Weapon) item).getAttack();
            }
        }
        return attack + itemAttack;
    }

    public int getDefense() {
        int itemDefense = 0;
        for (Equippable item : equipped) {
            if (item instanceof Armor) {
                itemDefense += ((Armor) item).getDefense();
            }
        }
        return defense + itemDefense;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getInventory() {
        return inventory;
    }

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