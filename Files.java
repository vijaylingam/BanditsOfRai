/**
 * Created by vijaychandra on 3/17/16.
 */

import java.io.File; //APIs declaration
import java.lang.String;

public class Files {

    int files = 0;
    public void printFandD(File cwd){ //method to print files and directories
    	int filecount = 0;
        for(File file: cwd.listFiles()){ //cwd here is the variable denoting current working directory
            if (file.isFile()) {

                System.out.println("0   " + "f   " + file); 
            } 
            else {
                filecount = fileCounter(file); //recursive call to filecounter method
                System.out.println(filecount + "	d   " + file);
                printFandD(file);
            }
        }
    }

    public int fileCounter(File cwd){
        files = 0;
        for(File file: cwd.listFiles()){
            if(file.isFile()){ //checks whether the selection is a file and returns appropriate boolean value
                files+=1;
            }
            else{
                files += 0;
            }
        }
        return files;
    }

    
    public static void main(String[] args) {
        Files start = new Files();
        String currentdirectory = "/Users/vijaychandra/Desktop/test"; //current directory is set to working directory
        File directory = new File(currentdirectory);
        start.printFandD(directory);
    }

    }

