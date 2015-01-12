import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveManager {
    private World world;
    private String line;
    private Scanner scanner = new Scanner(System.in);
    private File save;
    private List<Object> objects = new ArrayList<Object>();

    public void saveGame(World world) throws IOException {
        System.out.println("Please enter a directory (folder) path to save your game (ex: C:\\\\Games\\Zork Clone or /Users/Example User/Documents/Zork Clone) or press enter to cancel");
        System.out.println("If you want to specify a relative path (not starting with C:\\ or /), your game will be saved in the same directory as the game");
        String[] parts = scanner.nextLine().toLowerCase().split(" ");
        if (parts[0].length() < 1) {
            System.out.println("Game Save Cancelled");
        }
        this.world = world;
        promptSave(parts);
    }

    private void promptSave(String[] parts) throws IOException {
        switch (parts[0]) {
            case "cancel":
                System.out.println("Save Canceled");
                break;
            default:
                //save = new File(" " + Arrays.asList(parts).subList(1, parts.length - 1));
                save = new File(parts[0]);
                FileWriter fileWrite = new FileWriter(save);
                PrintWriter printWrite = new PrintWriter(fileWrite);
                if (save.exists() && save.isFile()) {
                    System.out.println("Save file already exists. Do you want to overwrite it? (y/n)");
                    if (scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")) {
                        if (save.canRead() && save.canWrite()) {
                            System.out.println("Saving . . .");
                            objects.clear();
                            objects = world.getObjects();
                            Object[] objArray = objects.toArray();
                            for (Object obj : objArray) {
                                printWrite.println(new String(DatatypeConverter.printBase64Binary(obj.toString().getBytes())));
                            }
                            printWrite.close();
                            fileWrite.close();
                            System.out.println("Done!");
                        } else {
                            System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                            promptSave(parts);
                            if (!save.exists()) {
                                System.out.println("The path you specified does not represent a valid directory");
                            } else if (!save.exists() || !save.isFile()) {
                                System.out.println("Save File does not exists. Do you want to create a new save? (y/n)");
                                if (scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")) {
                                    save.createNewFile();
                                    if (save.canRead() && save.canWrite()) {
                                        System.out.println("Saving . . .");
                                        objects.clear();
                                        objects = world.getObjects();
                                        Object[] objArray = objects.toArray();
                                        for (Object obj : objArray) {
                                            if (obj instanceof Item[]) {
                                                for (Item item : (Item[]) obj) {
                                                    printWrite.println("," + DatatypeConverter.printBase64Binary(item.toString().getBytes()));
                                                }
                                            }
                                            printWrite.close();
                                            fileWrite.close();
                                            System.out.println("Done!");
                                        }
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
                if (save.exists() && save.isFile()) {
                    System.out.println("Save file found: " + parts[0]);
                    if (save.canRead() && save.canWrite()) {
                        System.out.println("Loading . . .");
                        objects.clear();
                        while (fScan.hasNext()) {
                            objects.add(new String(DatatypeConverter.parseBase64Binary(fScan.nextLine())));
                        }
                        for (int i = objects.size() / 2; i < objects.size() - 1; ) {
                            objects.remove(i);
                        }
                        String[] strArray = objects.toArray(new String[objects.size()]);
                        objects.clear();
                        for (String objString : strArray) {
                            String[] objStringArray = objString.split(",");
                            switch (objStringArray[0]) {
                                case "Armor":
                                    objects.add(new Armor(objStringArray[1], objStringArray[2], Integer.parseInt(objStringArray[3])));
                                    break;

                                case "Weapon":
                                    objects.add(new Weapon(objStringArray[1], objStringArray[2], Integer.parseInt(objStringArray[3])));
                                    break;

                                case "Equippable":
                                    objects.add(new Armor(objStringArray[1], objStringArray[2], Integer.parseInt(objStringArray[3])));
                                    break;

                                case "Usable":
                                    objects.add(new Usable(objStringArray[1], objStringArray[2], Integer.parseInt(objStringArray[3]), Integer.parseInt(objStringArray[4]), Integer.parseInt(objStringArray[5])));
                                    break;
                            /*case "Character":
                            Item[] characterItems = new Item[Integer.parseInt(objStringArray[3])];
                            for(int i = 5; i <= characterItems.length; i++){
                            characterItems[i-4] = new Item(objStringArray[i-1], objStringArray[i]); 
                            }
                            objects.add(new Character(objStringArray[1], objStringArray[2], characterItems, Integer.parseInt(objStringArray[objStringArray.length + 4]), Integer.parseInt(objStringArray[characterItems.length + 5]), Integer.parseInt(objStringArray[characterItems.length + 6]), Boolean.parseBoolean(objStringArray[objStringArray.length + 7])));
                            break;*/

                            /*case "Player":
                            Item[] playerItems = new Item[Integer.parseInt(objStringArray[2])];
                            for(int i = 3; i <= playerItems.length; i++){
                            playerItems[i-3] = new Item(objStringArray[i-1], objStringArray[i]); 
                            }
                            objects.add(new Player(objStringArray[1], playerItems));
                            break;*/
                                case "Item":
                                    objects.add(new Item(objStringArray[1], objStringArray[2]));
                                    break;

                            /*case "Room":

                            break;*/
                            }
                        }
                        fScan.close();
                        world = new World(objects.toArray());
                        System.out.println("Done!");
                    } else {
                        System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                        promptLoad(parts);
                    }
                } else if (!save.exists() || !save.isFile()) {
                    System.out.println("File not found. Check for typos or incorrect path notation and try again");
                    promptLoad(parts);
                }
                return world;
        }
    }
}
