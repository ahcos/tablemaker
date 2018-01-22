package tmaker;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ButtonFactory {

    private String targetString = "";
    private String sourceString = "";
    private Stage primaryStage;
    
    public ButtonFactory(Stage stage){
    	this.primaryStage = stage;
    }

    /**
     * Beschreibt das Verhalten des "Table"-Buttons
     * @param TextArea textTarget
     * @param TextArea textSource
     * @param String name
     * @return Table Button
     */
    
    public Button makeTableButton (String name, TextArea textSource, TextArea textTarget) {
    	Button b = new Button(name);
    	FilesOrg file1 = new FilesOrg(sourceString, targetString);
    	
    	b.setOnAction(
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                      	if (!(sourceString == "" && targetString == "")) {
	                    	try {
								file1.go('t', null, null);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
                    	}
                    	
                    	else {
                    		try {
								file1.go('t', textSource, textTarget);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
                    	}
                    }                
                });  
    	return b;
    }
    
    /**
     * Erschafft einen Button, mit dem man eine Liste von Links erstellen kann.
     * @param TextArea textTarget 
     * @param TextArea textSource
     * @param String name
     * @return Link Button
     */
    
    public Button makeLinkButton (String name, TextArea textSource, TextArea textTarget) {
    	Button b = new Button(name);
    	FilesOrg file1 = new FilesOrg(sourceString, targetString);

    	
    	b.setOnAction(
    			
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                    	if (!(sourceString == "" && targetString == "")) {
	                    	try {
								file1.go('l', null, null);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
                    	}
                    	
                    	else {
                    		try {
								file1.go('l', textSource, textTarget);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
                    	}
                    }                
                });
    	return b;
    }
    /**
     * Erstellt einen "Zieldatei"-Button
     * @param String name
     * @param Stage primaryStage
     * @param Text text
     * @return Source File Button
     */

	public Button openSourceButton (String name, Text text) {
    	Button b = new Button(name);
    	FileChooser fileChooser = new FileChooser();
		b.setOnAction(
    		new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                    	text.setText(file.getName());
                    	sourceString = file.getPath();
                    }
                }                
            });
		return b;
    }
	/**
	 * Erstellt einen "Quelldatei"-Button
	 * @param name
	 * @param text
	 * @param Stage primaryStage
	 * @return Target File Button
	 */

	public Button openTargetButton (String name, Text text){
    	Button b = new Button(name);
    	FileChooser fileChooser = new FileChooser();
		b.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        text.setText(file.getName());
                        targetString = file.getPath();
                    }
                }
            });
    	return b;
	}
	
	public Button clearSourceButton (String name, Text text) {
		Button b = new Button(name);
		b.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                   sourceString = "";
	                   text.setText("Keine Datei gewählt.");
	                   
	                }
	            });
		
		
		return b;
	}
	
	public Button clearTargetButton (String name, Text text) {
		Button b = new Button(name);
		b.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                   targetString = "";
	                   text.setText("Keine Datei gewählt.");
	                   
	                }
	            });
		
		
		return b;
	}
    
}
