/**
 * Created by vijaychandra on 3/17/16.
 */

import java.io.File; //APIs declaration
import java.lang.String;

public class Files {

    public void printFandD(File cwd){ //method to print files and directories
        for(File file: cwd.listFiles()){ //cwd here is the variable denoting current working directory
            if (file.isFile()) {
                System.out.println("f   " + file); 
            } 
            else {
                System.out.println("d   " + file);
                printFandD(file);
            }
        }
    }

    
    public static void main(String[] args) {
        Files start = new Files();
        String currentdirectory = "/Users/vijaychandra/Desktop/test"; //current directory is set to working directory
        File directory = new File(currentdirectory);
        start.printFandD(directory);
    }

    }

