import java.io.PrintStream;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
/**
 * Project 14 The Zorkening!
 * Main Class
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
        if (instance == null)  instance = new Main();
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
        Room wizardsGrotto = new Room("Wizard's ", "A small room, with a skylight above. There is a door to the East");
        Room grateRoom = new Room("Grate Room", "A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits to the north, east, and west.");
        grateRoom.addCharacter(new Character("Grate", "A metal grate in the floor, about three feet square, just big enough for you to fit through. There are tiny points of light rising up from the grate.", new Item[0], 0, 0, 0, false));
        Room vault = new Room("Vault", "A large ancient vault");
        Room storeRoom = new Room("Storeroom", "A musty old storeroom");
        Room risingRoom = new Room("Rising Room", "A mystic force pushes you upward towards a 3 foot square grate. There is a small exit to the South");
        Room windingTunnel = new Room("Winding Tunnel", "A tunnel that twists and turns");
        Room crystalCavern = new Room("Crystal Cavern", "A cavern with giant glowing crystals protruding from the wall");
        Room crystalHall = new Room("Crystal Hall", "A large hall with the walls carved from some crystal. There are exits to the East and West");
        Room throneRoom = new Room("Throne Room", "A great room with a slightly undersized crystal throne. There is an exit to the West");

        Item moss = new Item("Glow Moss", "A clump of glowing moss");
        Item backpack = new Item("Leather Backpack", "A simple sturdy leather backpack");
        Item coal = new Item("Coal", "A large quantity of coal");
        Item staff = new Item("Old Staff", "An old wooden staff with a cavity at the top");
        Item spellBook = new Item("Ancient Spellbook", "An ancient tome with letters that seem to shift across the page");
        Armor robe = new Armor("Wizard's Robe", "A set of dark robes with symbols along the hem. +3 Defense", 3);
        Armor hat = new Armor("Wizard's Hat", "A pointy hat that makes your ears buzz when you put it on. +1 Defense", 1);
        Weapon hammer = new Weapon("War Hammer", "A mighty hammer of smashing things. +8 Attack", 8);
        Item flint = new Item("Piece of Flint", "A small black piece of flint");
        Item crystal = new Item("Crystal Shard", "A glowing shard of crystal");
        Item lantern = new Item("Lantern", "A small lantern with plenty of fuel");
        Item pickaxe = new Item("Rusty Pickaxe", "An old rusty worn pickaxe");

        wizardsWardrobe.setEast(wizardsGrotto);
        wizardsWardrobe.addItem(hat);
        wizardsWardrobe.addItem(robe);

        wizardsGrotto.setWest(wizardsWardrobe);
        wizardsGrotto.setSouth(grateRoom);
        wizardsGrotto.addItem(spellBook);
        wizardsGrotto.addItem(staff);

        grateRoom.setNorth(wizardsGrotto);
        grateRoom.setEast(vault);
        grateRoom.setSouth(storeRoom);
        grateRoom.setDown(risingRoom);

        vault.setWest(grateRoom);

        storeRoom.setNorth(grateRoom);
        storeRoom.addItem(coal);
        storeRoom.addItem(backpack);
        storeRoom.addItem(moss);

        risingRoom.setUp(grateRoom);
        risingRoom.setSouth(windingTunnel);

        windingTunnel.setNorth(risingRoom);
        windingTunnel.setEast(crystalCavern);

        crystalCavern.setWest(windingTunnel);
        crystalCavern.setEast(crystalHall);

        crystalHall.setWest(crystalCavern);
        crystalHall.setEast(throneRoom);
        crystalHall.addItem(lantern);
        //Flint and Shard added in game logic

        throneRoom.setWest(crystalHall);
        throneRoom.addItem(hammer);
        throneRoom.addItem(pickaxe);

        currentRoom = grateRoom;
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
            break;
            case "examine":
            if (parts.length == 1) {
                examineRoom(currentRoom);
            } else if (parts.length == 2) {
                String itemName = parts[1];
                examineThing(currentRoom, itemName);
            } else {
                out.println("I can't examine more than one thing!");
            }
            break;
            case "take":
            break;
            case "use":
            break;
            case "save":
            System.out.println("Save Game currently no implemented");
            break;
            default:
            out.printf("I dont understand %s!\n", command);
            break;
        }
    }

    private void navigate(String direction) {
        switch (direction) {
            case "north":
            navigate(currentRoom.getNorth());
            break;
            case "east":
            navigate(currentRoom.getEast());
            break;
            case "south":
            navigate(currentRoom.getSouth());
            break;
            case "west":
            navigate(currentRoom.getWest());
            break;
            case "up":
            navigate(currentRoom.getUp());
            break;
            case "down":
            navigate(currentRoom.getDown());
            break;
        }
    }

    private void navigate(Room room) {
        if (room == null) {
            out.println("You run your head into a wall and now have a slight headache");
            return;
        }else{
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
