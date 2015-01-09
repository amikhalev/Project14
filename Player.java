import java.util.List;
import java.util.ArrayList;
/**
 * Project 14 The Zorkening!
 * Player Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Player extends Character {
    private static final String description = "A lonely traveller…";
    private static final int health = 10;
    private static final int attack = 10;
    private static final int defense = 10;
    protected List<Equippable> equipped;
    protected List<Item> inventory;
    protected int money, baseAttack, baseDefense;

    public Player(String name, Item[] inventory) {
        super(name, description, inventory, health, attack, defense, false);
        this.equipped = new ArrayList<Equippable>();
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

    public int getBaseAttack() {
        return attack;
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
    
    public List<Item> getInventory(){
        return inventory;
    }
    
    public void equip(Equippable equipment) {
        equipped.add(equipment);
    }
    
    public void attack(Character target) {
        int damage = (getAttack() - target.getDefense());
        if (damage > 0) {
            target.setHealth(target.getHealth() - damage);
            System.out.printf("You attacked the %s for %d damage!\n", target.getName(), damage);
        } else {
            System.out.printf("The %s's armor is to strong!\n", target.getName());
        }
    }
}
