/**
 * Project 14 The Zorkening!
 * Weapon Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Weapon extends Equippable {
    private String name;
    private String description;
    private int attack;
    public Weapon(String name, String description, int attack) {
        super(name, description);
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAttack() {
        return attack;
    }
}