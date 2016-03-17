/**
 * Created by vijaychandra on 3/17/16.
 */

import java.io.File; //APIs declaration
import java.lang.String;

public class Files {

    int files = 0;
    static String[] TYPES = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml"}; //this list can be extended by adding in new strings of extensions
    public void printFandD(File cwd){ //method to print files and directories
    	int filecount = 0;
    	int recursivefcount;
        for(File file: cwd.listFiles()){ //cwd here is the variable denoting current working directory
            if (file.isFile()) {

                System.out.println("0   0   " + "f   " + file); 
            } 
            else {
                filecount = fileCounter(file); //recursive call to filecounter method
                recursivefcount = recursiveFileCounter(file);
                System.out.println(recursivefcount+"    "+filecount + "  d   " + file);
                printFandD(file);
            }
        }
    }

    public int fileCounter(File cwd){ //method to walk through files directly under the current folder
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

    public int recursiveFileCounter(File cwd){ //method to recursively count the number of files under a directory
        files = 0;
        for(File file: cwd.listFiles()){
            if(file.isFile()){
                files+=1;
            }
            else{
                files += fileCounter(file); //recursive call to fileCounter method
            }
        }
       return files;
    }

    public void extension(File cwd){
        for(String ext: TYPES){
            int count = 0;
            for(File file: cwd.listFiles()) {
                if (file.isFile()) {
                    if (file.toString().endsWith(ext)) { //using .endsWith() method to check if the selected files extension matches with the variable 'ext'
                        count += 1;
                    }
                } else {
                    count += extrecurse(file, ext); //extrecurse refers to external recursive method.
                }
            }
            System.out.println(ext +"  -  "+count); 
        }
    }

  public int extrecurse(File directory, String extension){ 
        int count = 0;
        for(File file: directory.listFiles()) {
            if (!file.isDirectory()) {
            if (file.toString().toLowerCase().endsWith(extension)) { //converting the file path to string first and then converting it to lower case and extracting the extension
                count += 1;
            }
        }
            else{
                count += extrecurse(file, extension); //recursive call
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        Files start = new Files();
        String currentdirectory = "/Users/vijaychandra/Desktop/test"; //current directory is set to working directory
        File directory = new File(currentdirectory);
        start.printFandD(directory);
        start.extension(directory);
    }

    }

