import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Scanner;
public class SaveManager{
    private String line;
    private Scanner scanner = new Scanner(System.in);
    private ObjectInputStream oIn;
    private ObjectOutputStream oOut;
    private File save;
    public SaveManager(){

    }

    public int saveGame(){
        System.out.println("Please enter a directory (folder) path to save your game (ex: C:\\\\Games\\Zork Clone or /Users/Example User/Documents/Zork Clone) or press enter to cancel");
        System.out.println("If you want to specify a relative path (not starting with C:\\ or /), your game will be saved in the same directory as the game");
        String[] parts = scanner.nextLine().toLowerCase().split(" ");
        if (parts[0].length() < 1) {
            System.out.println("Game Save Cancelled");
            return 0;
        }
        int promptStatus = setupFile(parts);
        return promptStatus;
    }

    int setupFile(String[] parts){
        switch (parts[0]) {
            case "quit":
            case "exit":
            return -1;
            case "cancel":
            return 0;
            default:
            Path path = Paths.get(parts[0]);
            save = new File(path.toUri());
            //oOut = new ObjectOutputStream(Files.newOutputStream(path));

            oIn = new ObjectInputStream(Files.newInputStream(path));
            if(!save.exists()){
                System.out.println("The path you specified does not represent a valid directory");
            }else if(save.exists() && save.isDirectory()){
                System.out.println("Save directory already exists. Do you want to overwrite it (y/n)");
                if(scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")){
                    if(save.canRead() && save.canWrite()){
                        path = Paths.get(parts[0]);
                    }else{
                        System.out.println("Directory does not have read/write access! Please specify a different directory or change directory permissions and try again");
                        setupFile(parts);
                    }
                }
            }
            return 1;
        }
    }
}