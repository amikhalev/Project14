/**
 * Project 14 The Zorkening!
 * Armor Class. An equippable which adds defense value to a player
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Armor extends Equippable {
    private int defense;

    /**
     * Creates a new weapon 
     * @param name The weapon's name
     * @param description The weapon's description
     * @param defense How much defense value the weapon adds
     */
    public Armor(String name, String description, int defense) {
        super(name, description);
        this.defense = defense;
    }

    /**
     * Gets how much defense value the weapon adds
     * @return The value
     */
    public int getDefense() {
        return defense;
    }
}
