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
		
		Text sourceName = new Text("Keine Datei gewählt."); // Name der Quelldatei
    	Text targetName = new Text("Keine Datei gewählt."); // Name der Zieldatei
		
		
		text.setFont(new Font(20)); 
		
		text.setText("Tablemaker 1.0 \n\nQuell- und Zieldatei auswählen \noder Text direkt im Feld eingeben.");
		
		ButtonFactory b = new ButtonFactory(primaryStage); // neues Element der Klasse in der das Button-Handling abläuft
		

        /*
         * Erstellung der verschiedenen Buttons
         */
		
        final Button openSourceButton = b.openSourceButton(sourceName, "Quelldatei wählen...");
        final Button openTargetButton = b.openSourceButton(targetName, "Zieldatei wählen...");
        final Button clearSource = b.clearSource("Clear", sourceName);
        final Button clearTarget = b.clearTarget("Clear", targetName);
      
        final Button createLinkButton = b.makeLinkButton("Links erstellen", textSource, textTarget);
        final Button createTableButton = b.makeTableButton("Table erstellen", textSource, textTarget);

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
        
        
        primaryStage.show();
		
	}
        	
	public static void main (String[] args) {
		launch(args);
	}	

}
