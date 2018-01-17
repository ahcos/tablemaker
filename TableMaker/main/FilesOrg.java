
import java.io.BufferedWriter;
import java.io.IOException;


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
    /**
     * Erzeugt einen internen HTML Link nach Vorgaben des Uni Köln Typ3 Hypertext Projekts
     * @return collection of HTML Links as String
     */
    private String createLink () {
    	String a = readLineByLineJava8(fromFile);
    	String result ="";
    	String link = "<sup><a href=\"http://www.hypertextprojekt.phil-fak.uni-koeln.de/?id=13759#z";
    	String linkEnd = "\" class=\"internal-link\" title=\"";
    	String supStart = "\" rtekeep=\"1\">";
    	String supEnd = "</a></sup>";
    	
    	
    	String[] b = a.split("\\r?\\n");
    	
    	for (int i = 0; i < b.length; i++) {
			result = result + link + i + linkEnd + b[i] + supStart + (i+1) + supEnd + "\r\n \r\n";
		}
    	
    	return result;
    	
    }
    
    /**
     * Erzeugt eine Table nach den Vorgaben des Uni-Köln Hypertextprojekt typo3-Codes
     * @return HTML table as String
     */
    private String createTable () {
    	String a = readLineByLineJava8(fromFile); //ausgelesene Datei als String
    	
    	//html code parts
    	String even = "<tr class=\"tr-even tr-";
    	String odd = "<tr class=\"tr-odd tr-";
    	String trail = "\"><td class=\"td-0\"";
    	String zId = " id=\"z"; // id zum auffinden des Objekts/für den HTML-Anchor
    	String zIdEnd = "\">";
    	String end = "</td></tr>";
    	String result ="";
    	
    	
    	String[] b = a.split("\\r?\\n"); //String splitten an \n
    	//int countEntries = 0;
    	for (int i = 0; i < b.length; i++) {
			if (i == b.length - 1) { //falls man beim letzten Element ist, muss das "last" Table-Element angefügt werden
				if (i % 2 == 0) {
					result = result + "\r\n \r\n" + even + "last" + trail + zId + i + zIdEnd +  b[i] + end;
				}
				else {
					result = result + "\r\n \r\n" + odd + "last" + trail + zId + i + zIdEnd +  b[i] + end;				}
			}
			else if (i % 2 == 0) { // gerade Elemente
				result = result + "\r\n \r\n" + even + i + trail + zId + i + zIdEnd +  b[i] + end;
			}
			else { //ungerade Elemente
				result = result + "\r\n \r\n" + odd + i + trail + zId + i + zIdEnd +  b[i] + end;
			}
//			countEntries++;
			
		}
    		
    	return result;
    }
    
    /**
     * Schreibt Table von der fromFile in die toFile
     */
	public void schreibenTable () {
	    
	  try (BufferedWriter writer = Files.newBufferedWriter(toFilePath)) //es wird angegeben wo rein zu schreiben ist
	  {
	      writer.write(createTable()); // es wird geschrieben in die angegebene File von der angegebenen File
	  } catch (IOException e) {
		e.printStackTrace();
	  }
	  }
	
	public void schreibenLink () {
	    
		  try (BufferedWriter writer = Files.newBufferedWriter(toFilePath)) //es wird angegeben wo rein zu schreiben ist
		  {
		      writer.write(createLink()); // es wird geschrieben in die angegebene File von der angegebenen File
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
