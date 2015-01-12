import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Project 14 The Zorkening!
 * Main Class
 *
 * @author Alex Mikhalev & Tavi Kohn
 * @version 1.0
 */
public class Main {
    private static Main instance;
    private int score;
    private Scanner scanner;
    private PrintStream out;
    private boolean running;
    private SaveManager save = new SaveManager();
    private Player player;
    private Room currentRoom;
    private Item backpack;

    public Main() {
        scanner = new Scanner(System.in);
        out = System.out;
        reset();
    }

    public static Main getInstance() {
        if (instance == null) instance = new Main();
        return instance;
    }

    public static void addScore(int score) {
        getInstance().incrementScore(score);
    }

    public static void readCommand(String command) {
        getInstance().process(command);
    }

    public static void main(String[] args) {
        getInstance().start();
    }

    private static Item findItemIn(List<Item> list, String name) {
        for (Item item : list) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    private static void transfer(List<Item> from, List<Item> to, Item item) {
        from.remove(item);
        to.add(item);
    }

    private void makeRooms() {
        Room wizardsWardrobe = new Room("Wizard's Wardrobe", "A small wardrobe with a door to the east");
        Room wizardsGrotto = new Room("Wizard's Grotto", "A small room, with a skylight above. There is a door to the west and an archway to the south.");
        Room grateRoom;

        Room vault = new Room("Vault", "A large ancient vault. There is a large vault door that you are pretty sure you can't open.");
        Room storeRoom = new Room("Storeroom", "A musty old storeroom. It smells like old grandpa feet in here, so you want to get out pretty quickly.");
        Room risingRoom = new Room("Rising Room", "A mystic force pushes you upward towards a 3 foot square grate. There is a small exit to the South");
        Room windingTunnel = new Room("Winding Tunnel", "A tunnel that twists and turns. You can't see very well in here, so good luck finding how to get out!");
        Room crystalCavern = new Room("Crystal Cavern", "A cavern with giant glowing crystals protruding from the wall. There are exits to the east and west.");
        Room crystalHall = new Room("Crystal Hall", "A large hall with the walls carved from some crystal. There are exits to the East and West");
        Room throneRoom = new Room("Throne Room", "A great room with a slightly undersized crystal throne. There is an exit to the West");

        Item moss = new Item("Moss", "A clump of glowing moss");
        backpack = new Item("Backpack", "A simple sturdy leather backpack");
        Item coal = new Item("Coal", "A large quantity of coal");
        Item staff = new Item("Staff", "An old wooden staff with a cavity at the top");
        Item spellBook = new Item("Spellbook", "An ancient tome with letters that seem to shift across the page." +
                " Some you can translate:\n" +
                "\tTransmutation:\n" +
                "\tUsing a ... it is possible to ...\n" +
                "\t for example chocolate (agherm)...\n\n" +
                " The rest of the book is stained, burned, and unreadable");
        Armor robe = new Armor("Robe", "A set of dark robes with symbols along the hem. +3 Defense", 3);
        Armor hat = new Armor("Hat", "A pointy hat that makes your ears buzz when you put it on. +1 Defense", 1);
        Weapon hammer = new Weapon("Hammer", "A mighty hammer of smashing things. +8 Attack", 8);
        Weapon rock = new Weapon("Rock", "A small rock that is sorta heavy. You think that it would be fun to throw. +1 Attack.", 1);
        Item flint = new Item("Flint", "A small black piece of flint");
        Item crystal = new Item("Shard", "A glowing shard of crystal");
        Item lantern = new Item("Unlit Lantern", "A small lantern with plenty of fuel, but it isn't lit");
        Item pickaxe = new Item("Rusty Pickaxe", "An old rusty worn pickaxe");
        Item warpRing = new Item("Warp Ring", "A ring that you think can make you travel in time");

        Character grate = new Character("Grate", "A metal grate in the floor, about three feet square, just big enough for you to fit through. There are tiny points of light rising up from the grate.", new Item[0], 0, 0, 0, false);

        grateRoom = new Room("Grate Room", "A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits" +
                " to the north, east, and south. I don't know about you, but I think this room is great!") {
            @Override
            public Room goDown() {
                if (getCharacters().contains(grate)) {
                    System.out.println("You need to get rid of that grate first!");
                } else if (!player.getInventory().contains(coal)) {
                    System.out.println("You try to go down, but a mystical force pulls you up and back into the great room!");
                } else {
                    return down;
                }
                return null;
            }
        };

        wizardsWardrobe.setEast(wizardsGrotto);
        wizardsGrotto.setWest(wizardsWardrobe);
        wizardsGrotto.setSouth(grateRoom);
        grateRoom.setNorth(wizardsGrotto);
        grateRoom.setEast(vault);
        grateRoom.setSouth(storeRoom);
        grateRoom.setDown(risingRoom);
        grateRoom.addCharacter(grate);
        vault.setWest(grateRoom);
        storeRoom.setNorth(grateRoom);
        risingRoom.setUp(grateRoom);
        risingRoom.setSouth(windingTunnel);
        windingTunnel.setNorth(risingRoom);
        windingTunnel.setEast(crystalCavern);
        crystalCavern.setWest(windingTunnel);
        crystalCavern.setEast(crystalHall);
        crystalHall.setWest(crystalCavern);
        crystalHall.setEast(throneRoom);
        //Flint and Crystal Shard added in game logic (when player inspects the room, flint gets added; when they break the crystals in the room, they get the crystal shard)

        throneRoom.setWest(crystalHall);
        if (player == null) {
            wizardsWardrobe.addItem(hat);
            wizardsWardrobe.addItem(robe);
            wizardsGrotto.addItem(spellBook);
            wizardsGrotto.addItem(staff);
            storeRoom.addItem(coal);
            storeRoom.addItem(backpack);
            vault.addItem(warpRing);
            vault.addItem(rock);
            storeRoom.addItem(moss);
            crystalHall.addItem(hammer);
            crystalHall.addItem(pickaxe);
            crystalCavern.addItem(lantern);
            crystalCavern.addItem(flint);
        } else {
            List<Item> inventory = player.getInventory();
            if (!inventory.contains(hat)) wizardsWardrobe.addItem(hat);
            if (!inventory.contains(robe)) wizardsWardrobe.addItem(robe);
            if (!inventory.contains(spellBook)) wizardsGrotto.addItem(spellBook);
            if (!inventory.contains(staff)) wizardsGrotto.addItem(staff);
            if (!inventory.contains(coal)) storeRoom.addItem(coal);
            if (!inventory.contains(warpRing)) vault.addItem(warpRing);
            if (!inventory.contains(rock)) vault.addItem(rock);
            if (!inventory.contains(backpack)) storeRoom.addItem(backpack);
            if (!inventory.contains(moss)) storeRoom.addItem(moss);
            if (!inventory.contains(pickaxe)) crystalHall.addItem(pickaxe);
            if (!inventory.contains(hammer)) crystalHall.addItem(hammer);
            if (!inventory.contains(flint)) crystalCavern.addItem(flint);
            if (!inventory.contains(crystal)) crystalCavern.addItem(crystal);
        }

        currentRoom = grateRoom;
    }

    public void reset() {
        out.println("Project 14");
        out.println("Made by Alex Mikhalev and Tavi Kohn");
        score = 0;
        running = false;
        makeRooms();
        out.println("\nPlease enter your name:");
        String line = scanner.nextLine();
        player = new Player(line);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int score) {
        this.score += score;
    }

    public void start() {
        running = true;
        examineRoom(currentRoom);
        while (running) prompt();
    }

    public void prompt() {
        out.print(" > ");
        String line = scanner.nextLine();
        line = line.toLowerCase().trim();
        process(line);
    }

    public void process(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            out.println("What did you say?");
            return;
        }
        String rest = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
        switch (parts[0]) {
            case "quit":
            case "exit":
                out.println("Bye!");
                running = false;
                break;
            case "cheat":
                switch (rest) {
                    case "i am the all seeing schreiber!":
                        printMap();
                        break;
                    default:
                        out.printf("\"%s\" isn't a cheat code, scrub!\n", rest);
                }
                break;
            case "go":
            case "navigate":
                if (parts.length != 2)
                    out.println("I don't understand that");
                else
                    navigate(parts[1]);
                break;
            case "attack":
                if (parts.length == 1) {
                    out.println("Attack what?");
                } else {
                    List<Character> characters = currentRoom.getCharacters();
                    for (Character character : characters) {
                        if (character.getName().equalsIgnoreCase(rest)) {
                            if (player.attack(character)) {
                                characters.remove(character);
                            }
                            return;
                        }
                    }
                    out.printf("There isn't a(n) %s to attack\n", rest);
                }
                break;
            case "stats":
                out.println("Your stats:");
                out.printf("Health: %d\n", player.getHealth());
                out.printf("Attack: %d\n", player.getAttack());
                out.printf("Defense: %d\n", player.getDefense());
                break;
            case "say":
                out.printf("You say '%s' and listen to it echo, falling on nobody's ears but you\n", rest);
                break;
            case "inventory":
            case "items":
                showInventory();
                break;
            case "look":
            case "examine":
                if (parts.length == 1) {
                    examineRoom(currentRoom);
                } else if (parts.length >= 2) {
                    examineThing(rest);
                }
                break;
            case "grab":
            case "take":
                if (parts.length >= 2) {
                    takeItem(rest);
                } else {
                    out.println("What do you want to take?");
                }
                break;
            case "equip":
                if (parts.length == 1) {
                    out.println("Equip what?");
                } else {
                    equipItem(rest);
                }
                break;
            case "drop":
                if (parts.length >= 2) {
                    dropItem(rest);
                } else {
                    out.println("What do you want to drop?");
                }
                break;
            case "use":
                if (parts.length == 1) {
                    out.println("What do you want to use?");
                } else {
                    String[] otherParts = rest.split(" on ");
                    if (otherParts.length <= 1) {
                        useItem(otherParts[0]);
                    } else {
                        useItemOn(otherParts[0], otherParts[1]);
                    }
                }
                break;
            case "load":
                try {
                    List<Object> itemList = save.loadSave().getObjects();
                    player = new Player(player.getName());
                    for (Object item : itemList.toArray()) {
                        player.addItem((Item) item);
                    }
                    running = true;
                    examineRoom(currentRoom);
                } catch (java.io.IOException e) {
                    out.println("Load Failed!");
                }
            case "help":
                out.println("Yeah, you definitely need help");
                break;
            case "restart":
            case "reload":
                out.println("K.");
                reset();
                start();
                break;
            default:
                out.printf("I don't understand %s!\n", command);
                break;
        }
    }

    private void useItemOn(String name, String onName) {
        Item item = findItemIn(player.getInventory(), name);
        if (item == null) {
            out.printf("You don't have a %s to use\n", name);
        } else {
            Item onItem = findItemIn(player.getInventory(), onName);
            if (onItem == null) {
                out.printf("You don't have a %s to use a %s on\n", onName, item.getName());
            } else {
                item.useOn(onItem);
            }
        }
    }

    private void useItem(String name) {
        Item item = findItemIn(player.getInventory(), name);
        if (item == null) {
            out.printf("You don't have a %s to use\n", name);
        } else {
            item.use();
        }
    }

    private void equipItem(String name) {
        Item item = findItemIn(player.getInventory(), name);
        if (item instanceof Equippable) {
            player.equip((Equippable) item);
            out.printf("You equip the %s, which boosts your stats ", item.getName());
            if (item instanceof Armor) {
                out.printf("Defense +%d\n", ((Armor) item).getDefense());
            } else if (item instanceof Weapon) {
                out.printf("Attack +%d\n", ((Weapon) item).getAttack());
            } else {
                out.println("??? +???");
            }
        } else {
            out.printf("You can't equip a %s!\n", item.getName());
        }
    }

    private void dropItem(String name) {
        Item item = findItemIn(player.getInventory(), name);
        if (item != null) {
            transfer(player.getInventory(), currentRoom.getItems(), item);
            out.printf("You drop the %s on the ground\n", item.getName());
        } else {
            out.printf("You don't have a %s to drop\n", name);
        }
    }

    private void showInventory() {
        out.println("Player Inventory:");
        List<Item> inventory = player.getInventory();
        if (inventory.size() == 0)
            out.println("dust");
        for (Item item : inventory) {
            out.printf(" * %s - %s\n", item.getName(), item.getDescription());
        }
    }

    private void takeItem(String name) {
        List<Item> inventory = player.getInventory();
        Item item = findItemIn(currentRoom.getItems(), name);
        if (item != null) {
            if (inventory.contains(backpack)) {
                transfer(currentRoom.getItems(), inventory, item);
                out.printf("You shove the %s in your bag\n", item.getName());
            } else {
                if (inventory.size() >= 1) {
                    out.println("You don't have enough room for that in your hands...");
                } else {
                    transfer(currentRoom.getItems(), inventory, item);
                    out.printf("You pick up the %s\n", item.getName());
                }
            }

        } else {
            out.printf("There isn't a(n) %s in the area!\n", name);
        }
    }

    private void navigate(String direction) {
        switch (direction) {
            case "north":
            case "n":
                navigate(currentRoom.goNorth());
                break;
            case "east":
            case "e":
                navigate(currentRoom.goEast());
                break;
            case "south":
            case "s":
                navigate(currentRoom.goSouth());
                break;
            case "west":
            case "w":
                navigate(currentRoom.goWest());
                break;
            case "up":
            case "u":
                navigate(currentRoom.goUp());
                break;
            case "down":
            case "d":
                navigate(currentRoom.goDown());
                break;
        }
    }

    private void navigate(Room room) {
        if (room != null) {
            currentRoom = room;
            examineRoom(currentRoom);
        }
    }

    private void examineRoom(Room room) {
        out.printf("-- %s --\n", room.getName());
        out.println(room.getDescription());
        for (Item item : room.getItems()) {
            out.printf("There is a(n) %s in the room\n", item.getName());
        }
        for (Character character : room.getCharacters()) {
            out.printf("There is a(n) %s in the room\n", character.getName());
        }
    }

    private void examineThing(String name) {
        for (Item item : currentRoom.getItems()) {
            if (item.getName().toLowerCase().equals(name)) {
                out.printf("%s - %s\n", item.getName(), item.getDescription());
                return;
            }
        }
        for (Character character : currentRoom.getCharacters()) {
            if (character.getName().toLowerCase().equals(name)) {
                out.printf("%s - %s\n", character.getName(), character.getDescription());
                return;
            }
        }
        out.printf("There isn't a(n) %s in this area!\n", name);
    }

    private void printMap() {
        out.println("                                            WORLD MAP");
        out.println("                  Subsurface                                              The Beneath");
        out.println("╭─────────────────────┴┴───────────────────────╮╭──────────────────────────────┴┴──────────────────────────────╮");
        out.println("│╔════════════╗  ╔════════════╗                ││                                                              │");
        out.println("│║  Wizard's  ║  ║  Wizard's  ║                ││                                                              │");
        out.println("│║  Wardrobe  ║  ║   Grotto   ║                ││                                                              │");
        out.println("│║            ╚══╝            ║                ││                                                              │");
        out.println("│║            ╔══╗            ║                ││                                                              │");
        out.println("│║   Robes,   ║  ║ Old Staff, ║                ││                                                              │");
        out.println("│║    Hat     ║  ║ Spellbook  ║                ││                                                              │");
        out.println("│╚════════════╝  ╚════╗  ╔════╝                ││                                                              │");
        out.println("│                ╔════╝  ╚════╗  ╔════════════╗││╔════════════╗                                                │");
        out.println("│                ║ Grate Room ║  ║   Vault    ║││║Rising Room ║                                                │");
        out.println("│                ║  (Start)   ║  ║            ║││║            ║                                                │");
        out.println("│                ║    ╔══╗    ╚══╝            ║││║    ╔══╗    ║                                                │");
        out.println("│                ║    ║\\/║    ╔══╗            ║││║    ║/\\║    ║                                                │");
        out.println("│                ║    ╚══╝    ║  ║  Warp Ring ║││║    ╚══╝    ║                                                │");
        out.println("│                ║            ║  ║    Rock    ║││║            ║                                                │");
        out.println("│                ╚════╗  ╔════╝  ╚════════════╝││╚════╗  ╔════╝                                                │");
        out.println("│                ╔════╝  ╚════╗                ││╔════╝  ╚════╗  ╔════════════╗  ╔════════════╗  ╔════════════╗│");
        out.println("│                ║ Storeroom  ║                ││║   Winding  ║  ║  Crystal   ║  ║  Crystal   ║  ║Throne Room ║│");
        out.println("│                ║            ║                ││║   Tunnel   ║  ║  Cavern    ║  ║    Hall    ║  ║            ║│");
        out.println("│                ║            ║                ││║            ╚══╝            ╚══╝            ╚══╝            ║│");
        out.println("│                ║    Coal,   ║                ││║            ╔══╗   Flint,   ╔══╗            ╔══╗            ║│");
        out.println("│                ║ Glow Moss, ║                ││║            ║  ║  Crystal,  ║  ║  Pickaxe   ║  ║   Lizard   ║│");
        out.println("│                ║  Backpack  ║                ││║            ║  ║  Lantern   ║  ║ War Hammer ║  ║   Warrior  ║│");
        out.println("│                ╚════════════╝                ││╚════════════╝  ╚════════════╝  ╚════════════╝  ╚════════════╝│");
        out.println("╰──────────────────────────────────────────────╯╰──────────────────────────────────────────────────────────────╯");
    }
}
