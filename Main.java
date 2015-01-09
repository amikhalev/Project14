import java.util.*;
import java.io.*;
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
        Room wizardsWardrobe = new Room("Wizard's Wardrobe", "A small wardrobe with a door to the east");
        Room wizardsGrotto = new Room("Wizard's Grotto", "A small room, with a skylight above. There is a archway to the south and a door to the west.");
        wizardsWardrobe.addItem(new Item("Old carved stuff (Broken)", "Old-looking pottery and stuff thats broken");
                                Room grateRoom = new Room("Grate Room", "A rectangular room with old-looking stone walls. There is a small puddle of water on the floor. There is an archway to the north, an iron door to the east, and a wooden door to the south.");
                                grateRoom.addCharacter(new Character("Grate", "A metal grate in the floor, about three feet square, just big enough for you to fit through. There are tiny points of light rising up from the grate.", new Item[0], 0, 0, 0, false));
                                Room vault = new Room("Vault", "A vault with an iron door to the west.");
                                vault.addItem(new Item("Warp Ring", "A shiny irridescent ring with writing engraved on the surface."));
                                Room storeRoom = new Room("Storeroom", "A storeroom with an exit to the north.");
                                Room risingRoom = new Room("Rising Room", "A mystic force trys to lift you up. There is an exit to the south.");
                                Room windingTunnel = new Room("Winding Tunnel", "A rocky tunnel that has many twists and turns.");
                                Room crystalCavern = new Room("Crystal Cavern", "A cavern with walls covered in crystals. There are exits to the east and west");
                                Room crystalHall = new Room("Crystal Hall", "A large hall with the walls carved from some crystal. There are exits to the east and west");
                                Room throneRoom = new Room("Throne Room", "A great room with a slightly undersized crystal throne. There is an exit to the west");

                                Item moss = new Item("Glow Moss", "A clump of glowing moss");

                                wizardsWardrobe.setEast(wizardsGrotto);
                                wizardsGrotto.setWest(wizardsWardrobe);
                                wizardsGrotto.setSouth(grateRoom);
                                grateRoom.setNorth(wizardsGrotto);
                                grateRoom.setEast(vault);
                                vault.setWest(grateRoom);
                                grateRoom.setSouth(storeRoom);
                                storeRoom.setNorth(grateRoom);
                                grateRoom.setDown(risingRoom);
                                risingRoom.setUp(grateRoom);
                                risingRoom.setSouth(windingTunnel);
                                windingTunnel.setNorth(risingRoom);
                                windingTunnel.setEast(crystalCavern);
                                crystalCavern.setWest(windingTunnel);
                                crystalCavern.setEast(crystalHall);
                                crystalHall.setWest(crystalCavern);
                                crystalHall.setEast(throneRoom);
                                throneRoom.setWest(crystalHall);

                                currentRoom = grateRoom;
    }

    public void reset() {
        score = 0;
        running = false;
        player = new Player("TODO: read name from player");
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
        case "inventory":
        case "items":
            showInventory();
            break;
        case "walk":
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
        case "use":
            break;
        default:
            out.printf("I dont understand %s!\n", command);
            break;
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
        default:
            out.printf("As you try to go %s, you travel to the fourth dimension, but you come back quickly\n", direction);
        }
    }

    private void navigate(Room room) {
        if (room == null) {
            out.println("You run your head into a wall and get a concussion");
            return;
        }
        currentRoom = room;
        examineRoom(currentRoom);
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
            if (item.getName().toLowerCase().matches(name)) {
                out.printf("%s - %s\n", item.getName(), item.getDescription());
                return;
            }
        }
        for (Character character : room.getCharacters()) {
            if (character.getName().toLowerCase().matches(name)) {
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
