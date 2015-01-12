/**
 * Project 14 The Zorkening!
 * Weapon Class. An item which boosts a characters attack.
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Weapon extends Equippable {
    private int attack;

    /**
     * Creates a new weapon 
     * @param name The weapon's name
     * @param description The weapon's description
     * @param attack How much attack value the weapon adds
     */
    public Weapon(String name, String description, int attack) {
        super(name, description);
        this.attack = attack;
    }

    /**
     * Gets how much attack value the weapon adds
     * @return The value
     */
    public int getAttack() {
        return attack;
    }
}
