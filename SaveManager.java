import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.io.BufferedWriter;
//import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.File;
import java.util.Scanner;
import java.util.Scanner;
public class SaveManager {
    private String line;
    private Scanner scanner = new Scanner(System.in);
    private ObjectInputStream oIn;
    private ObjectOutputStream oOut;
    private File save;
    private World world = null;
    public SaveManager(){

    }

    public int saveGame() {
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

    private char promptSave(String[] parts){
        switch (parts[0]) {
            case "cancel":
            case "Cancel":

            switch (parts[0]) {
                case "quit":
                case "exit":
                return (char)-1;
                case "cancel":
                return 0;
                default:
                Path path = Paths.get(parts[0]);
                save = new File(path.toUri());

                try{
                    oIn = new ObjectInputStream(Files.newInputStream(path));
                }catch(java.io.IOException x){
                    System.err.format(">IOException: %s%n", x);
                }
                if(save.exists() && save.isFile()){
                    System.out.println("Save file already exists. Do you want to overwrite it? (y/n)");
                    if(scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")){
                        if(save.canRead() && save.canWrite()){
                            try{
                                oOut.writeObject(world);
                            }catch(java.io.IOException x){
                                System.err.format(">IOException: %s%n", x);
                            }catch(java.lang.NullPointerException x){
                                System.err.format(">NullPointerException: %s%n", x);
                            }
                            try{
                                oOut.close();
                            }catch(java.io.IOException x){
                                System.err.format(">IOException: %s%n", x);
                            }
                        }else{
                            System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                            promptSave(parts);
                            try{
                                oIn = new ObjectInputStream(Files.newInputStream(path));
                            }catch(java.io.IOException x){
                                System.err.format(">IOException: %s%n", x);
                            }
                            if(!save.exists()) {
                                System.out.println("The path you specified does not represent a valid directory");
                            } else if(save.exists() && save.isDirectory()) {
                                System.out.println("Save directory already exists. Do you want to overwrite it (y/n)");
                                if(scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")) {
                                    if(save.canRead() && save.canWrite()) {
                                        path = Paths.get(parts[0]);
                                    } else {
                                        System.out.println("Directory does not have read/write access! Please specify a different directory or change directory permissions and try again");
                                        promptSave(parts);
                                    }
                                }else{
                                    System.out.println("Cancelled save overwrite");
                                }
                            }else if(!save.exists() || !save.isFile()){
                                System.out.println("Save File does not exists. Do you want to create a new save? (y/n)");
                                if(scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")){
                                    try{
                                        save.createNewFile();
                                    }catch(java.io.IOException x){
                                        System.err.format(">IOException: %s%n", x);
                                    }
                                    if(save.canRead() && save.canWrite()){
                                        try{
                                            oOut.writeObject(world);
                                            oOut.close();
                                        }catch(java.io.IOException x){
                                            System.err.format(">IOException: %s%n", x);
                                        }catch(java.lang.NullPointerException x){
                                            System.err.format(">NullPointerException: %s%n", x);
                                        }
                                    }else{
                                        System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                                        promptSave(parts);
                                    }
                                }else{
                                    System.out.println("Cancelled create new save");
                                }
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }

    public World loadSave(){
        System.out.println("Please enter a directory (folder) path to your saved game (ex: C:\\\\Games\\Zork Clone\\save.dat or /Users/Example User/Documents/Zork Clone/save.dat) or press enter to cancel");
        System.out.println("If you want to specify a relative path (not starting with C:\\ or /), your save should be located in the same directory as the game");
        String[] parts = scanner.nextLine().split(" ");
        Room[] rooms;
        if (parts[0].length() < 1) {
            System.out.println("Game Save Cancelled");
        }
        return promptLoad(parts);
    }

    private World promptLoad(String[] parts){
        switch (parts[0]) {
            case "cancel":
            case "Cancel":
            return null;
            default:
            Path path = Paths.get(parts[0]);
            save = new File(path.toUri());

            try{
                oIn = new ObjectInputStream(Files.newInputStream(path));
            }catch(java.io.IOException x){
                System.err.format(">IOException: %s%n", x);
            }

            if(save.exists() && save.isFile()){
                System.out.println("Save file found: " + path.toString());
                if(save.canRead() && save.canWrite()){
                    try{
                        world = (World)oIn.readObject();
                        oIn.close();
                    }catch(java.io.IOException x){
                        System.err.format(">IOException: %s%n", x);
                    }catch(ClassNotFoundException x){
                        System.err.format(">ClassNotFoundException: %s%n", x);
                    }catch(java.lang.NullPointerException x){
                        System.err.format(">NullPointerException: %s%n", x);
                    }
                }else{
                    System.out.println("File does not have read/write access! Please specify a different file or change file permissions and try again");
                    promptLoad(parts);
                }
                //System.out.println("Cancelled save overwrite");
            }else if(!save.exists() || !save.isFile()){
                System.out.println("File not found. Check for typos or incorrect path notation and try again");
                promptLoad(parts);
            }
            return world;
        }
    }

    /* Example code snippets:
    Charset charset = Charset.forName("US-ASCII");
    String s = ...;
    try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
    writer.write(s, 0, s.length());
    } catch (IOException x) {
    System.err.format("IOException: %s%n", x);
    }*/
}