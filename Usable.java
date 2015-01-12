/**
 * Project 14 The Zorkening!
 * Usable Item Class
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Usable extends Item {
    protected int healthChange;
    protected int attackChange;
    protected int defenseChange;

    public Usable(String name, String description, int healthChange, int attackChange, int defenseChange) {
        super(name, description);
        this.healthChange = healthChange;
        this.attackChange = attackChange;
        this.defenseChange = defenseChange;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void use() {

    }

    public void use(Character target) {

    }
}
