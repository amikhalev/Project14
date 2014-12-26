/**
 * Project 14 The Zorkening!
 * Weapon Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Character {
    protected String name;
    protected String description;
    protected Item[] inventory;
    protected int health, attack, defense;
    protected boolean hostile;
    public Character(String name, String description, Item[] inventory, int health, int attack, int defense) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void setHealth(int health) {
            this.health = health;
    }

    public void setAttack(int attack) {
            this.attack = attack;
    }

    public void setDefense(int defense) {
            this.defense = defense;
    }

    public int getHealth() {
            return health;
    }
    
    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
