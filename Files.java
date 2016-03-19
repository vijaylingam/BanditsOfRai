/**
 * Created by vijaychandra on 3/17/16.
 */

import java.io.File; //APIs declaration
import java.lang.String;
public class Files {
    static String[] TYPES = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml"}; //this list can be extended by adding in new strings of extensions
    /**
	* printFandD method takes in the paramater cwd, which is the current working directory, and prints out: the number of files recurvsively under a folder by calling 
	* recursiveFileCounter method, number of files directly under a folder by calling on the fileCounter method, 'f' followed by the directory path if its a file or
	* d followed by the directory path if its a folder. This method does not return anything.
	*/
    public void printFandD(File cwd){ 
    	int filecount = 0;
    	int recursivefcount;
        for(File file: cwd.listFiles()){ 
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
    /**
    * fileCounter function takes in cwd (current working directory) as the paramater, and is invoked by printFandD method. It runs through through the folder and
    * returns the number of files directly under a folder and this count is saved in the variable 'filecount' in the printFandD method.
    */
    public int fileCounter(File cwd){ 
        int files = 0;
        for(File file: cwd.listFiles()){
            if(file.isFile()){ //checks whether the current selection is a file and returns appropriate boolean value.
                files+=1;
            }
            else{
                files += 0;
            }
        }
        return files; 
    }
    /**
    * recursiveFileCounter function takes cwd (current working directory) as a parameter, and is invoked by printFandD method. It recursively goes through the folder and returns
    * the total number of files under a folder. This count is saved in the variable 'recursivefcount' in the printFandD method.
    */
    public int recursiveFileCounter(File cwd){ 
        int files = 0;
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
    /**
    * extension method takes cwd (current working directory) as the paramater and is invoked by the main method. It invokes the extrecuse and extrecursesize functions
    * and prints out the statistics of each kind of extension along with their size. This method does not return anything.
    */
    public void extension(File cwd){
        for(String ext: TYPES){
            int count = 0;
            long size = 0;
            for(File file: cwd.listFiles()) {
                if (file.isFile()) {
                    if (file.toString().toLowerCase().endsWith(ext)) {
                        count += 1;
                        size = size + file.length();
                    }
                } else {
                    count += extrecurse(file, ext);
                    size += extrecursesize(file, ext);
                }
            }
            System.out.println(ext +"  -  "+count+" "+ String.format("%,d", size)); //%,d places comma after every 3 digits in the size variable. 
        }
    }
    /**
    * extrecurse (external recursion) function takes directory and extension as paramaters which are of the type File and String. This function returns the count of
    * file type of the given extension.
    */
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
    /**
    * extrecursesize (external recursion for size) takes in 'directory' and 'extension' as parameters and returns the variable 'size', which is the total size of files
    * of a certain extension in bytes.
    */
    public int extrecursesize(File directory, String extension){ // external method to calculating the size recursively
        long size = 0;
        for(File file: directory.listFiles()){
            if (!file.isDirectory()) {
                if (file.toString().toLowerCase().endsWith(extension)) {
                    size += file.length();
                }
            }
            else{
                size += extrecursesize(file, extension);
            }
        }
        return (int) size; //converting long to integer type
    }
    public static void main(String[] args) {
        long starttime = System.currentTimeMillis(); //long integer to save the timestamp when the program starts
        Files start = new Files();
        String currentdirectory = "/Users/vijaychandra/Desktop/test"; //change the directory here
        File directory = new File(currentdirectory);
        start.printFandD(directory);
        start.extension(directory);
        long terminationtime = System.currentTimeMillis(); //variable storing the time stamp when the program terminates
        long executiontime = terminationtime - starttime;  
        System.out.printf("Program executed in %d milliseconds.\n", executiontime);
    }
}

