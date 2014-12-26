/**
 * Project 14 The Zorkening!
 * Armor Class
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Armor extends Equippable {
    private int defense;
    public Armor(String name, String description, int defense) {
        super(name, description);
        this.defense = defense;
    }
    public int getDefense() {
        return defense;
    }
}
