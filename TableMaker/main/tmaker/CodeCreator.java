package tmaker;

/**
 * {link {@link #createLink(String)} und {@link #createTable(String)} zum erzeugen der jeweiligen Files, {@link #readFile(String)} zum auslesen der Source-Datei
 * @author FME
 *
 */
public class CodeCreator {
	
    /**
     * Erzeugt einen internen HTML Link nach Vorgaben des Uni Köln Typ3 Hypertext Projekts
     * @return collection of HTML Links as String
     */
    public String createLink (String fromFile) {
    	
    	String a = fromFile;
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
    public String createTable (String fromFile) {

    	String a = fromFile; //ausgelesene Datei als String
    	
    	//html code parts
    	String even = "<tr class=\"tr-even tr-";
    	String odd = "<tr class=\"tr-odd tr-";
    	String trail = "\"><td class=\"td-0\"";
    	String zId = " id=\"z"; // id zum auffinden des Objekts/für den HTML-Anchor
    	String zIdEnd = "\">";
    	String end = "</td></tr>";
    	String result ="";
    	
    	String[] b = a.split("\\r?\\n"); //String splitten an \n
    	
    	for (int i = 0; i < b.length; i++) {
			if (i == b.length - 1) { //falls man beim letzten Element ist, muss das "last" Table-Element angefügt werden
				if (i % 2 == 0) {
					result = result + even + "last" + trail + zId + i + zIdEnd +  b[i] + end  + "\r\n \r\n";
				}
				else {
					result = result + odd + "last" + trail + zId + i + zIdEnd +  b[i] + end  + "\r\n \r\n";				}
			}
			else if (i % 2 == 0) { // gerade Elemente
				result = result  + even + i + trail + zId + i + zIdEnd +  b[i] + end + "\r\n \r\n";
			}
			else { //ungerade Elemente
				result = result  + odd + i + trail + zId + i + zIdEnd +  b[i] + end + "\r\n \r\n";
			}
		}
    	
    	return result;
    }
	

	
	
	

}
