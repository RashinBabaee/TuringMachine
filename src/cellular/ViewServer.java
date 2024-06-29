package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * this is the class for interface of server window
 */
public class ViewServer {

	private Stage stage5 = new Stage();
	private ControllerServer controllerServer;
	private TextField portTextField = new TextField();
	private TextArea textArea = new TextArea();

	/**
	 * this function is for getting port as a String
	 * 
	 * @return portTextField.getText
	 */
	public String getPort() {
		return portTextField.getText();
	}

	/**
	 * this function is for showing text in textArea
	 * 
	 * @param text is String
	 */
	public void displayText(String text) {
		try {
		textArea.appendText(text);
		 } catch (IndexOutOfBoundsException e) {
		        // Handle IndexOutOfBoundsException
		        // Log the error and handle it appropriately
		        System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		        e.printStackTrace();
		        // Other error handling actions
		    }
	}

	/**
	 * this function is for showing server window
	 */
	void show() {
		stage5.show();
	}

	/**
	 * it is the constructor for the graphical user interface of server window
	 * 
	 * @param controllerServer is a parameter for viewServer
	 */
	public ViewServer(ControllerServer controllerServer) {
		this.controllerServer = controllerServer;

		BorderPane borderMain = new BorderPane();
		Scene scene4 = new Scene(borderMain, 600, 300);
		stage5.setScene(scene4);
		stage5.setTitle("Game Server - JAP(fall 2023)");

		FlowPane topFlow = new FlowPane();
		borderMain.setTop(topFlow);

		Image image = new Image("tm-server.png");
		ImageView imageView = new ImageView(image);
		topFlow.getChildren().add(imageView);
		topFlow.setAlignment(Pos.CENTER);

		FlowPane centerFlow = new FlowPane();
		borderMain.setCenter(centerFlow);

		Label portLabel = new Label("Port: ");

		Button startBut = new Button("Start");
		Button ModelBut = new Button("Model");
		CheckBox finalizesCheck = new CheckBox("Finalizes");
		Button endBut = new Button("End");

		centerFlow.getChildren().add(portLabel);
		FlowPane.setMargin(portLabel, new Insets(10));
		centerFlow.getChildren().add(portTextField);
		FlowPane.setMargin(portTextField, new Insets(10));

		centerFlow.getChildren().add(startBut);
		FlowPane.setMargin(startBut, new Insets(10));
		startBut.setTooltip(new Tooltip("Start"));
		startBut.setOnAction(controllerServer);

		centerFlow.getChildren().add(ModelBut);
		FlowPane.setMargin(startBut, new Insets(10));
		ModelBut.setTooltip(new Tooltip("Model"));
		ModelBut.setOnAction(controllerServer);

		centerFlow.getChildren().add(finalizesCheck);
		FlowPane.setMargin(finalizesCheck, new Insets(10));
		finalizesCheck.setTooltip(new Tooltip("Finalizes"));
		finalizesCheck.setOnAction(controllerServer);

		centerFlow.getChildren().add(endBut);
		FlowPane.setMargin(endBut, new Insets(10));
		endBut.setTooltip(new Tooltip("End"));
		endBut.setOnAction(controllerServer);

		FlowPane bottomFlow = new FlowPane();
		borderMain.setBottom(bottomFlow);
		bottomFlow.getChildren().add(textArea);
	}

}
