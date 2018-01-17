import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesOrg {
	
	private String fromFile;
	private Path toFilePath;


    public FilesOrg(String fromFile, String toFile) {
    	this.fromFile = fromFile;
    	this.toFilePath = Paths.get(toFile);
    	
    }
    
    private String createTable () {
    	String a = readLineByLineJava8(fromFile);
    	StringReader r = new StringReader(a);
    	
    	return a;
    }
    
    /**
     * Schreibt von der fromFile in die toFile
     */
	public void schreiben () {
	    
	  try (BufferedWriter writer = Files.newBufferedWriter(toFilePath)) //es wird angegeben wo rein zu schreiben ist
	  {
	      writer.write(readLineByLineJava8(fromFile)); // es wird geschrieben in die angegebene File von der angegebenen File
	  } catch (IOException e) {
		e.printStackTrace();
	  }
	  }

	/**
	 * Liest die angegebene Datei aus und gibt sie als String zurück
	 * @param fromFile
	 * @return String mit \n
	 */
    private String readLineByLineJava8(String fromFile)
    {
        StringBuilder contentBuilder = new StringBuilder();
 
        try (Stream<String> stream = Files.lines( Paths.get(fromFile), StandardCharsets.ISO_8859_1)) // charset nicht utf-8, klappt nicht!
        {
       
        	
            stream.forEach(s -> contentBuilder
            		.append(s)
            		.append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
 
        return contentBuilder.toString();
    }


	
}
