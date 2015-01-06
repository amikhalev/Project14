import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.xml.bind.DatatypeConverter;
public class SaveManager {
    private World world;
    private String line;
    private Scanner scanner = new Scanner(System.in);
    private File save;
    private List<Object> objects = new ArrayList<Object>();

    public int saveGame(World world) throws IOException {
        System.out.println("Please enter a directory (folder) path to save your game (ex: C:\\\\Games\\Zork Clone or /Users/Example User/Documents/Zork Clone) or press enter to cancel");
        System.out.println("If you want to specify a relative path (not starting with C:\\ or /), your game will be saved in the same directory as the game");
        String[] parts = scanner.nextLine().toLowerCase().split(" ");
        if (parts[0].length() < 1) {
            System.out.println("Game Save Cancelled");
            return 0;
        }
        this.world = world;
        return promptSave(parts);
    }

    private int promptSave(String[] parts) throws IOException {
        switch (parts[0]) {
        case "cancel":
        case "Cancel":
            return -1;
        case "quit":
        case "exit":
            return 0;
        default:
            save = new File(parts[0]);
            FileWriter fileWrite = new FileWriter(save);
            PrintWriter printWrite = new PrintWriter(fileWrite);
            if(save.exists() && save.isFile()) {
                System.out.println("Save file already exists. Do you want to overwrite it? (y/n)");
                if(scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")) {
                    if(save.canRead() && save.canWrite()) {
                        System.out.println("Saving . . .");
                        objects.clear();
                        objects = world.getObjects();
                        Object[] objArray = objects.toArray();
                        for(Object obj : objArray) {
                            printWrite.println(new String(DatatypeConverter.printBase64Binary(obj.toString().getBytes())));
                        }
                        printWrite.close();
                        fileWrite.close();
                        System.out.println("Done!");
                    } else {
                        System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                        promptSave(parts);
                        if(!save.exists()) {
                            System.out.println("The path you specified does not represent a valid directory");
                        } else if(!save.exists() || !save.isFile()) {
                            System.out.println("Save File does not exists. Do you want to create a new save? (y/n)");
                            if(scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")) {
                                try {
                                    save.createNewFile();
                                } catch(java.io.IOException x) {
                                    System.err.format(">IOException: %s%n", x);
                                }
                                if(save.canRead() && save.canWrite()) {
                                    //Save file
                                } else {
                                    System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                                    promptSave(parts);
                                }
                            } else {
                                System.out.println("Cancelled create new save");
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }

    public World loadSave() throws IOException {
        System.out.println("Please enter a directory (folder) path to your saved game (ex: C:\\\\Games\\Zork Clone\\save.dat or /Users/Example User/Documents/Zork Clone/save.dat) or press enter to cancel");
        System.out.println("If you want to specify a relative path (not starting with C:\\ or /), your save should be located in the same directory as the game");
        String[] parts = scanner.nextLine().split(" ");
        Room[] rooms;
        if (parts[0].length() < 1) {
            System.out.println("Game Save Cancelled");
        }
        return promptLoad(parts);
    }

    private World promptLoad(String[] parts) throws IOException {
        switch (parts[0]) {
        case "cancel":
        case "Cancel":
            return null;
        default:
            save = new File(parts[0]);
            Scanner fScan = new Scanner(save);
            if(save.exists() && save.isFile()) {
                System.out.println("Save file found: " + parts[0]);
                if(save.canRead() && save.canWrite()) {
                    System.out.println("Loading . . .");
                    objects.clear();
                    while(fScan.hasNext()) {
                        objects.add(new String(DatatypeConverter.parseBase64Binary(fScan.nextLine())));
                    }

                    for(int i = objects.toArray().length/2; i < objects.toArray().length -1;) {
                        objects.remove(i);
                    }

                    fScan.close();
                    world = new World(objects.toArray());
                    System.out.println("Done!");
                } else {
                    System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                    promptLoad(parts);
                }
            } else if(!save.exists() || !save.isFile()) {
                System.out.println("File not found. Check for typos or incorrect path notation and try again");
                promptLoad(parts);
            }
            return world;
        }
    }
}
