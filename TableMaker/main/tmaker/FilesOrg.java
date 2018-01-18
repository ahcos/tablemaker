package tmaker;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FilesOrg {
	
	private String fromFile;
	private Path toFilePath;
	private String toFile;

	
    public FilesOrg(String fromFile, String toFile) {
    	this.fromFile = fromFile;
    	this.toFile = toFile;
    	this.toFilePath = Paths.get(toFile);
    }

    
    /**
     * Ruft die {@link tmaker.CodeCreator} Klasse auf, um eine Table zu erzeugen.
     */
	private void schreibenTable () {
	  CodeCreator c = new CodeCreator(); 
	  try (BufferedWriter writer = Files.newBufferedWriter(toFilePath)) //es wird angegeben wo rein zu schreiben ist
	  {
	      writer.write(c.createTable(fromFile)); // es wird geschrieben in die angegebene File von der angegebenen File
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
		      writer.write(c.createLink(fromFile)); // es wird geschrieben in die angegebene File von der angegebenen File
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		  }
	



    /**
     * Programm-Entrypoint - ruft abhängig vom User-Input {@link #schreibenLink()} oder {@link #schreibenTable()} auf, fordert neuen Userinput, oder beendet das Programm
     * @param type
     * @throws IOException
     */
    public void go (char type) throws IOException {
    	CheckInput c = new CheckInput(); //Objekt um den Dateiinput auf Korrektheit zu überprüfen
    	
    	c.check(fromFile, toFile);
    	
    	if (c.isFromFileExists() && c.isToFileExists()) { //sofern beide Dateien existieren und im korrekten Zustand sind
    		if (type == 'L')
    			schreibenLink();
    		else if (type == 'T')
    			schreibenTable();
    		else {
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    			System.out.println("\'L\' für Linksammlung, \'T\' für Table! Neue Eingabe oder \'Q\', um das Programm zu beenden.");
    			char[] input = br.readLine().toCharArray(); // Userinput wird auf ein Array abgelegt, dieses folgend ausgelesen
    			br.close();
    			if (input[0] == 'q' && input.length == 1)
    				System.exit(-1);
    			else if (input.length > 1) { // user gibt mehr als ein Zeichen ein
    				System.out.println("Ein Komiker! Programm wird beendet.");
    				System.exit(-1);
    			}
    			else
    				go(input[0]); //Rekursiver Aufruf mit neuem Zeichen
    			
    		}
    	}
    	else if (!c.isFromFileExists()) { //alter Quelldateipfad ungültig, neuer Pfad wird aufgerufen, dann go() rekursiv
    		this.fromFile = c.getNewFromFile(); 
    		go(type);
    	}
    	
    }

	
}
