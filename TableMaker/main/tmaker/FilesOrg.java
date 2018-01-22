package tmaker;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javafx.scene.control.TextArea;


public class FilesOrg {
	
	private String fromFile;
	private Path toFilePath;

	
    public FilesOrg(String fromFile, String toFile) {
    	this.fromFile = fromFile;
    	this.toFilePath = Paths.get(toFile);
    }

    
    /**
     * Ruft die {@link tmaker.CodeCreator} Klasse auf, um eine Table zu erzeugen.
     */
	private void schreibenTable () {
	  CodeCreator c = new CodeCreator(); 
	  try (BufferedWriter writer = Files.newBufferedWriter(toFilePath)) //es wird angegeben wo rein zu schreiben ist
	  {
		  String a = readFile(fromFile);
	      writer.write(c.createTable(a)); // es wird geschrieben in die angegebene File von der angegebenen File
	  } catch (IOException e) {
		e.printStackTrace();
	  }
	  }
	
	/**
	 * Ruft die {@link tmaker.CodeCreator} Klasse auf, um eine Linksammlung zu erzeugen.
	 */
	private void schreibenLink () {
		CodeCreator c = new CodeCreator();
		  try (BufferedWriter writer = Files.newBufferedWriter(toFilePath)) //es wird angegeben wo rein zu schreiben ist
		  {
			  String a = readFile(fromFile);
		      writer.write(c.createLink(a)); // es wird geschrieben in die angegebene File von der angegebenen File
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		  }
	



    /**
     * Programm-Entrypoint
     * @param type
     * @param String target
     * @param String source 
     * @throws IOException
     * 
     */
    public void go (char type, TextArea source, TextArea target) throws IOException {
    	
    	
    	if ((source == null && target == null) && type == 'l' ) {
    		schreibenLink();
    	}
    	else if ((source == null && target == null) && type =='t') {
    		schreibenTable();
    	}
    	else {
	    	if (type == 'l') {
	    		CodeCreator c = new CodeCreator();
	    		target.setText(c.createLink(source.getText()));
	    	}
				
			else if (type == 't') {
				CodeCreator c = new CodeCreator();
	    		target.setText(c.createTable(source.getText()));
			}
			
			else
				System.exit(-1);
    	}
    	
    	//legacy code below
    	
//    	CheckInput c = new CheckInput(); //Objekt um den Dateiinput auf Korrektheit zu überprüfen
//    	
//    	c.check(fromFile, toFile);
//    	
//    	if (c.isFromFileExists() && c.isToFileExists()) { //sofern beide Dateien existieren und im korrekten Zustand sind
//    		if (type == 'l')
//    			schreibenLink();
//    		else if (type == 't')
//    			schreibenTable();
//    		else {
//    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    			System.out.println("\'L\' für Linksammlung, \'T\' für Table! Neue Eingabe oder \'Q\', um das Programm zu beenden.");
//    			char[] input = br.readLine().toCharArray(); // Userinput wird auf ein Array abgelegt, dieses folgend ausgelesen
//    			br.close();
//    		
//    			input[0] = Character.toLowerCase(input[0]);
//    			if ((input[0] == 'q') && input.length == 1)
//    				System.exit(0);
//    			else if (input.length > 1) { // user gibt mehr als ein Zeichen ein
//    				System.out.println("Ein Komiker! Programm wird beendet.");
//    				System.exit(-1);
//    			}
//    			else
//    				go(input[0]); //Rekursiver Aufruf mit neuem Zeichen
//    			
//    		}
//    	}
//    	else if (!c.isFromFileExists()) { //alter Quelldateipfad ungültig, neuer Pfad wird aufgerufen, dann go() rekursiv
//    		this.fromFile = c.getNewFromFile(); 
//    		go(type);
//    	}
//    	else {
//    		System.out.println("Unbekannter Fehler - Dateipfade korrekt?");
//    		System.exit(-1);
//    	}
    	
    }
    
	/**
	 * Liest die angegebene Datei aus und gibt sie als String zurück
	 * @param fromFile
	 * @return String mit \n
	 */
    private String readFile(String fromFile)
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
