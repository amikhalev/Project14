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

    public static void addScore(int score) {
        getInstance().incrementScore(score);
    }

    public static void readCommand(String command) {
        getInstance().process(command);
    }

    public static void main(String[] args) {
        getInstance().start();
    }

    public void reset() {
        score = 0;
        running = false;
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
        System.out.println("Project 14");
        System.out.println("Made by Alex Mikhalev and Tavi Kohn");
        running = true;
        while (running) {
            prompt();
        }
    }

    public void prompt() {
        System.out.print(" > ");
        String line = scanner.nextLine();
        line = line.toLowerCase().trim();
        process(line);
    }

    public void process(String command) {
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
        case "say":
            if(parts[1].equals("herr") && parts[2].equals("schreiber") && parts[3].equals("demands") && parts[4].equals("a") && parts[5].equals("map!")) {
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

    public void printMap() {
        System.out.println("╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗");
        System.out.println("║ Wizard's ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║ Wardrobe ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║ Robes,   ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║ Hat      ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝");
        System.out.println("╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝");
        System.out.println("╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗  ╔══════════╗");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("║          ║  ║          ║  ║          ║  ║          ║  ║          ║  ║          ║");
        System.out.println("╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝  ╚══════════╝");
    }
}
