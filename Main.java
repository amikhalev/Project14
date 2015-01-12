import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    private void makeRooms() {
        Room wizardsWardrobe = new Room("Wizard's Wardrobe", "A small wardrobe with a door to the West");
        Room wizardsGrotto = new Room("Wizard's Spellchamber", "A small room, with a skylight above. There is a door to the East");
        Room grateRoom = new Room("Grate Room", "A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits to the north, east, and south.");
        Room vault = new Room("Vault", "A large ancient vault");
        Room storeRoom = new Room("Storeroom", "A musty old storeroom");
        Room risingRoom = new Room("Rising Room", "A mystic force pushes you upward towards a 3 foot square grate. There is a small exit to the South");
        Room windingTunnel = new Room("Winding Tunnel", "A tunnel that twists and turns");
        Room crystalCavern = new Room("Crystal Cavern", "A cavern with giant glowing crystals protruding from the wall. There are exits to the east and west.");
        Room crystalHall = new Room("Crystal Hall", "A large hall with the walls carved from some crystal. There are exits to the East and West");
        Room throneRoom = new Room("Throne Room", "A great room with a slightly undersized crystal throne. There is an exit to the West");

        Item moss = new Item("Moss", "A clump of glowing moss");
        Item backpack = new Item("Backpack", "A simple sturdy leather backpack");
        Item coal = new Item("Coal", "A large quantity of coal");
        Item staff = new Item("Staff", "An old wooden staff with a cavity at the top");
        Item spellBook = new Item("Spellbook", "An ancient tome with letters that seem to shift across the page. Some you can translate:\n\tTransmutation:\n\tUsing a ... it is possible to ...\n\t for example chocolate (agherm)...\n\n The rest of the book is stained, burned, and unreadable");
        Armor robe = new Armor("Robe", "A set of dark robes with symbols along the hem. +3 Defense", 3);
        Armor hat = new Armor("Hat", "A pointy hat that makes your ears buzz when you put it on. +1 Defense", 1);
        Weapon hammer = new Weapon("Hammer", "A mighty hammer of smashing things. +8 Attack", 8);
        Item flint = new Item("Flint", "A small black piece of flint");
        Item crystal = new Item("Shard", "A glowing shard of crystal");
        Item lantern = new Item("Lantern", "A small lantern with plenty of fuel");
        Item pickaxe = new Item("Rusty Pickaxe", "An old rusty worn pickaxe");
        Item warpRing = new Item("Warp Ring", "A ring that you think can make you travel in time");

        wizardsWardrobe.setEast(wizardsGrotto);
        wizardsGrotto.setWest(wizardsWardrobe);
        wizardsGrotto.setSouth(grateRoom);
        grateRoom.setNorth(wizardsGrotto);
        grateRoom.setEast(vault);
        grateRoom.setSouth(storeRoom);
        grateRoom.setDown(risingRoom);
        grateRoom.addCharacter(new Character("Grate", "A metal grate in the floor, about three feet square, just big enough for you to fit through. There are tiny points of light rising up from the grate.", new Item[0], 0, 0, 0, false));

        vault.setWest(grateRoom);
        vault.addItem(warpRing);

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
            storeRoom.addItem(moss);
            throneRoom.addItem(hammer);
            throneRoom.addItem(pickaxe);
            crystalHall.addItem(lantern);
        } else if (!player.getInventory().contains(hat)) {
            wizardsWardrobe.addItem(hat);
        } else if (!player.getInventory().contains(robe)) {
            wizardsWardrobe.addItem(robe);
        } else if (!player.getInventory().contains(spellBook)) {
            wizardsGrotto.addItem(spellBook);
        } else if (!player.getInventory().contains(staff)) {
            wizardsGrotto.addItem(staff);
        } else if (!player.getInventory().contains(coal)) {
            storeRoom.addItem(coal);
        } else if (!player.getInventory().contains(backpack)) {
            storeRoom.addItem(backpack);
        } else if (!player.getInventory().contains(moss)) {
            storeRoom.addItem(moss);
        } else if (!player.getInventory().contains(hat)) {
            throneRoom.addItem(hat);
        } else if (!player.getInventory().contains(hat)) {
            throneRoom.addItem(hat);
        } else if (!player.getInventory().contains(hat)) {
            crystalHall.addItem(hat);
        }
    }

    public void reset() {
        score = 0;
        running = false;
        makeRooms();
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
        out.println("Project 14");
        out.println("Made by Alex Mikhalev and Tavi Kohn");
        out.println("\nPlease enter your name:");
        String line = scanner.nextLine();
        player = new Player(line);

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
                String cheatCode = " " + Arrays.asList(parts).subList(1, parts.length);
                switch (cheatCode) {
                    case "i am the all seeing schreiber!":
                        printMap();
                        break;
                    default:
                        out.printf("\"%s\" isn't a cheat code, scrub!\n", cheatCode);
                }
                break;
            case "go":
                if (parts.length != 2)
                    out.println("I don't understand that");
                else
                    navigate(parts[1]);
                break;
            case "attack":
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
                    examineThing(currentRoom, rest);
                }
                break;
            case "grab":
            case "take":
                if (parts.length >= 2) {
                    takeItem(currentRoom, rest);
                } else {
                    out.println("What do you want to take?");
                }
                break;
            case "drop":
                if (parts.length >= 2) {
                    dropItem(currentRoom, rest);
                } else {
                    out.println("What do you want to drop?");
                }
                break;
            case "use":
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
            default:
                out.printf("I don't understand %s!\n", command);
                break;
        }
    }

    private void dropItem(Room room, String name) {
        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(name)) {
                room.addItem(item);
                player.getInventory().remove(item);
                out.printf("For some odd reason, you drop the %s on the ground\n", item.getName());
                return;
            }
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

    private void takeItem(Room room, String name) {
        for (Item item : room.getItems()) {
            if (item.getName().toLowerCase().matches(".*" + name + ".*")) {
                room.getItems().remove(item);
                player.addItem(item);
                out.printf("You shove the %s in your bag\n", item.getName());
                return;
            }
        }
        out.printf("There isn't a(n) %s in the area!\n", name);
    }

    private void navigate(String direction) {
        switch (direction) {
            case "north":
            case "n":
                navigate(currentRoom.getNorth());
                break;
            case "east":
            case "e":
                navigate(currentRoom.getEast());
                break;
            case "south":
            case "s":
                navigate(currentRoom.getSouth());
                break;
            case "west":
            case "w":
                navigate(currentRoom.getWest());
                break;
            case "up":
            case "u":
                navigate(currentRoom.getUp());
                break;
            case "down":
            case "d":
                navigate(currentRoom.getDown());
                break;
        }
    }

    private void navigate(Room room) {
        if (room == null) {
            out.println("You run your head into a wall and now have a slight headache");
        } else {
            currentRoom = room;
            examineRoom(currentRoom);
            if (Objects.equals(currentRoom.getName(), "Rising Room") && !player.getInventory().stream().anyMatch((i) -> i.getName().equals("Coal"))) {
                currentRoom = currentRoom.getUp();
                examineRoom(currentRoom);
            }
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

    private void examineThing(Room room, String name) {
        for (Item item : room.getItems()) {
            if (item.getName().toLowerCase().equals(name)) {
                out.printf("%s - %s\n", item.getName(), item.getDescription());
                return;
            }
        }
        for (Character character : room.getCharacters()) {
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
        out.println("│                ║            ║  ║            ║││║            ║                                                │");
        out.println("│                ╚════╗  ╔════╝  ╚════════════╝││╚════╗  ╔════╝                                                │");
        out.println("│                ╔════╝  ╚════╗                ││╔════╝  ╚════╗  ╔════════════╗  ╔════════════╗  ╔════════════╗│");
        out.println("│                ║ Storeroom  ║                ││║   Winding  ║  ║  Crystal   ║  ║  Crystal   ║  ║Throne Room ║│");
        out.println("│                ║            ║                ││║   Tunnel   ║  ║  Cavern    ║  ║    Hall    ║  ║            ║│");
        out.println("│                ║            ║                ││║            ╚══╝            ╚══╝            ╚══╝            ║│");
        out.println("│                ║    Coal,   ║                ││║            ╔══╗   Flint,   ╔══╗            ╔══╗            ║│");
        out.println("│                ║ Glow Moss, ║                ││║            ║  ║  Crystal,  ║  ║            ║  ║  Pickaxe   ║│");
        out.println("│                ║  Backpack  ║                ││║            ║  ║  Lantern   ║  ║            ║  ║ War Hammer ║│");
        out.println("│                ╚════════════╝                ││╚════════════╝  ╚════════════╝  ╚════════════╝  ╚════════════╝│");
        out.println("╰──────────────────────────────────────────────╯╰──────────────────────────────────────────────────────────────╯");
    }
}
