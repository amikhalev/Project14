import java.io.PrintStream;
import java.util.Scanner;
//<<<<<<< Updated upstream
import java.util.Random;    //if we ever get to random worldgen
import java.util.Arrays;
//=======
//>>>>>>> Stashed changes
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
    private GameSaver save = new GameSaver();
    //<<<<<<< Updated upstream
    private Player player;
    private Room currentRoom;
    //=======
    protected Player player1;
    //     >>>>>>> Stashed changes

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
        Room wizardsWardrobe = new Room(new Item[0], new Character[0], "Wizard's Wardrobe", "The wardrobe of the Wizard!");
        Room wizardsGrotto = new Room(new Item[0], new Character[0], "Wizard's Grotto", "The grotto of the Wizard!");
        Room grateRoom = new Room(new Item[0], new Character[0], "Grate Room", "A room with a grate!");
        Room vault = new Room(new Item[0], new Character[0], "Vault", "A room with a vault!");
        Room storeroom = new Room(new Item[0], new Character[0], "Storeroom", "A room with stores!");
        Room risingRoom = new Room(new Item[0], new Character[0], "Rising Room", "The room in which you rise!");
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
        /*Room wardrobe = new Room(
                new List<Item>(
                    new Armor("Wizard's Robes", "an old worn set of wizard's robes", 1), new Armor("Wizard's Hat", "an old worn wizard's hat", 1)
                )
            );*/
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
            default:
            out.printf("You said %s!\n", command);
            break;
        }
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