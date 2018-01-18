package tmaker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckInput {
	
	private boolean fromFileExists;
	private boolean toFileExists;
	private String newFromFile;

	public boolean isFromFileExists() {
		return fromFileExists;
	}

	public boolean isToFileExists() {
		return toFileExists;
	}

	public String getNewFromFile() {
		return newFromFile;
	}
	/** Überprüft, ob der Dateinput korrekt ist
	 * 
	 * @param fromFile
	 * @param toFile
	 * @throws IOException
	 */
	public void check (String fromFile, String toFile) throws IOException{
		File tmpFrom = new File(fromFile); //File-Objekt
    	File tmpTo = new File(toFile);


    	if (tmpTo.exists() && tmpTo.isFile()) {
    		this.toFileExists = true;
    	}
    	else {
    		File targetFile = new File(toFile);
    		System.out.println("Keine Zieldatei gefunden, wird erstellt laut Pfad.");
			targetFile.createNewFile(); // Neue Datei wird erstellt
			this.toFileExists = true;
			
    		
    	}
    	if (tmpFrom.exists() && tmpFrom.isFile()){ // fromFile existiert
        	BufferedReader br = new BufferedReader(new FileReader(fromFile));
    		if ( (br.readLine() != null) || tmpFrom.length() >= 1) { // fromFile existiert und hat Einträge
    			br.close();    		
    			this.fromFileExists = true;
    		}
    		else { //fromFile existiert, aber ist leer
    			br.close();
    			System.out.println("Quelldatei leer - neuen Dateinamen angeben oder \"Q\" um Programm zu beenden.");
        		BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
        		String temp = breader.readLine().toString().toLowerCase();
        		if (temp == "q") {
        			System.exit(-1);
        		}
        		else {
        			this.newFromFile = temp; // neuer Pfad für fromFile
        			System.out.println("Neuer Pfad der Sourcedatei: " + temp);
        			this.fromFileExists = false;
        		}
    			
    		}
    	}
    	else { //fromFile existiert nicht
    		System.out.println("Keine Quelldatei vorhanden - neuen Dateinamen angeben oder \"Q\" um Programm zu beenden.");
    		BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
    		String temp = breader.readLine().toString().toLowerCase();
    		if (temp == "q") {
    			System.exit(-1);
    		}
    		else {
    			this.newFromFile = temp;
    			System.out.println("Neuer Pfad der Sourcedatei: " + temp);
    			this.fromFileExists = false;
    		}
    	
	}
		
}
}