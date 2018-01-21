package tmaker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Text text = new Text(); //"Begrüßungstext"
	
		
		TextArea textSource = new TextArea(); // Textfeld für direkte Eingabe der Elemente
		TextArea textTarget = new TextArea(); // Textfeld für direkte Ausgabe
		
		textSource.setText("Quelltext");
		textTarget.setText("Ergebnis");
		
		Text sourceName = new Text(); // Name der Quelldatei
    	Text targetName = new Text(); // Name der Zieldatei
		
		
		text.setFont(new Font(20)); 
		
		text.setText("Tablemaker 1.0 \n\nQuell- und Zieldatei auswählen \noder Text direkt im Feld eingeben.");
		
		TMButton b = new TMButton(); // neues Element der Klasse in der das Button-Handling abläuft
		String getSourceName = b.getSourceName();
		String getTargetName = b.getTargetName();

        /*
         * Erstellung der verschiedenen Buttons
         */
		
        final Button openSourceButton = b.getSourceButton(primaryStage);
        final Button openTargetButton = b.getTargetButton(primaryStage);
        final Button clearSource = b.getClearSourceButton(primaryStage);
        final Button clearTarget = b.getClearTargetButton(primaryStage);
        final Button createLinkButton = b.getGenerateLink();
        final Button createTableButton = b.getGenerateTable();

    	sourceName.setText(getSourceName); // FUNKTIONIERT NOCH NICHT
    	targetName.setText(getTargetName);
    	
		/*
		 * GridPane welches die Elemente hält
		 * HBox hält die nebeneinander angeordneten Buttons
		 */
    	
    	
    	
    	
		final GridPane inputGridPane = new GridPane();
		
		
		
		HBox sourceButtons = new HBox();
		HBox targetButtons = new HBox();
		HBox createButtons = new HBox();
		
		sourceButtons.getChildren().add(openSourceButton);
		sourceButtons.getChildren().add(clearSource);
		
		targetButtons.getChildren().add(openTargetButton);
		targetButtons.getChildren().add(clearTarget);
		
		createButtons.getChildren().addAll(createTableButton, createLinkButton);
	    
		GridPane.setConstraints(text, 0, 0);
        GridPane.setConstraints(sourceButtons, 0, 1);
        GridPane.setConstraints(targetButtons, 1, 1);
        GridPane.setConstraints(sourceName, 0, 2);
        GridPane.setConstraints(targetName, 1, 2);
        GridPane.setConstraints(textSource,	0, 3);
        GridPane.setConstraints(textTarget,	1, 3);
        GridPane.setConstraints(createButtons, 0, 4);
        
        // Abstandsdeklaration zwischen den Elementen
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll( text, textSource, textTarget, sourceButtons, targetButtons, targetName, sourceName, createButtons); //Elemente in das Grid einfügen
 
        final Pane rootGroup = new VBox(100);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));
 
        primaryStage.setTitle("TableMaker 1.0"); // Programmleiste
        primaryStage.setScene(new Scene(rootGroup));
        
  
		
	}
        	
	public static void main (String[] args) {
		launch(args);
	}	

}
