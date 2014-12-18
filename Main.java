import java.io.PrintStream;
import java.util.Scanner;
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

    public Main() {
        scanner = new Scanner(System.in);
        out = System.out;
        reset();
    }

    public static Main getInstance() {
        if (instance == null)
            instance = new Main();
        return instance;
    }

    protected static void addScore(int score) {
        getInstance().incrementScore(score);
    }

    protected static void readCommand(String command) {
        getInstance().process(command);
    }

    protected static void main(String[] args) {
        getInstance().start();
    }

    protected void reset() {
        score = 0;
        running = false;
    }

    protected int getScore() {
        return score;
    }

    protected void setScore(int score) {
        this.score = score;
    }

    protected void incrementScore(int score) {
        this.score += score;
    }

    protected void start() {
        //Progress Bar Chars:▏▎▍▌▐▋▊▉█
        System.out.print("Project 14\nMade by Alex Mikhalev and Tavi Kohn\nCreating Rooms      |--------------------|");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }
        //create rooms here
        System.out.print("\fProject 14\nMade by Alex Mikhalev and Tavi Kohn\nLinking Rooms       |▉▉▉▉----------------|");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }
        //create room n, s, e, w relations
        System.out.print("\fProject 14\nMade by Alex Mikhalev and Tavi Kohn\nCreating Items      |▉▉▉▉▉▉▉▉------------|");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }
        //create items
        System.out.print("\fProject 14\nMade by Alex Mikhalev and Tavi Kohn\nCreating Characters |▉▉▉▉▉▉▉▉▉▉▉▉--------|");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }
        //create characters (including doors, interactive features etc.)
        System.out.print("\fProject 14\nMade by Alex Mikhalev and Tavi Kohn\nPopulating Rooms    |▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉----|");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }
        //assign characters and items to rooms
        System.out.println("\fProject 14\nMade by Alex Mikhalev and Tavi Kohn\nDone                |▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉|");
        running = true;
        while (running) {
            prompt();
        }
    }

    protected void prompt() {
        System.out.print(" > ");
        String line = scanner.nextLine();
        line = line.toLowerCase().trim();
        process(line);
    }

    protected void process(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            System.out.println("What did you say?");
            return;
        }
        switch (parts[0]) {
            case "quit":
            case "exit":
            System.out.println("Bye!");
            running = false;
            break;
            case "herr":
            if(parts[1].equals("schreiber") && parts[2].equals("demands") && parts[3].equals("a") && parts[4].equals("map!")) {
                printMap();
            } else {
                System.out.println("I don't understand that");
            }
            break;
            default:
            System.out.printf("You said %s!\n", command);
            break;
        }
    }

    private void printMap() {
        System.out.println("                                            WORLD MAP");
        System.out.println("                  Subsurface                                              The Beneath");
        System.out.println("╭─────────────────────┴┴───────────────────────╮╭──────────────────────────────┴┴──────────────────────────────╮");
        System.out.println("│╔════════════╗  ╔════════════╗                ││                                                              │");
        System.out.println("│║  Wizard's  ║  ║  Wizard's  ║                ││                                                              │");
        System.out.println("│║  Wardrobe  ║  ║   Grotto   ║                ││                                                              │");
        System.out.println("│║            ╚══╝            ║                ││                                                              │");
        System.out.println("│║            ╔══╗            ║                ││                                                              │");
        System.out.println("│║   Robes,   ║  ║ Old Staff, ║                ││                                                              │");
        System.out.println("│║    Hat     ║  ║ Spellbook  ║                ││                                                              │");
        System.out.println("│╚════════════╝  ╚════╗  ╔════╝                ││                                                              │");
        System.out.println("│                ╔════╝  ╚════╗  ╔════════════╗││╔════════════╗                                                │");
        System.out.println("│                ║ Grate Room ║  ║   Vault    ║││║Rising Room ║                                                │");
        System.out.println("│                ║  (Start)   ║  ║            ║││║            ║                                                │");
        System.out.println("│                ║    ╔══╗    ╚══╝            ║││║    ╔══╗    ║                                                │");
        System.out.println("│                ║    ║\\/║    ╔══╗            ║││║    ║/\\║    ║                                                │");
        System.out.println("│                ║    ╚══╝    ║  ║  Warp Ring ║││║    ╚══╝    ║                                                │");
        System.out.println("│                ║            ║  ║            ║││║            ║                                                │");
        System.out.println("│                ╚════╗  ╔════╝  ╚════════════╝││╚════╗  ╔════╝                                                │");
        System.out.println("│                ╔════╝  ╚════╗                ││╔════╝  ╚════╗  ╔════════════╗  ╔════════════╗  ╔════════════╗│");
        System.out.println("│                ║ Storeroom  ║                ││║   Winding  ║  ║  Crystal   ║  ║  Crystal   ║  ║Throne Room ║│");
        System.out.println("│                ║            ║                ││║   Tunnel   ║  ║  Cavern    ║  ║    Hall    ║  ║            ║│");
        System.out.println("│                ║            ║                ││║            ╚══╝            ╚══╝            ╚══╝            ║│");
        System.out.println("│                ║    Coal,   ║                ││║            ╔══╗   Flint,   ╔══╗            ╔══╗            ║│");
        System.out.println("│                ║ Glow Moss, ║                ││║            ║  ║  Crystal,  ║  ║            ║  ║  Pickaxe   ║│");
        System.out.println("│                ║  Backpack  ║                ││║            ║  ║  Lantern   ║  ║            ║  ║ War Hammer ║│");
        System.out.println("│                ╚════════════╝                ││╚════════════╝  ╚════════════╝  ╚════════════╝  ╚════════════╝│");
        System.out.println("╰──────────────────────────────────────────────╯╰──────────────────────────────────────────────────────────────╯");
    }
}
