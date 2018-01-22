package tmaker;
import java.io.IOException;

/**
 * Alter Einstiegspunkt fürs Programm
 * 
 * @author FME
 *
 */


public class Main
{
    public static void main(String[] args) throws IOException 
    
    {
//    	FilesOrg file = new FilesOrg("C:\\Users\\FME\\Desktop\\testtext.txt", "C:\\Users\\FME\\Desktop\\lul.txt");

//    	file.schreibenTable();
    	String sourceFile = "C:\\Users\\FME\\Desktop\\testtext1.txt";
    	String targetFile = "C:\\Users\\FME\\Desktop\\lultest.txt";
    	
    	FilesOrg file1 = new FilesOrg(sourceFile, targetFile);
    	
    	file1.go('p', null, null);
    	
    }

}