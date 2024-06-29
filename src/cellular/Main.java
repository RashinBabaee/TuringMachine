
package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 5 Nov 2023
 */

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * This is a main class to run the program and to creates a simple graphical
 * user interface (GUI) with various components, including an image, a combo
 * box, and buttons.
 */
public class Main extends Application {
	/**
	 * constructor of class
	 */
	public Main() {

	}

	/**
	 * This is a start method
	 */
	@Override
	public void start(Stage stage) {

		CellularAutomata cellularAutomata = new CellularAutomata();
		GameView game = new GameView();
		
		ControllerServer controllerServer = new ControllerServer();
		BorderPane border = new BorderPane(); // border for first window
		Scene scene = new Scene(border, 500, 250); // scene for first window
		stage.setScene(scene);
		stage.setTitle("[JAP - Computer Science]"); // title for first window

		Image image = new Image("CSmin.png"); // image for first window
		ImageView imageView = new ImageView(image);

		FlowPane centerFlow = new FlowPane(); // flowpane for center of first window
		centerFlow.getChildren().add(imageView);
		centerFlow.setAlignment(Pos.CENTER); // align the flowpane
		FlowPane.setMargin(imageView, new Insets(20));
		// name of features for drop down
		String array[] = new String[] { "[A12] CA - Cellular Automata", "[A22] GL - Game of Life",
				"[A32] TMS - Turing Machine Server", "[A32] TMC - Turing Machine Client" };

		ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(array)); // combox for first window
		comboBox.setValue(array[0]);
		centerFlow.getChildren().add(comboBox); // add combobox to centerflow
		FlowPane.setMargin(comboBox, new Insets(20));
		border.setCenter(centerFlow);

		FlowPane bottomFlow = new FlowPane(); // flowpane for bottom of first window

		Button okButton = new Button("Ok"); // it is ok button

		Button cancelButton = new Button("Cancel"); // it is cancel button
		Button helpButton = new Button("Help"); // it is help button

		bottomFlow.getChildren().add(okButton); // add ok button to bottomflow
		bottomFlow.getChildren().add(cancelButton); // add cancel button to bottomflow
		bottomFlow.getChildren().add(helpButton); // add help button to bottomflow

		bottomFlow.setAlignment(Pos.CENTER);
		FlowPane.setMargin(okButton, new Insets(10));
		FlowPane.setMargin(cancelButton, new Insets(10));
		FlowPane.setMargin(helpButton, new Insets(10));

		okButton.setOnMouseClicked(e -> { // if user clicks ok button, can go to that option
			if(comboBox.getValue()==array[0]) {
				cellularAutomata.show();
			}else if(comboBox.getValue()==array[1]) {
				game.showGame();
			}
			else if(comboBox.getValue()==array[2]) {
				controllerServer.show();
			}
			else if(comboBox.getValue()==array[3]) {
				ControllerClient controllerClient = new ControllerClient();
				controllerClient.show();
				
			}
		});
		cancelButton.setOnMouseClicked(e -> { // if user clicks cancel button, can exit the program
			System.exit(0);
		});

		border.setBottom(bottomFlow);

		stage.show();

	}

	/**
	 * This method is for running the program
	 * 
	 * @param args is a parameter
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
