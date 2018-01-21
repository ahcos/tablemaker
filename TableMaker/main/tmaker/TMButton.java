package tmaker;


import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TMButton {
	

	
    private FileChooser fileChooser = new FileChooser();
    
    private Button sourceButton = new Button("Quelldatei öffnen ...");
    private Button targetButton = new Button("Zieldatei öffnen ...");
    private Button cSource = new Button("Clear");
    private Button cTarget = new Button("Clear");
    private Button generateLink = new Button ("Links erstellen");
    private Button generateTable = new Button ("Table erstellen");
    
  
    private String targetString = "";
    private String sourceString = "";
    private String targetName = "(Name Zieldatei)";
    private String sourceName = "(Name Quelldatei)";

    /**
     * Beschreibt das Verhalten des "Table"-Buttons
     */
    
    public void makeTable () {
    	generateTable.setOnAction(
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                    	FilesOrg file1 = new FilesOrg(sourceString, targetString);
                        try {
							file1.go('t');
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    }                
                });
    	
    }
    
    /**
     * Beschreibt das Verhalten des "Link"-Buttons
     */
    
    private void makeLink () {
    	generateLink.setOnAction(
    			
        		new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                    	FilesOrg file1 = new FilesOrg(sourceString, targetString);
                    	try {
							file1.go('f');
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    }                
                });
    }
    /**
     * Beschreibt das Verhalten des "Zieldatei öffnen ..."-Buttons
     * @param primaryStage um den FileChooser zu öffnen
     * @return 
     */
    
    private void setSourceName (String s) {
    	this.sourceName = s;
    }
    
	private void openSourceButton (Stage primaryStage) {
    	sourceButton.setOnAction(
    		new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                    	setSourceName(file.getName());
                    	
                    	sourceString = file.getPath();
                    }
                }                
            });
    }

	private void openTargetButton (Stage primaryStage){
    	targetButton.setOnAction(
    
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        targetString = file.getPath();
                        targetName = file.getName();
                    }
                }
            });
	}
    
    public Button getClearSourceButton (Stage primaryStage) {
    	return cSource;
    }
    public Button getClearTargetButton (Stage primaryStage) {
    	return cTarget;
    }
    
    public Button getSourceButton (Stage primaryStage) {
    	openSourceButton(primaryStage);
    	return sourceButton;
    }
    
    public Button getTargetButton (Stage primaryStage) {
    	openTargetButton(primaryStage);
    	return targetButton;
    }
    
    public String getTargetName() {
  		return targetName;
  	}


  	public String getSourceName() {
  		return sourceName;
  	}
	
	public String getTargetString() {
		return targetString;
	}
	
	public String getSourceString() {
		return sourceString;
	}
    
	public Button getGenerateLink() {
		makeLink();
		return generateLink;
	}

	public Button getGenerateTable() {
		makeTable();
		return generateTable;
	}
	

}
