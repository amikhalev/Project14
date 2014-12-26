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
    private Room[][][] floors;
    private int currentFloor;

    public Main() {
        scanner = new Scanner(System.in);
        out = out.
        reset();
    }

    public static Main getInstance() {
        if (instance == null)
            instance = new Main();
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

    private void generateRooms() {
        Random random = new Random();
        floors = new Room[5][5][5];
        for (int i = 0; i < floors.length; ++i) {
            Room[][] floor = floors[i];
            for (int j = 0; j < floor.length; ++j) {
                for (int k = 0; k < floor.length; ++k) {
                    double num = random.nextDouble();
                    if (num <= .40) {
                        floor[j][k] = makeHallway();
                    } else if (num <= .70) {
                        floor[j][k] = makeEasyMonster();
                    } else if (num <= .90) {
                        floor[j][k] = makeHardMonster();
                    } else {
                        floor[j][k] = makeTreasure();
                    }
                }
            }
            int bossX = (int) Math.floor(random.nextDouble() * 5);
            int bossY = (int) Math.floor(random.nextDouble() * 5);
            floor[bossX][bossY] = makeBoss();
        }
    }

    private Room makeHallway() {
        // TODO: make hallway
        return new Room(new Item[0], new Character[0], "Hallway", "");
    }

    private Room makeEasyMonster() {
        // TODO: make EasyMonster
        return new Room(new Item[0], new Character[0], "EasyMonster", "");
    }

    private Room makeHardMonster() {
        // TODO: make HardMonster
        return new Room(new Item[0], new Character[0], "HardMonster", "");
    }

    private Room makeTreasure() {
        // TODO: make Treasure
        return new Room(new Item[0], new Character[0], "Treasure", "");
    }

    private Room makeBoss() {
        // TODO: make Boss
        return new Room(new Item[0], new Character[0], "Boss", "");
    }

    public void reset() {
        score = 0;
        running = false;
        generateRooms();
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
        while (running) {
            prompt();
        }
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
            String cheatCode = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
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
        // out.println("                                            WORLD MAP");
        // out.println("                  Subsurface                                              The Beneath");
        // out.println("╭─────────────────────┴┴───────────────────────╮╭──────────────────────────────┴┴──────────────────────────────╮");
        // out.println("│╔════════════╗  ╔════════════╗                ││                                                              │");
        // out.println("│║  Wizard's  ║  ║  Wizard's  ║                ││                                                              │");
        // out.println("│║  Wardrobe  ║  ║   Grotto   ║                ││                                                              │");
        // out.println("│║            ╚══╝            ║                ││                                                              │");
        // out.println("│║            ╔══╗            ║                ││                                                              │");
        // out.println("│║   Robes,   ║  ║ Old Staff, ║                ││                                                              │");
        // out.println("│║    Hat     ║  ║ Spellbook  ║                ││                                                              │");
        // out.println("│╚════════════╝  ╚════╗  ╔════╝                ││                                                              │");
        // out.println("│                ╔════╝  ╚════╗  ╔════════════╗││╔════════════╗                                                │");
        // out.println("│                ║ Grate Room ║  ║   Vault    ║││║Rising Room ║                                                │");
        // out.println("│                ║  (Start)   ║  ║            ║││║            ║                                                │");
        // out.println("│                ║    ╔══╗    ╚══╝            ║││║    ╔══╗    ║                                                │");
        // out.println("│                ║    ║\\/║    ╔══╗            ║││║    ║/\\║    ║                                                │");
        // out.println("│                ║    ╚══╝    ║  ║  Warp Ring ║││║    ╚══╝    ║                                                │");
        // out.println("│                ║            ║  ║            ║││║            ║                                                │");
        // out.println("│                ╚════╗  ╔════╝  ╚════════════╝││╚════╗  ╔════╝                                                │");
        // out.println("│                ╔════╝  ╚════╗                ││╔════╝  ╚════╗  ╔════════════╗  ╔════════════╗  ╔════════════╗│");
        // out.println("│                ║ Storeroom  ║                ││║   Winding  ║  ║  Crystal   ║  ║  Crystal   ║  ║Throne Room ║│");
        // out.println("│                ║            ║                ││║   Tunnel   ║  ║  Cavern    ║  ║    Hall    ║  ║            ║│");
        // out.println("│                ║            ║                ││║            ╚══╝            ╚══╝            ╚══╝            ║│");
        // out.println("│                ║    Coal,   ║                ││║            ╔══╗   Flint,   ╔══╗            ╔══╗            ║│");
        // out.println("│                ║ Glow Moss, ║                ││║            ║  ║  Crystal,  ║  ║            ║  ║  Pickaxe   ║│");
        // out.println("│                ║  Backpack  ║                ││║            ║  ║  Lantern   ║  ║            ║  ║ War Hammer ║│");
        // out.println("│                ╚════════════╝                ││╚════════════╝  ╚════════════╝  ╚════════════╝  ╚════════════╝│");
        // out.println("╰──────────────────────────────────────────────╯╰──────────────────────────────────────────────────────────────╯");
        String[] map = new String[8 * 5];
        for (int i = 0; i < map.length; ++i)
            map[i] = "";
        Room[][] rooms = floors[currentFloor];
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[i].length; ++j) {
                Room room = rooms[i][j];
                int k = i * 8;
                map[k] += "╔══════════════╗";
                map[k + 7] += "╚══════════════╝";
                map[k + 1] += String.format("║ %-12s ║", room.getName());
            }
            out.println();
        }
        out.println(String.join("\n", map));
    }
}
