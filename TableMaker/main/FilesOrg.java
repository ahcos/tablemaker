
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
    	String even = "<tr class=\"tr-even tr-";
    	String odd = "<tr class=\"tr-odd tr-";
    	String trail = "\"><td class=\"td-0\"";
    	String zId = " id=\"z";
    	String zIdEnd = "\">";
    	String end = "</td></tr>";
    	String result ="";
    	
    	String[] b = a.split("\\r?\\n");
    	//int countEntries = 0;
    	for (int i = 0; i < b.length; i++) {
			if (i == b.length - 1) {
				if (i % 2 == 0) {
					result = result + "\n" + even + "last" + trail + zId + i + zIdEnd +  b[i] + end;
				}
				else {
					result = result + "\n" + odd + "last" + trail + zId + i + zIdEnd +  b[i] + end;				}
			}
			else if (i % 2 == 0) {
				result = result + "\n" + even + i + trail + zId + i + zIdEnd +  b[i] + end;
			}
			else {
				result = result + "\n" + odd + i + trail + zId + i + zIdEnd +  b[i] + end;
			}
//			countEntries++;
			
		}
    		
    	return result;
    }
    
    /**
     * Schreibt von der fromFile in die toFile
     */
	public void schreiben () {
	    
	  try (BufferedWriter writer = Files.newBufferedWriter(toFilePath)) //es wird angegeben wo rein zu schreiben ist
	  {
	      writer.write(createTable()); // es wird geschrieben in die angegebene File von der angegebenen File
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
