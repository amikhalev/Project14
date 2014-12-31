import java.util.List;
import java.util.ArrayList;
/**
 * Project 14 The Zorkening!
 * Player Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Player extends Character {
    private static final String DESCRIPTION = "A lonely traveller....";
    private static final int HEALTH = 10;
    private static final int ATTACK = 10;
    private static final int DEFENSE = 10;
    protected List<Equippable> equipped;
    protected int money;

    public Player(String name, Item[] inventory) {
        super(name, DESCRIPTION, inventory, HEALTH, ATTACK, DEFENSE, false);
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
